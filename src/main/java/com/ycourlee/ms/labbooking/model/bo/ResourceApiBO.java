package com.ycourlee.ms.labbooking.model.bo;

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
public class ResourceApiBO {

    private Integer id;
    private String  name;
    @ApiModelProperty("uri")
    private String  path;
    @ApiModelProperty("是否包含路径变量 0.否 1.是")
    private Integer containPathVar;
    @ApiModelProperty("父id")
    private Integer parentId;
    @ApiModelProperty("备注")
    private String  memo;
}
