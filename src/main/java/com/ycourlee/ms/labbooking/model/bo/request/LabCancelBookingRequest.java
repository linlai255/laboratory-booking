package com.ycourlee.ms.labbooking.model.bo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class LabCancelBookingRequest extends BaseCurrentUserRequest {

    @NotNull
    @ApiModelProperty("预定记录id")
    private Integer       bookingRecordId;
    @NotEmpty
    @ApiModelProperty("预定时间记录id")
    private List<Integer> idList;
}
