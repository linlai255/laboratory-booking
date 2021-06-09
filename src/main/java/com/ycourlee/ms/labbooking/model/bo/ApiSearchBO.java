package com.ycourlee.ms.labbooking.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class ApiSearchBO {

    private String  name;
    private String  path;
    private Integer containPathVar;
    private String  method;
}
