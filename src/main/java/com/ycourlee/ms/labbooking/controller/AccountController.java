package com.ycourlee.ms.labbooking.controller;

import com.ycourlee.ms.labbooking.model.bo.request.LoginRequest;
import com.ycourlee.ms.labbooking.model.bo.request.RegisterRequest;
import com.ycourlee.ms.labbooking.service.AccountService;
import com.ycourlee.root.core.domain.context.Rtm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yongjiang
 */
@RestController
@RequestMapping("/v1")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/verify-code/{phone:[0-9]}")
    public Rtm verifyCode(@PathVariable("phone") String phone) {
        accountService.verifyCode(phone);
        return Rtm.success();
    }

    @PostMapping(value = {"/register", "sign-up"})
    public Rtm register(RegisterRequest request) {
        return Rtm.success();
    }

    @PostMapping("/login")
    public Rtm login(@Validated @RequestBody LoginRequest request) {
        accountService.login(request);
        return Rtm.success();
    }
}
