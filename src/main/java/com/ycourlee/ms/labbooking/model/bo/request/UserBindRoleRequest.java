package com.ycourlee.ms.labbooking.model.bo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class UserBindRoleRequest {

    @NotNull
    @ApiModelProperty("用户id")
    private Integer      userId;
    @NotEmpty
    @ApiModelProperty("绑定的角色id")
    private Set<Integer> roleIdSet;
}
