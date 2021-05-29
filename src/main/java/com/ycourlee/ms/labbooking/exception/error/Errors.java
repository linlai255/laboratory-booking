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
    OPERATION_TOO_FAST(10000, "操作太快了"),
    INTERNAL_CONFIGURATION_ERROR(10001, "内部配置错误"),
    INTERNAL_DATA_ERROR(10002, "内部数据错误"),


    /**
     * 认证和鉴权
     */
    CANNOT_AUTHENTICATE(11000, "无法认证"),
    TOKEN_NOT_FOUND(11001, "找不到token"),
    UNAVAILABLE_TOKEN(11002, "token不可用"),
    TOKEN_EXPIRED(11003, "token已过期"),
    CANNOT_AUTHORIZATION(11004, "无法鉴权"),
    UNKNOWN_ACCOUNT_TYPE(11005, "未知账户类型"),
    AUTHORIZATION_FAILED(11006, "鉴权失败"),
    UNKNOWN_USER(11007, "未知用户"),

    /**
     * 账号相关
     */
    SMS_SEND_FAILED(10020, "短信发送失败"),
    YOU_ARE_NOT_ADMIN(10021, "您还不是管理员, 无法注册"),
    PHONE_NUMBER_ALREADY_EXISTS(10022, "手机号已被注册"),
    PLEASE_NOT_HACK_REGISTRATION(10023, "请不要非法地注册账号"),
    PHONE_NOT_EXISTS(10024, "手机号不存在"),
    PHONE_OR_PASSWORD_ERROR(10025, "手机号或密码错误"),
    USER_NOT_EXISTS(10026, "用户不存在"),
    ROLE_NEED_BIND_NOT_EXISTS(10027, "要绑定的角色已不存在"),
    ROLE_NOT_EXISTS(10028, "角色不存在"),
    RESOURCE_NEED_BIND_NOT_EXISTS(10029, "要绑定的资源已不存在"),
    RESOURCE_NOT_EXISTS(10030, "资源不存在"),
    RESOURCE_ID_INVALID(10031, "资源无效"),
    HAVE_ALREADY_BOUND_RESOURCE(10032, "存在已绑定过的资源"),
    HAVE_ALREADY_BOUND_ROLE(10033, "存在已绑定过的角色"),
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
