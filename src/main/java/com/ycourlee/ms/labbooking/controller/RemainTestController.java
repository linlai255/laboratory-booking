package com.ycourlee.ms.labbooking.controller;

import com.ycourlee.root.core.domain.context.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author yongjiang
 */
@ApiIgnore
@RestController
@SuppressWarnings("rawtypes")
public class RemainTestController {

    @PostMapping("/test")
    public ApiResponse test() {
        return ApiResponse.success();
    }

    @PostMapping("/test2/{name}")
    public ApiResponse test2(@PathVariable String name) {
        return ApiResponse.success();
    }
}
