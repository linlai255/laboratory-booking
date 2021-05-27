package com.ycourlee.ms.labbooking.model.bo.response;

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
public class LoginResponse {

    @ApiModelProperty("凭证")
    private String token;
    @ApiModelProperty("账户类型")
    private Byte   type;
}
