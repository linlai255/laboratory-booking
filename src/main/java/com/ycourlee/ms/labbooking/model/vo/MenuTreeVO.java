package com.ycourlee.ms.labbooking.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class MenuTreeVO {

    @ApiModelProperty("id")
    private Integer          id;
    @ApiModelProperty("名")
    private String           name;
    @ApiModelProperty("路由")
    private String           route;
    @ApiModelProperty("备注")
    private String           memo;
    @ApiModelProperty("父id")
    private Integer          parentId;
    @ApiModelProperty("子菜单")
    private List<MenuTreeVO> children;
}
