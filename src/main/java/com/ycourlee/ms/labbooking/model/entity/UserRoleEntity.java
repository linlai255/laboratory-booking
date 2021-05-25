package com.ycourlee.ms.labbooking.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * RBAC 用户角色表
 *
 * @author yongjiang
 */
@ApiModel(value = "com-ycourlee-ms-labbooking-entity-UserRole")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleEntity {

    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "")
    private Integer userId;

    @ApiModelProperty(value = "")
    private Integer roleId;

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
    private Boolean isDelete;
}