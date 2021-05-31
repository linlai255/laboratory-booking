package com.ycourlee.ms.labbooking.service.impl;

import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.manager.AccountManager;
import com.ycourlee.ms.labbooking.manager.RbacManager;
import com.ycourlee.ms.labbooking.manager.spec.AliyunDysms;
import com.ycourlee.ms.labbooking.manager.spec.Redis;
import com.ycourlee.ms.labbooking.manager.spec.VerifyCodeSender;
import com.ycourlee.ms.labbooking.model.bo.request.RegisterRequest;
import com.ycourlee.ms.labbooking.model.bo.response.CheckVerifyCodeResponse;
import com.ycourlee.ms.labbooking.service.AccountService;
import com.ycourlee.ms.labbooking.util.BizAssert;
import com.ycourlee.ms.labbooking.util.KeyPool;
import com.ycourlee.ms.labbooking.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yongjiang
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountManager   accountManager;
    @Autowired
    private RbacManager      rbacManager;
    @Autowired
    private AliyunDysms      aliyunDysms;
    @Autowired
    private Redis            redis;
    @Autowired
    private VerifyCodeSender verifyCodeSender;

    @Override
    public void verifyCode(Byte type, String phone) {
        BizAssert.that(RegexUtil.isPhone(phone), "手机号格式不正确");
        BizAssert.isNull(accountManager.queryUserBy(type, phone, null), Errors.PHONE_NUMBER_ALREADY_EXISTS);
        accountManager.adminFilter(type, phone);
        BizAssert.that(accountManager.noAliveCodeCurrentPhone(phone), "验证码只能每60s获取一次");
        String code = aliyunDysms.sendVerifyCodeWhenRegister(phone);
        redis.setEx(KeyPool.registerCode(phone), accountManager.bindType(type, code), VerifyCodeSender.VERIFY_CODE_TIMEOUT_IN_SECONDS);
    }

    @Override
    public void verifyCodeByEmail(Byte type, String email) {
        BizAssert.that(RegexUtil.isEmail(email), "邮箱格式不正确");
        BizAssert.isNull(accountManager.queryUserBy(type, null, email), Errors.EMAIL_ALREADY_EXISTS);
        accountManager.adminFilter(type, email);
        BizAssert.that(accountManager.noAliveCodeCurrentAccount(email), "验证码每60s只可获取一次");
        String code = verifyCodeSender.sendEmailVerifyCodeWhenRegistration(email);
        redis.setEx(KeyPool.registerCode(email), accountManager.bindType(type, code), VerifyCodeSender.VERIFY_CODE_TIMEOUT_IN_SECONDS);
    }

    @Override
    public CheckVerifyCodeResponse checkVerifyCode(String account, String verifyCode) {
        return CheckVerifyCodeResponse.builder()
                .registerKey(accountManager.checkCodeAndReturnKey(account, verifyCode))
                .build();
    }

    @Override
    public void register(RegisterRequest request) {
        accountManager.checkRegisterKey(request);
        rbacManager.saveUser(request.getType(), request.getPhone(), request.getEmail(), request.getPassword());
    }

    @Override
    public void logout(String token) {
        redis.delete(KeyPool.token(token));
    }
}
