package com.ycourlee.ms.labbooking.model.bo.request;

import com.ycourlee.ms.labbooking.enums.EAccountType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class LoginRequest {

    @NotEmpty
    @ApiModelProperty("手机号")
    private String  phone;
    @NotEmpty
    @ApiModelProperty("密码sha1")
    private String  password;
    /**
     * {@link EAccountType}
     */
    @NotNull
    @ApiModelProperty("账户类型 1.管理员 2.教师")
    private Byte    type;
    @ApiModelProperty("自动登录")
    private Boolean rememberMe;
}
