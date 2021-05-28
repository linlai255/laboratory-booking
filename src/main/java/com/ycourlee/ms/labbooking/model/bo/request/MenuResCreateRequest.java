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
public class MenuResCreateRequest {

    @NotEmpty
    @ApiModelProperty("名")
    private String  name;
    @ApiModelProperty("页面路由")
    private String  route;
    @ApiModelProperty("父id")
    private Integer parentId;
    @ApiModelProperty("备注")
    private String  memo;
}
