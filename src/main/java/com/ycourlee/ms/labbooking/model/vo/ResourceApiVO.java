package com.ycourlee.ms.labbooking.model.vo;

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
public class ResourceApiVO {

    @ApiModelProperty("id")
    private Integer    id;
    @ApiModelProperty("API名")
    private String     name;
    @ApiModelProperty("API path")
    private String     path;
    @ApiModelProperty("是否包含路径变量 0否 1是")
    private CodeNameVO containPathVar;
    @ApiModelProperty("父id")
    private Integer    parentId;
    @ApiModelProperty("备注")
    private String     memo;
}
