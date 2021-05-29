package com.ycourlee.ms.labbooking.controller;

import com.ycourlee.ms.labbooking.config.properties.LabAuthProperties;
import com.ycourlee.ms.labbooking.model.bo.request.LoginRequest;
import com.ycourlee.ms.labbooking.model.bo.request.RegisterRequest;
import com.ycourlee.ms.labbooking.model.bo.response.CheckVerifyCodeResponse;
import com.ycourlee.ms.labbooking.model.bo.response.LoginResponse;
import com.ycourlee.ms.labbooking.service.AccountService;
import com.ycourlee.ms.labbooking.util.CookieUtil;
import com.ycourlee.root.core.domain.context.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yongjiang
 */
@RestController
@RequestMapping("/v1")
public class AccountController {

    @Autowired
    private AccountService    accountService;
    @Autowired
    private LabAuthProperties properties;

    @GetMapping("/verify-code/{type:[1-2]}/{phone:[0-9]{11}}")
    public ApiResponse<Boolean> verifyCode(@PathVariable("type") Byte type,
                                           @PathVariable("phone") String phone) {
        accountService.verifyCode(type, phone);
        return ApiResponse.success(true);
    }

    @GetMapping("/check-verify-code/{phone}/{verifyCode}")
    public ApiResponse<CheckVerifyCodeResponse> checkVerifyCode(@PathVariable("phone") String phone,
                                                                @PathVariable("verifyCode") String verifyCode) {
        return ApiResponse.success(accountService.checkVerifyCode(phone, verifyCode));
    }

    @PostMapping(value = {"/register", "sign-up"})
    public ApiResponse<Boolean> register(@Validated @RequestBody RegisterRequest request) {
        accountService.register(request);
        return ApiResponse.success(true);
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Validated @RequestBody LoginRequest request, HttpServletRequest httpRequest,  HttpServletResponse httpResponse) {
        LoginResponse data = accountService.login(request);
        CookieUtil.addCookie(httpResponse, properties.getTokenKey(), data.getToken(), 0, httpRequest.getServerName());
        return ApiResponse.success(data);
    }
}
