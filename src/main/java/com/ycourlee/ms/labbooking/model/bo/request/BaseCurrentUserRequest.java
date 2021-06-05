package com.ycourlee.ms.labbooking.model.bo.request;

import com.ycourlee.ms.labbooking.auth.Context;
import com.ycourlee.ms.labbooking.util.BizAssert;
import lombok.ToString;

/**
 * @author yongjiang
 */
@ToString
public class BaseCurrentUserRequest {

    /**
     * uid
     */
    private Integer userId;
    /**
     * 用户名，不是真实姓名
     */
    private String  username;
    /**
     * 账户的注册类型
     */
    private Integer registerType;
    private boolean blank = true;

    public void fillCurrentUser() {
        BizAssert.notNull(Context.getUserId());
        BizAssert.notNull(Context.getUsername());
        BizAssert.notNull(Context.getType());
        this.userId = Context.getUserId();
        this.username = Context.getUsername();
        this.registerType = Context.getType();
        this.blank = false;
    }

    public Integer getUserId() {
        BizAssert.impossible(blank);
        return userId;
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
