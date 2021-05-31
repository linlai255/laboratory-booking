package com.ycourlee.ms.labbooking.model.bo.request;

import com.ycourlee.ms.labbooking.auth.Context;
import com.ycourlee.ms.labbooking.util.BizAssert;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class BaseCurrentUserRequest {

    private Integer userId;
    private String  username;
    private Integer registerType;

    public void fillCurrentUser() {
        BizAssert.notNull(Context.getUserId());
        BizAssert.notNull(Context.getUsername());
        BizAssert.notNull(Context.getType());
        this.userId = Context.getUserId();
        this.username = Context.getUsername();
        this.registerType = Context.getType().intValue();
    }
}
