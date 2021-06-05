package com.ycourlee.ms.labbooking.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author yongjiang
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CodeNameVO {

    @ApiModelProperty("码")
    private Integer code;
    @ApiModelProperty("名称")
    private String  name;
}
