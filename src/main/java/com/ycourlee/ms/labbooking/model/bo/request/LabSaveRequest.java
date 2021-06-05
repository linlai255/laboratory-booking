package com.ycourlee.ms.labbooking.model.bo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class LabSaveRequest extends BaseCurrentUserRequest {

    @NotEmpty
    @ApiModelProperty("实验室名称")
    private String  name;
    @NotNull
    @ApiModelProperty("实验室最大容纳学生数")
    private Integer maxCapacity;
    @ApiModelProperty(value = "开放时间", example = "06:30")
    private String  openTime;
    @ApiModelProperty(value = "闭室时间", example = "17:30")
    private String  closeTime;
    @ApiModelProperty("仪器数量")
    private Integer instrumentAmount;
    @ApiModelProperty("仪器描述")
    private String  instrumentMemo;
    @ApiModelProperty("实验室注意事项、描述")
    private String  memo;
    @ApiModelProperty("实验室位置描述")
    private String  location;
}
