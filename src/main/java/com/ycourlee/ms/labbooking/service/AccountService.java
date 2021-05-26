package com.ycourlee.ms.labbooking.service;

import com.ycourlee.ms.labbooking.model.bo.request.LoginRequest;
import com.ycourlee.ms.labbooking.model.bo.request.RegisterRequest;
import com.ycourlee.ms.labbooking.model.bo.response.CheckVerifyCodeResponse;

/**
 * @author yongjiang
 */
public interface AccountService {

    void verifyCode(Integer type, String phone);

    CheckVerifyCodeResponse checkVerifyCode(String phone, String verifyCode);

    void register(RegisterRequest request);

    void login(LoginRequest request);

    Boolean checkAccount();
}
