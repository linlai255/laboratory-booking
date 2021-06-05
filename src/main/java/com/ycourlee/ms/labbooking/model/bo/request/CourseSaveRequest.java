package com.ycourlee.ms.labbooking.model.bo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class CourseSaveRequest extends BaseCurrentUserRequest {

    @NotEmpty
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
