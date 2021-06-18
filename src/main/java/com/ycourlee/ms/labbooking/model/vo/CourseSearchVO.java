package com.ycourlee.ms.labbooking.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class CourseSearchVO {

    @ApiModelProperty(value = "")
    private Integer id;
    @ApiModelProperty(value = "课程名称")
    private String  name;
    @ApiModelProperty(value = "上课人数")
    private Integer studentAmount;
    @ApiModelProperty(value = "课时数")
    private Integer classHours;
    @ApiModelProperty(value = "备注")
    private String  memo;
    @ApiModelProperty(value = "更新时间")
    private String  updateTime;
    @ApiModelProperty(value = "更新人id")
    private Integer updateUserId;
    @ApiModelProperty(value = "更新人name")
    private String  updateUsername;
}
