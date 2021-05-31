package com.ycourlee.ms.labbooking.model.bo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @author yongjiang
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    @ApiModelProperty("用户名")
    private String       username;
    @ApiModelProperty("姓名")
    private String       name;
    @ApiModelProperty("昵称")
    private String       nickname;
    @ApiModelProperty("凭证")
    private String       token;
    @ApiModelProperty("账户类型")
    private Byte         type;
    @ApiModelProperty("拥有的角色")
    private List<String> roles;
}
