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
public class ApiResUpdateRequest {

    @NotNull
    @ApiModelProperty("API id")
    private Integer id;
    @ApiModelProperty("名")
    private String  name;
    @NotEmpty
    @ApiModelProperty("uri")
    private String  path;
    @ApiModelProperty("是否包含路径变量 0.否 1.是")
    private Integer containPathVar;
    @ApiModelProperty("父id")
    private Integer parentId;
    @ApiModelProperty("备注")
    private String  memo;
}
