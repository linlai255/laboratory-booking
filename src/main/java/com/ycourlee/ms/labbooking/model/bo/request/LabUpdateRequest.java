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
public class LabUpdateRequest extends BaseCurrentUserRequest {

    @NotNull
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("名称")
    private String  name;
    @ApiModelProperty(value = "最大容纳人数")
    private Integer maxCapacity;
    @ApiModelProperty(value = "仪器数量")
    private Integer instrumentAmount;
    @ApiModelProperty(value = "仪器备注")
    private String  instrumentMemo;
    @ApiModelProperty(value = "实验室位置")
    private String  location;
    @ApiModelProperty(value = "开放时间 24小时制 02:00")
    private String  openTime;
    @ApiModelProperty(value = "关闭时间 24小时制 19:00")
    private String  closeTime;
    @ApiModelProperty(value = "备注")
    private String  memo;
}
