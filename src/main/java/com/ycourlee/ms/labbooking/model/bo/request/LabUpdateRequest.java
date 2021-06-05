package com.ycourlee.ms.labbooking.model.bo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class LabUpdateRequest extends BaseCurrentUserRequest {

    @NotNull
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;
}
