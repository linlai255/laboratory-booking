package com.ycourlee.ms.labbooking.model.bo.request;

import com.ycourlee.ms.labbooking.auth.Context;
import com.ycourlee.ms.labbooking.util.BizAssert;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

/**
 * @author yongjiang
 */
@ToString
public class BaseCurrentUserRequest {

    /**
     * uid
     */
    @ApiModelProperty(hidden = true)
    private Integer userId;
    /**
     * uid
     */
    @ApiModelProperty(hidden = true)
    private Integer refId;
    /**
     * 用户名，不是真实姓名
     */
    @ApiModelProperty(hidden = true)
    private String  username;
    /**
     * 账户的注册类型
     */
    @ApiModelProperty(hidden = true)
    private Integer registerType;
    @ApiModelProperty(hidden = true)
    private boolean blank = true;

    public BaseCurrentUserRequest fillCurrentUser() {
        BizAssert.notNull(Context.getUserId());
        BizAssert.notNull(Context.getRefId());
        BizAssert.notNull(Context.getUsername());
        BizAssert.notNull(Context.getType());
        this.userId = Context.getUserId();
        this.refId = Context.getRefId();
        this.username = Context.getUsername();
        this.registerType = Context.getType();
        this.blank = false;
        return this;
    }

    public Integer getUserId() {
        BizAssert.impossible(blank);
        return userId;
    }

    public Integer getRefId() {
        BizAssert.impossible(blank);
        return refId;
    }

    public String getUsername() {
        BizAssert.impossible(blank);
        return username;
    }

    public Integer getRegisterType() {
        BizAssert.impossible(blank);
        return registerType;
    }
}
