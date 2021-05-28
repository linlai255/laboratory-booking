package com.ycourlee.ms.labbooking.model.bo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class MenuBindResRequest {

    @NotNull
    @ApiModelProperty("菜单id")
    private Integer      menuId;
    @NotEmpty
    @ApiModelProperty("子资源id")
    private Set<Integer> subResIdSet;
}
