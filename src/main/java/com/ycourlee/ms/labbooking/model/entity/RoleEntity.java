package com.ycourlee.ms.labbooking.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * RBAC 角色表
 *
 * @author yongjiang
 */
@ApiModel(value = "com-ycourlee-ms-labbooking-entity-Role")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {

    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

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