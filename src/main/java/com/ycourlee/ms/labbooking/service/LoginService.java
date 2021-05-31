package com.ycourlee.ms.labbooking.service;

import com.ycourlee.ms.labbooking.model.bo.request.LoginRequest;
import com.ycourlee.ms.labbooking.model.bo.response.LoginResponse;

/**
 * @author yongjiang
 */
public interface LoginService {

    LoginResponse login(LoginRequest request);
}
