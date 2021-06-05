package com.ycourlee.ms.labbooking.model.vo;

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
public class LabSearchVO {

    @ApiModelProperty(value = "")
    private Integer    id;
    @ApiModelProperty(value = "实验室名称")
    private String     name;
    @ApiModelProperty(value = "最大容纳人数")
    private Integer    maxCapacity;
    @ApiModelProperty(value = "仪器数量")
    private Integer    instrumentAmount;
    @ApiModelProperty(value = "是否可用")
    private CodeNameVO status;
    @ApiModelProperty(value = "实验室位置")
    private String     location;
    @ApiModelProperty(value = "更新时间")
    private Date       updateTime;
    @ApiModelProperty(value = "更新人name")
    private String     updateUsername;
}
