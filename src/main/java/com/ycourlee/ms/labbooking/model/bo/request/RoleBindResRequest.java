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
public class RoleBindResRequest {

    @NotNull
    @ApiModelProperty("角色id")
    private Integer      roleId;
    @NotEmpty
    @ApiModelProperty("资源id列表")
    private Set<Integer> resIdSet;
}
