package com.ycourlee.ms.labbooking.service;

import com.ycourlee.ms.labbooking.model.bo.request.RegisterRequest;
import com.ycourlee.ms.labbooking.model.bo.response.CheckVerifyCodeResponse;

/**
 * @author yongjiang
 */
public interface AccountService {

    void verifyCode(Integer type, String phone);

    void verifyCodeByEmail(Integer type, String email);

    CheckVerifyCodeResponse checkVerifyCode(String account, String verifyCode);

    void register(RegisterRequest request);

    void logout(String token);
}
