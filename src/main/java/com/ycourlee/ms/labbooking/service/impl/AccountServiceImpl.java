package com.ycourlee.ms.labbooking.service.impl;

import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.manager.AccountCacheManager;
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
import com.ycourlee.root.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yongjiang
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountManager      accountManager;
    @Autowired
    private RbacManager         rbacManager;
    @Autowired
    private AliyunDysms         aliyunDysms;
    @Autowired
    private Redis               redis;
    @Autowired
    private VerifyCodeSender    verifyCodeSender;
    @Autowired
    private AccountCacheManager accountCacheManager;

    @Override
    public void verifyCode(Integer type, String phone) {
        BizAssert.that(RegexUtil.isPhone(phone), Errors.PHONE_FORMAT_ERROR);
        BizAssert.isNull(accountManager.queryUserBy(type, phone, null), Errors.PHONE_NUMBER_ALREADY_EXISTS);
        accountManager.adminFilter(type, phone);
        BizAssert.that(accountManager.noAliveCodeCurrentPhone(phone), Errors.VERIFY_CODE_RETURN_PRE_60S);
        String code = aliyunDysms.sendVerifyCodeWhenRegister(phone);
        redis.setEx(KeyPool.registerCode(phone), accountManager.bindType(type, code), VerifyCodeSender.VERIFY_CODE_TIMEOUT_IN_SECONDS);
    }

    @Override
    public void verifyCodeByEmail(Integer type, String email) {
        BizAssert.that(RegexUtil.isEmail(email), Errors.EMAIL_FORMAT_ERROR);
        BizAssert.isNull(accountManager.queryUserBy(type, null, email), Errors.EMAIL_ALREADY_EXISTS);
        accountManager.adminFilter(type, email);
        BizAssert.that(accountManager.noAliveCodeCurrentAccount(email), Errors.VERIFY_CODE_RETURN_PRE_60S);
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
        String userIdString = redis.get(KeyPool.tokenMapUid(token));
        if (StringUtil.isNotEmpty(userIdString)) {
            accountCacheManager.expireUserContextCache(Integer.parseInt(userIdString));
        }
        accountCacheManager.expireToken(token);
    }
}
