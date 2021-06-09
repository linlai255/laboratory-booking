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
public class ApiSearchRequest extends BasicPageRequest {

    @ApiModelProperty("API名 全模糊")
    private String  name;
    @ApiModelProperty("API path 左模糊")
    private String  path;
    @ApiModelProperty("包含路径变量 0不含 1含 默认0")
    private Integer containPathVar;
    @ApiModelProperty("0全部 1get 2post 3put 4delete 5option")
    private Integer method;
}
