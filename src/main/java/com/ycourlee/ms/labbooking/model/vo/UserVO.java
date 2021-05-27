package com.ycourlee.ms.labbooking.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class UserVO {

    private Integer id;
    private String username;
    private String email;
    private String wechat;
    private Byte   refId;
}
