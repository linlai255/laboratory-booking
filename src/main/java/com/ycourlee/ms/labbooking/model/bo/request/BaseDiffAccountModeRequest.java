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
public class BaseDiffAccountModeRequest {

    @NotEmpty
    @ApiModelProperty("账户（手机号或密码）")
    private String  account;
    @ApiModelProperty("登录模式 0.邮箱 1.手机号 默认0")
    private Integer mode;
}
