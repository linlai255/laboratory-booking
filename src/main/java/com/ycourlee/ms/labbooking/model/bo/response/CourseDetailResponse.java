package com.ycourlee.ms.labbooking.model.bo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class CourseDetailResponse {

    @ApiModelProperty(value = "")
    private Integer id;
    @ApiModelProperty(value = "课程名称")
    private String  name;
    @ApiModelProperty(value = "上课教师id")
    private Integer teacherId;
    @ApiModelProperty(value = "上课教师name")
    private String  teacherName;
    @ApiModelProperty(value = "上课人数")
    private Integer studentAmount;
    @ApiModelProperty(value = "课时数")
    private Integer classHours;
    @ApiModelProperty(value = "备注")
    private String  memo;
    @ApiModelProperty(value = "更新时间")
    private Date    updateTime;
    @ApiModelProperty(value = "更新人id")
    private Integer updateUserId;
    @ApiModelProperty(value = "更新人name")
    private String  updateUsername;
}
