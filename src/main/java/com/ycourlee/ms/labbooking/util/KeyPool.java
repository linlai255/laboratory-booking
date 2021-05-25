package com.ycourlee.ms.labbooking.util;

/**
 * @author yongjiang
 */
public class KeyPool {

    public static String token(String username) {
        return "TOKEN:" + username;
    }

    public static String verifyCode(String phone) {
        return "CODE:" + phone;
    }
}
