package com.ycourlee.ms.labbooking.util;

/**
 * @author yongjiang
 */
public class KeyPool {

    /**
     * @param token issued token
     * @return key
     */
    public static String token(String token) {
        return "TOKEN:" + token;
    }

    /**
     * two hours.
     *
     * @return duration
     */
    public static long defaultTokenExpireTime() {
        return 60L * 60 * 2;
    }

    /**
     * 7 days.
     *
     * @return duration
     */
    public static long days7InSeconds() {
        return 60L * 60 * 24 * 7;
    }

    public static String registerCodeApplyFrequencyLock(String account) {
        return "REGISTER_CODE:LOCK:" + account;
    }

    public static String registerCode(String account) {
        return "REGISTER_CODE:" + account;
    }
}
