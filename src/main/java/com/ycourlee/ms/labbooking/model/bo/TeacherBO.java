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
public class TeacherBO {

    private Integer id;
    private String name;
    private String nickname;
    private String department;
    private String academy;
}
