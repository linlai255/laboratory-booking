package com.ycourlee.ms.labbooking.model.bo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class CourseUpdateRequest {

    @NotNull
    @ApiModelProperty("课程id")
    private Integer id;
    @ApiModelProperty("课程名称")
    private String  name;
    @ApiModelProperty("教师id")
    private Integer teacherId;
    @ApiModelProperty("学生数量")
    private Integer studentAmount;
    @ApiModelProperty("课时数")
    private Integer classHours;
    @ApiModelProperty("未预约实验室")
    private Integer noBooking;
    @ApiModelProperty("备注")
    private String  memo;
}
