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
public class RegisterRequest extends BaseDiffAccountModeRequest {

    @NotEmpty
    @ApiModelProperty("密码sha1")
    private String password;
    @NotEmpty
    @ApiModelProperty("注册key")
    private String registerKey;

    @ApiModelProperty(value = "注册的用户类型 1.管理员 2.教师", hidden = true)
    private Integer type;
    @ApiModelProperty(value = "手机号", hidden = true)
    private String  phone;
    @ApiModelProperty(value = "邮箱", hidden = true)
    private String  email;
}
