package com.ycourlee.ms.labbooking.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yongjiang
 */
public enum ERoleType {

    /**
     * 1.admin 2.teacher
     */
    ADMIN_ROLE(1, "admin"),
    TEACHER_ROLE(2, "teacher"),
    ;

    private static final Map<Integer, ERoleType> ALL = new HashMap<>();

    static {
        for (ERoleType item : ERoleType.values()) {
            ALL.put(item.getCode(), item);
        }
    }

    private final Integer code;
    private final String  name;

    ERoleType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ERoleType instanceOf(int code) {
        return ALL.get(code);
    }

    public static String getNameByCode(int code) {
        return Objects.nonNull(instanceOf(code)) ? instanceOf(code).getName() : "";
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
