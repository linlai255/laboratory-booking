package com.ycourlee.ms.labbooking.service.impl;

import com.ycourlee.ms.labbooking.autoconfig.AliyunDysmsProperties;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.manager.AccountManager;
import com.ycourlee.ms.labbooking.manager.AliyunDysms;
import com.ycourlee.ms.labbooking.manager.RbacManager;
import com.ycourlee.ms.labbooking.manager.Redis;
import com.ycourlee.ms.labbooking.model.bo.request.LoginRequest;
import com.ycourlee.ms.labbooking.model.bo.request.RegisterRequest;
import com.ycourlee.ms.labbooking.model.bo.response.CheckVerifyCodeResponse;
import com.ycourlee.ms.labbooking.service.AccountService;
import com.ycourlee.ms.labbooking.util.BizAssert;
import com.ycourlee.ms.labbooking.util.KeyPool;
import com.ycourlee.ms.labbooking.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author yongjiang
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountManager        accountManager;
    @Autowired
    private RbacManager           rbacManager;
    @Autowired
    private AliyunDysms           aliyunDysms;
    @Autowired
    private AliyunDysmsProperties properties;
    @Autowired
    private Redis                 redis;

    @Override
    public void verifyCode(Integer type, String phone) {
        BizAssert.that(RegexUtil.isPhone(phone), "手机号格式不正确");
        BizAssert.isNull(accountManager.queryUserBy(type, phone), Errors.PHONE_NUMBER_ALREADY_EXISTS);
        accountManager.adminFilter(type, phone);
        BizAssert.that(accountManager.noAliveCodeCurrentPhone(phone), "验证码只能每60s获取一次");
        String code = aliyunDysms.sendCacheVerifyCodeWhenRegister(phone);
        redis.setEx(KeyPool.code(phone), accountManager.bindType(type, code), properties.getVerifyCodeAliveTimeout(), TimeUnit.SECONDS);
    }

    @Override
    public CheckVerifyCodeResponse checkVerifyCode(String phone, String verifyCode) {
        return CheckVerifyCodeResponse.builder()
                .registerKey(accountManager.checkCodeAndReturnKey(phone, verifyCode))
                .build();
    }

    @Override
    public void register(RegisterRequest request) {
        accountManager.checkRegisterKey(request);
        rbacManager.createUser(request.getType(), request.getPhone(), request.getPassword());
    }

    @Override
    public void login(LoginRequest request) {

    }

    @Override
    public Boolean checkAccount() {
        return null;
    }
}
