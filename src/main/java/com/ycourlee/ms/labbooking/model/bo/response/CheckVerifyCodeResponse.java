package com.ycourlee.ms.labbooking.model.bo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author yongjiang
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CheckVerifyCodeResponse {

    @ApiModelProperty("注册key")
    private String registerKey;
}
