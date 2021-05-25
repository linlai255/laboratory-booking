package com.ycourlee.ms.labbooking.exception.error;

import com.ycourlee.root.core.context.CmReturn;

/**
 * @author yongjiang
 */
public enum Errors implements CmReturn {

    /**
     * 未知错误
     */
    UNKNOWN(-1, "不好意思, 出错了"),
    CANNOT_AUTHENTICATE(10001, "无法认证"),
    SMS_SEND_FAILED(10002, "短信发送失败"),
    OPERATION_TOO_FAST(10003, "操作太快了"),
    ;

    private final int code;
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
