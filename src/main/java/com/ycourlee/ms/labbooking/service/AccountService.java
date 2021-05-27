package com.ycourlee.ms.labbooking.service;

import com.ycourlee.ms.labbooking.model.bo.request.LoginRequest;
import com.ycourlee.ms.labbooking.model.bo.request.RegisterRequest;
import com.ycourlee.ms.labbooking.model.bo.response.CheckVerifyCodeResponse;
import com.ycourlee.ms.labbooking.model.bo.response.LoginResponse;

/**
 * @author yongjiang
 */
public interface AccountService {

    void verifyCode(Byte type, String phone);

    CheckVerifyCodeResponse checkVerifyCode(String phone, String verifyCode);

    void register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    Boolean checkAccount();
}
