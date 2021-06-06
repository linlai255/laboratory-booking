package com.ycourlee.ms.labbooking.controller;

import com.ycourlee.ms.labbooking.config.properties.LabAppLoginProperties;
import com.ycourlee.ms.labbooking.config.properties.LabAuthProperties;
import com.ycourlee.ms.labbooking.enums.EDigit;
import com.ycourlee.ms.labbooking.exception.error.Errors;
import com.ycourlee.ms.labbooking.model.bo.request.LoginRequest;
import com.ycourlee.ms.labbooking.model.bo.request.RegisterRequest;
import com.ycourlee.ms.labbooking.model.bo.response.CheckVerifyCodeResponse;
import com.ycourlee.ms.labbooking.model.bo.response.LoginResponse;
import com.ycourlee.ms.labbooking.service.AccountService;
import com.ycourlee.ms.labbooking.service.LoginService;
import com.ycourlee.ms.labbooking.util.CookieUtil;
import com.ycourlee.root.core.context.BusinessException;
import com.ycourlee.root.core.domain.context.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yongjiang
 */
@Api
@RestController
@RequestMapping("/v1")
public class AccountController {

    @Autowired
    private AccountService        accountService;
    @Autowired
    @Qualifier("loginServicePhoneImpl")
    private LoginService          phoneLoginService;
    @Autowired
    @Qualifier("loginServiceEmailImpl")
    private LoginService          emailLoginService;
    @Autowired
    private LabAuthProperties     authProperties;
    @Autowired
    private LabAppLoginProperties loginProperties;

    @ApiOperation("获取验证码")
    @GetMapping("/verify-code/{account:.+}/{registerType:[1-2]}/{mode:[0-1]}")
    public ApiResponse<Boolean> verifyCode(@PathVariable("registerType") Integer registerType,
                                           @PathVariable("account") String account,
                                           @PathVariable(value = "mode", required = false) Integer mode) {
        if (EDigit.ONE.getCode().equals(mode)) {
            if (!loginProperties.isPhoneModeEnabled()) {
                throw new BusinessException(Errors.THE_LOGIN_REGISTRATION_MODE_DISABLED);
            }
            accountService.verifyCode(registerType, account);
        } else if (EDigit.ZERO.getCode().equals(mode)) {
            if (!loginProperties.isEmailModeEnabled()) {
                throw new BusinessException(Errors.THE_LOGIN_REGISTRATION_MODE_DISABLED);
            }
            accountService.verifyCodeByEmail(registerType, account);
        } else {
            throw new BusinessException(Errors.THE_LOGIN_REGISTRATION_MODE_DISABLED);
        }
        return ApiResponse.success(true);
    }

    @ApiOperation("校验验证码")
    @GetMapping("/check-verify-code/{account}/{verifyCode}")
    public ApiResponse<CheckVerifyCodeResponse> checkVerifyCode(@PathVariable("account") String account,
                                                                @PathVariable("verifyCode") String verifyCode) {
        return ApiResponse.success(accountService.checkVerifyCode(account, verifyCode));
    }

    @ApiOperation("注册")
    @PostMapping(value = {"/register", "sign-up"})
    public ApiResponse<Boolean> register(@Validated @RequestBody RegisterRequest request) {
        if (EDigit.ONE.getCode().equals(request.getMode())) {
            request.setPhone(request.getAccount());
        } else {
            request.setEmail(request.getAccount());
        }
        accountService.register(request);
        return ApiResponse.success(true);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Validated @RequestBody LoginRequest request,
                                            HttpServletRequest httpRequest,
                                            HttpServletResponse httpResponse) {
        LoginResponse data;
        if (EDigit.ONE.getCode().equals(request.getMode())) {
            if (!loginProperties.isPhoneModeEnabled()) {
                throw new BusinessException(Errors.THE_LOGIN_REGISTRATION_MODE_DISABLED);
            }
            data = phoneLoginService.login(request);
        } else if (EDigit.ZERO.getCode().equals(request.getMode())) {
            if (!loginProperties.isEmailModeEnabled()) {
                throw new BusinessException(Errors.THE_LOGIN_REGISTRATION_MODE_DISABLED);
            }
            data = emailLoginService.login(request);
        } else {
            throw new BusinessException(Errors.THE_LOGIN_REGISTRATION_MODE_DISABLED);
        }
        CookieUtil.addCookie(httpResponse, authProperties.getTokenKey(), data.getToken(), 0, httpRequest.getServerName());
        return ApiResponse.success(data);
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public ApiResponse<Object> logout(HttpServletRequest httpRequest,
                                      HttpServletResponse httpResponse) {
        accountService.logout(httpRequest.getHeader(authProperties.getTokenKey()));
        return ApiResponse.success(true);
    }
}
