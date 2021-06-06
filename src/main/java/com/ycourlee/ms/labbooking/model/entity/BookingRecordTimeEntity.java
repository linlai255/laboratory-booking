package com.ycourlee.ms.labbooking.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * 预约记录所选时间表
 *
 * @author yongjiang
 */
@ApiModel(value = "com-ycourlee-ms-labbooking-model-entity-BookingRecordTime")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookingRecordTimeEntity {

    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 预约记录id
     */
    @ApiModelProperty(value = "预约记录id")
    private Integer bookingRecordId;

    /**
     * 周编号 1-18
     */
    @ApiModelProperty(value = "周编号 1-18")
    private Integer weekNo;

    /**
     * 周几 1-7
     */
    @ApiModelProperty(value = "周几 1-7")
    private Integer dayNo;

    /**
     * 节编号 1-5
     */
    @ApiModelProperty(value = "节编号 1-5")
    private Integer sectionNo;

    /**
     * 1.等待上课 2.已下课 3.被取消
     */
    @ApiModelProperty(value = "1.等待上课 2.已下课 3.被取消")
    private Integer status;

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