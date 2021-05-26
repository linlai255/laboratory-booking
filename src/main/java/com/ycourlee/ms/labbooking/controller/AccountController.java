package com.ycourlee.ms.labbooking.controller;

import com.ycourlee.ms.labbooking.model.bo.request.LoginRequest;
import com.ycourlee.ms.labbooking.model.bo.request.RegisterRequest;
import com.ycourlee.ms.labbooking.model.bo.response.CheckVerifyCodeResponse;
import com.ycourlee.ms.labbooking.service.AccountService;
import com.ycourlee.root.core.domain.context.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yongjiang
 */
@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/verify-code/{type:[1-2]]}/{phone:[0-9]{11}}")
    public ApiResponse verifyCode(@PathVariable("type") Integer type,
                                  @PathVariable("phone") String phone) {
        accountService.verifyCode(type, phone);
        return ApiResponse.success();
    }

    @GetMapping("/check-verify-code/{phone}/{verifyCode}")
    public ApiResponse<CheckVerifyCodeResponse> checkVerifyCode(@PathVariable("phone") String phone,
                                                                @PathVariable("verifyCode") String verifyCode) {
        return ApiResponse.success(accountService.checkVerifyCode(phone, verifyCode));
    }

    @PostMapping(value = {"/register", "sign-up"})
    public ApiResponse register(@Validated @RequestBody RegisterRequest request) {
        accountService.register(request);
        return ApiResponse.success();
    }

    @PostMapping("/login")
    public ApiResponse login(@Validated @RequestBody LoginRequest request) {
        accountService.login(request);
        return ApiResponse.success();
    }
}
