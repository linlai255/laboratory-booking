package com.ycourlee.ms.labbooking.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yongjiang
 */
public enum EAccountType {

    /**
     * administrator
     */
    ADMINISTRATOR(1, "管理员"),
    TEACHER(2, "教师"),
    ;

    private static final Map<Integer, EAccountType> ALL = new HashMap<Integer, EAccountType>();

    static {
        for (EAccountType item : EAccountType.values()) {
            ALL.put(item.getCode(), item);
        }
    }

    private final Integer code;
    private final String  name;

    EAccountType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static EAccountType instanceOf(int code) {
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
