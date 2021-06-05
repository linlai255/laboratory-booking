package com.ycourlee.ms.labbooking.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * 用户之教师
 *
 * @author yongjiang
 */
@ApiModel(value = "com-ycourlee-ms-labbooking-model-entity-Teacher")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeacherEntity {

    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 学院
     */
    @ApiModelProperty(value = "学院")
    private String academy;

    /**
     * 系
     */
    @ApiModelProperty(value = "系")
    private String department;

    /**
     * 注册时间
     */
    @ApiModelProperty(value = "注册时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 已删除
     */
    @ApiModelProperty(value = "已删除")
    private Integer isDelete;
}