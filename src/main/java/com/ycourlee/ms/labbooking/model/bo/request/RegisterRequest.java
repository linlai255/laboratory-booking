package com.ycourlee.ms.labbooking.model.bo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class RegisterRequest {

    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("验证码")
    private String verifyCode;
}
