package com.ycourlee.ms.labbooking.model.bo.request;

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
public class RoleUpdateRequest {

    @NotNull
    @ApiModelProperty("角色id")
    private Integer id;
    @NotEmpty
    @ApiModelProperty("角色name")
    private String  name;
}
