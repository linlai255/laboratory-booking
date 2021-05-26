package com.ycourlee.ms.labbooking.util;

import com.ycourlee.root.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yongjiang
 */
public class PasswdGeneratorUtil {

    private static final Logger log = LoggerFactory.getLogger(PasswdGeneratorUtil.class);

    private static final char[] BASE_CHAR    = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] SPECIAL_CHAR = "!@#$".toCharArray();

    public static String next(int length) {
        return next(length, Level.ADVANCED);
    }

    /**
     * 极小概率不含数字或字母或特殊字符
     *
     * @param length length
     * @param level  level
     * @return password
     */
    public static String next(int length, Level level) {
        if (length <= 0) {
            return null;
        }
        if (length < 8) {
            log.warn("password length too short.");
        }

        int specialCharCount = Math.floorDiv(length, 4);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(BASE_CHAR[RandomUtil.RANDOM.nextInt(BASE_CHAR.length)]);
            if (level.ordinal() == 0 || i == length - 1) {
                continue;
            }
            int index = RandomUtil.RANDOM.nextInt(length);
            if (index <= specialCharCount) {
                stringBuilder.append(SPECIAL_CHAR[RandomUtil.RANDOM.nextInt(SPECIAL_CHAR.length)]);
                i++;
            }
        }
        return stringBuilder.toString();
    }

    enum Level {
        /**
         * 基础版
         */
        BASIC,
        /**
         * 提升版
         */
        ADVANCED;
    }
}
