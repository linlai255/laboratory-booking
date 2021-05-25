package com.ycourlee.ms.labbooking.model.bo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class LoginRequest {

    @NotEmpty
    @ApiModelProperty("用户名")
    private String  username;
    @NotEmpty
    @ApiModelProperty("密码sha1")
    private String  password;
    @ApiModelProperty("自动登录")
    private Boolean rememberMe;
}
