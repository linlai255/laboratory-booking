package com.ycourlee.ms.labbooking.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * 实验课程表
 *
 * @author yongjiang
 */
@ApiModel(value = "com-ycourlee-ms-labbooking-entity-Course")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    private String name;

    /**
     * 上课教师id
     */
    @ApiModelProperty(value = "上课教师id")
    private Integer teacherId;

    /**
     * 上课教师name
     */
    @ApiModelProperty(value = "上课教师name")
    private Integer teacherName;

    /**
     * 上课人数
     */
    @ApiModelProperty(value = "上课人数")
    private Integer studentAmount;

    /**
     * 课时数
     */
    @ApiModelProperty(value = "课时数")
    private Integer classHours;

    /**
     * 未预约实验室
     */
    @ApiModelProperty(value = "未预约实验室")
    private Integer noBooking;

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
    private String createUserId;

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
    private String updateUsername;

    /**
     * 已删除
     */
    @ApiModelProperty(value = "已删除")
    private Integer isDelete;
}