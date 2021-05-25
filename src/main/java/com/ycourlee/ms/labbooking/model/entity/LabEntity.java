package com.ycourlee.ms.labbooking.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * 实验室表
 *
 * @author yongjiang
 */
@ApiModel(value = "com-ycourlee-ms-labbooking-entity-Lab")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LabEntity {

    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 实验室名称
     */
    @ApiModelProperty(value = "实验室名称")
    private String name;

    /**
     * 最大容纳人数
     */
    @ApiModelProperty(value = "最大容纳人数")
    private Integer maxCapacity;

    /**
     * 仪器数量
     */
    @ApiModelProperty(value = "仪器数量")
    private Integer instrumentAmount;

    /**
     * 仪器备注
     */
    @ApiModelProperty(value = "仪器备注")
    private Integer instrumentMemo;

    /**
     * 是否空闲
     */
    @ApiModelProperty(value = "是否空闲")
    private Boolean isIdle;

    /**
     * 是否可用
     */
    @ApiModelProperty(value = "是否可用")
    private Boolean isAvailable;

    /**
     * 实验室位置
     */
    @ApiModelProperty(value = "实验室位置")
    private String location;

    /**
     * 开放时间 24小时制
     */
    @ApiModelProperty(value = "开放时间 24小时制")
    private String openTime;

    /**
     * 关闭时间 24小时制
     */
    @ApiModelProperty(value = "关闭时间 24小时制")
    private String closeTime;

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
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    private Integer createUserId;

    /**
     * 创建人name
     */
    @ApiModelProperty(value = "创建人name")
    private Integer createUsername;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 更新人id
     */
    @ApiModelProperty(value = "更新人id")
    private Integer updateUserId;

    /**
     * 更新人name
     */
    @ApiModelProperty(value = "更新人name")
    private Integer updateUsername;

    /**
     * 已删除
     */
    @ApiModelProperty(value = "已删除")
    private Boolean isDelete;
}