package com.ycourlee.ms.labbooking.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yongjiang
 */
public enum ERequestMethod {

    /**
     *
     */
    GET(1, "get"),
    POST(2, "post"),
    PUT(3, "put"),
    DELETE(4, "delete"),
    OPTION(5, "option"),

    ;

    private static final Map<Integer, ERequestMethod> ALL = new HashMap<>();

    static {
        for (ERequestMethod item : ERequestMethod.values()) {
            ALL.put(item.getCode(), item);
        }
    }

    private final Integer code;
    private final String  name;

    ERequestMethod(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ERequestMethod instanceOf(Integer code) {
        return ALL.get(code);
    }

    public static String getNameByCode(Integer code) {
        ERequestMethod e = instanceOf(code);
        return Objects.nonNull(e) ? e.getName() : "";
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
