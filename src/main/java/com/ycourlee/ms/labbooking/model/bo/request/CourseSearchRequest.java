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
public class CourseSearchRequest extends BasicPageRequest {

    @ApiModelProperty("课程名称")
    private String name;
}
