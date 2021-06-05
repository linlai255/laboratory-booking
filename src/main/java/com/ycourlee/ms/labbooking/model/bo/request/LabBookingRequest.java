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
public class LabBookingRequest extends BaseCurrentUserRequest {

    @NotNull
    @ApiModelProperty("要预约的实验室id")
    private Integer labId;
    @NotNull
    @ApiModelProperty("课程id")
    private Integer courseId;

    @NotNull
    @ApiModelProperty("起始周次")
    private Integer beginWeekNo;
    @NotNull
    @ApiModelProperty("结束周次，不小于起始周次")
    private Integer endWeekNo;
    @NotNull
    @ApiModelProperty("起始节次")
    private Integer beginSectionNo;
    @NotNull
    @ApiModelProperty("结束节次，不小于起始节次")
    private Integer endSectionNo;
}
