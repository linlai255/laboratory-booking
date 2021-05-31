package com.ycourlee.ms.labbooking.enums;

/**
 * @author yongjiang
 */
public enum EDigit {

    /**
     * 0 1 2
     */
    ZERO(0),
    ONE(1),
    TWO(2),

    ;

    private final Integer code;

    EDigit(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
