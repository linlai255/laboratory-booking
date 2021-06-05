package com.ycourlee.ms.labbooking.model.bo.request;

import com.ycourlee.root.core.dto.BasicPageRequest;
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
public class LabSearchRequest extends BasicPageRequest {

    @ApiModelProperty("名称, 支持全模糊")
    private String name;
}
