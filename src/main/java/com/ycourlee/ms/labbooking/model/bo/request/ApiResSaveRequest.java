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
public class ApiResSaveRequest {

    @NotEmpty
    @ApiModelProperty("名")
    private String  name;
    @NotEmpty
    @ApiModelProperty("uri")
    private String  path;
    @ApiModelProperty("是否包含路径变量 0.否 1.是")
    private Integer containPathVar;
    @ApiModelProperty("备注")
    private String  memo;
}
