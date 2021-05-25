package com.ycourlee.ms.labbooking.service.impl;

import com.ycourlee.ms.labbooking.autoconfig.AliyunDysmsProperties;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.manager.AccountManager;
import com.ycourlee.ms.labbooking.manager.AliyunDysms;
import com.ycourlee.ms.labbooking.manager.Redis;
import com.ycourlee.ms.labbooking.model.bo.request.LoginRequest;
import com.ycourlee.ms.labbooking.service.AccountService;
import com.ycourlee.ms.labbooking.util.KeyPool;
import com.ycourlee.root.core.context.BusinessException;
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
    private AliyunDysms           aliyunDysms;
    @Autowired
    private AliyunDysmsProperties properties;
    @Autowired
    private Redis                 redis;

    @Override
    public void verifyCode(String phone) {
        if (accountManager.haveAliveCodeCurrentPhone(phone)) {
            throw new BusinessException(Errors.OPERATION_TOO_FAST);
        }
        String code = aliyunDysms.sendCacheVerifyCodeWhenRegister(phone);
        redis.setEx(KeyPool.verifyCode(phone), code, properties.getVerifyCodeAliveTimeout(), TimeUnit.SECONDS);
    }

    @Override
    public void login(LoginRequest request) {

    }

    @Override
    public Boolean checkAccount() {
        return null;
    }
}
