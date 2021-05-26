package com.ycourlee.ms.labbooking.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yongjiang
 */
public enum ERoleId {

    /**
     * admin
     */
    ADMIN_ROLE(1, "admin"),
    TEACHER_ROLE(2, "teacher"),
    ;

    private static final Map<Integer, ERoleId> ALL = new HashMap<>();

    static {
        for (ERoleId item : ERoleId.values()) {
            ALL.put(item.getCode(), item);
        }
    }

    private final int    code;
    private final String name;

    ERoleId(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ERoleId instanceOf(int code) {
        return ALL.get(code);
    }

    public static String getNameByCode(int code) {
        return Objects.nonNull(instanceOf(code)) ? instanceOf(code).getName() : "";
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
