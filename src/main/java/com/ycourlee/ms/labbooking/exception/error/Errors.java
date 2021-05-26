package com.ycourlee.ms.labbooking.exception.error;

import com.ycourlee.root.core.context.CmReturn;

/**
 * @author yongjiang
 */
public enum Errors implements CmReturn {

    /**
     * 通用
     */
    UNKNOWN(-1, "不好意思, 出错了"),
    CANNOT_AUTHENTICATE(10001, "无法认证"),
    OPERATION_TOO_FAST(10002, "操作太快了"),
    INTERNAL_CONFIGURATION_ERROR(10003, "内部配置错误"),

    /**
     * 账号相关
     */
    SMS_SEND_FAILED(10020, "短信发送失败"),
    YOU_ARE_NOT_ADMIN(10021, "您还不是管理员, 无法注册"),
    PHONE_NUMBER_ALREADY_EXISTS(10022, "手机号已被注册"),
    PLEASE_NOT_HACK_REGISTRATION(10023, "请不要非法地注册账号"),
    ;

    private final int    code;
    private final String msg;

    Errors(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
