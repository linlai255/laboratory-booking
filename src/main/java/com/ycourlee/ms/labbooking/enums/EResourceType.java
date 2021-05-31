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
    MENU(((byte) 1), "菜单"),
    API(((byte) 2), "API"),
    ;

    private static final Map<Byte, EResourceType> ALL = new HashMap<Byte, EResourceType>();

    static {
        for (EResourceType item : EResourceType.values()) {
            ALL.put(item.getCode(), item);
        }
    }

    private final byte   code;
    private final String name;

    EResourceType(byte code, String name) {
        this.code = code;
        this.name = name;
    }

    public static EResourceType instanceOf(int code) {
        return ALL.get(code);
    }

    public static String getNameByCode(int code) {
        return Objects.nonNull(instanceOf(code)) ? instanceOf(code).getName() : "";
    }

    public byte getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
