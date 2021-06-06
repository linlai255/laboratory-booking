package com.ycourlee.ms.labbooking.model.bo.request;

import com.ycourlee.root.core.dto.BasicPageRequest;
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
public class BookingRecordRequest extends BasicPageRequest {

    @NotNull
    @ApiModelProperty("课程id")
    private Integer courseId;
}
