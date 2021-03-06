package com.ycourlee.ms.labbooking.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * RBAC 角色资源表
 *
 * @author yongjiang
 */
@ApiModel(value = "com-ycourlee-ms-labbooking-model-entity-RoleResource")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleResourceEntity {

    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    /**
     * 资源id
     */
    @ApiModelProperty(value = "资源id")
    private Integer resourceId;

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