package com.ycourlee.ms.labbooking.model.bo.response;

import com.ycourlee.ms.labbooking.model.vo.CodeNameVO;
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
public class LabDetailResponse {

    @ApiModelProperty(value = "")
    private Integer    id;
    @ApiModelProperty(value = "实验室名称")
    private String     name;
    @ApiModelProperty(value = "最大容纳人数")
    private Integer    maxCapacity;
    @ApiModelProperty(value = "仪器数量")
    private Integer    instrumentAmount;
    @ApiModelProperty(value = "仪器备注")
    private String     instrumentMemo;
    @ApiModelProperty(value = "是否可用")
    private CodeNameVO status;
    @ApiModelProperty(value = "实验室位置")
    private String     location;
    @ApiModelProperty(value = "开放时间 24小时制")
    private String     openTime;
    @ApiModelProperty(value = "关闭时间 24小时制")
    private String     closeTime;
    @ApiModelProperty(value = "备注")
    private String     memo;
    @ApiModelProperty(value = "更新时间")
    private Date       updateTime;
    @ApiModelProperty(value = "更新人id")
    private Integer    updateUserId;
    @ApiModelProperty(value = "更新人name")
    private String     updateUsername;
}