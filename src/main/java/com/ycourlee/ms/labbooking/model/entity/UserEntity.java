package com.ycourlee.ms.labbooking.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * @author yongjiang
 */

/**
 * RBAC 用户表
 */
@ApiModel(value = "com-ycourlee-ms-labbooking-model-entity-UserEntity")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 登录账户名
     */
    @ApiModelProperty(value = "登录账户名")
    private String username;

    /**
     * 登录密码 SHA1
     */
    @ApiModelProperty(value = "登录密码 SHA1")
    private String password;

    /**
     * 绑定的邮箱
     */
    @ApiModelProperty(value = "绑定的邮箱")
    private String email;

    /**
     * 绑定的微信
     */
    @ApiModelProperty(value = "绑定的微信")
    private String wechat;

    /**
     * 绑定的手机号
     */
    @ApiModelProperty(value = "绑定的手机号")
    private String phone;

    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型")
    private Byte type;

    /**
     * 用户详细id
     */
    @ApiModelProperty(value = "用户详细id")
    private Integer refId;

    /**
     * 注册时间
     */
    @ApiModelProperty(value = "注册时间")
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