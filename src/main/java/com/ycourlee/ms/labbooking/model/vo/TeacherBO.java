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
public class TeacherBO {

    private Integer id;
    private String name;
    private String nickname;
    private String academy;
    private String department;
}
