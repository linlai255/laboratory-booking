package com.ycourlee.ms.labbooking.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * 预约记录表
 *
 * @author yongjiang
 */
@ApiModel(value = "com-ycourlee-ms-labbooking-model-entity-BookingRecord")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookingRecordEntity {

    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 实验室id
     */
    @ApiModelProperty(value = "实验室id")
    private Integer labId;

    /**
     * 实验课程id
     */
    @ApiModelProperty(value = "实验课程id")
    private Integer courseId;

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
    private String createUsername;

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
    private String updateUsername;

    /**
     * 已删除
     */
    @ApiModelProperty(value = "已删除")
    private Integer isDelete;
}