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
public class UserBO {

    private Integer id;
    private String  username;
    private String  email;
    private String  wechat;
    private Integer refId;
}
