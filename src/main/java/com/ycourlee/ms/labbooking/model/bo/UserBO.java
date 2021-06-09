package com.ycourlee.ms.labbooking.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class UserBO {

    private Integer id;
    private String  username;
    private String  name;
    private String  nickname;
    private String  phone;
    private String  email;
    private String  wechat;
    private Integer refId;
    private Integer type;

    private String       academy;
    private String       department;
    private List<RoleBO> roleList;
}
