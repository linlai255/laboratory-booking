package com.ycourlee.ms.labbooking.service.impl;

import com.ycourlee.ms.labbooking.manager.AccountManager;
import com.ycourlee.ms.labbooking.model.bo.request.LoginRequest;
import com.ycourlee.ms.labbooking.model.bo.response.LoginResponse;
import com.ycourlee.ms.labbooking.model.entity.UserEntity;
import com.ycourlee.ms.labbooking.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yongjiang
 */
@Service("loginServiceEmailImpl")
public class LoginServiceEmailImpl implements LoginService {

    @Autowired
    private AccountManager accountManager;

    @Override
    public LoginResponse login(LoginRequest request) {
        UserEntity userEntity = accountManager.verifyAccountAndPasswordByEmail(request.getRegisterType(), request.getPassword(), request.getAccount());
        return accountManager.buildLoginResponse(userEntity, request.getRememberMe());
    }
}
