package com.ycourlee.ms.labbooking.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yongjiang
 */
public enum EResourceType {

    /**
     * 1.菜单 2.API
     */
    MENU(1, "菜单"),
    API(2, "API"),
    ;

    private static final Map<Integer, EResourceType> ALL = new HashMap<Integer, EResourceType>();

    static {
        for (EResourceType item : EResourceType.values()) {
            ALL.put(item.getCode(), item);
        }
    }

    private final Integer code;
    private final String  name;

    EResourceType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static EResourceType instanceOf(Integer code) {
        return ALL.get(code);
    }

    public static String getNameByCode(Integer code) {
        return Objects.nonNull(instanceOf(code)) ? instanceOf(code).getName() : "";
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
