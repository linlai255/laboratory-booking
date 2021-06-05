package com.ycourlee.ms.labbooking.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * RBAC 资源表
 *
 * @author yongjiang
 */
@ApiModel(value = "com-ycourlee-ms-labbooking-model-entity-Resource")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResourceEntity {

    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 类型 1.菜单 2.API
     */
    @ApiModelProperty(value = "类型 1.菜单 2.API")
    private Integer type;

    /**
     * 资源名
     */
    @ApiModelProperty(value = "资源名")
    private String name;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    private Integer parentId;

    /**
     * page route.
     */
    @ApiModelProperty(value = "page route.")
    private String route;

    /**
     * API path.
     */
    @ApiModelProperty(value = "API path.")
    private String path;

    /**
     * 包含path variable.
     */
    @ApiModelProperty(value = "包含path variable.")
    private Integer containPathVar;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String memo;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 已删除
     */
    @ApiModelProperty(value = "已删除")
    private Integer isDelete;
}