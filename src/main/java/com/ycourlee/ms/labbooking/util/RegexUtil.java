package com.ycourlee.ms.labbooking.util;

import java.util.regex.Pattern;

/**
 * @author yongjiang
 */
public class RegexUtil {

    private static final String  PHONE_REGEX   = "^((13[0-9])|(14[5-9])|(15([0-3]|[5-9]))|(16[6-7])|(17[1-8])|(18[0-9])|(19[1|3])|(19[5|6])|(19[8|9]))\\d{8}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    private static final String  EMAIL_REGEX   = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private static final String  HOUR_MINUTE_REGEX   = "^(([0-1][0-9])|(2[0-3])):[0-5][0-9]$";
    private static final Pattern HOUR_MINUTE_PATTERN = Pattern.compile(HOUR_MINUTE_REGEX);

    public static boolean isPhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isHourMinute(String time) {
        return HOUR_MINUTE_PATTERN.matcher(time).matches();
    }
}
