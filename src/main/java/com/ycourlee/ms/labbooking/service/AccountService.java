package com.ycourlee.ms.labbooking.service;

import com.ycourlee.ms.labbooking.model.bo.request.LoginRequest;

/**
 * @author yongjiang
 */
public interface AccountService {

    void verifyCode(String phone);

    void login(LoginRequest request);

    Boolean checkAccount();
}
