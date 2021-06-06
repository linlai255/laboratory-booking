package com.ycourlee.ms.labbooking.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yongjiang
 */
public enum EContainPathVar {

    /**
     * 0否 1否
     */
    NO(0, "否"),
    YES(1, "是"),
    ;

    private final Integer code;
    private final String  name;

    EContainPathVar(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private static final Map<Integer, EContainPathVar> ALL = new HashMap<>();

    static {
        for (EContainPathVar item : EContainPathVar.values()) {
            ALL.put(item.getCode(), item);
        }
    }

    public static EContainPathVar instanceOf(Integer code) {
        return ALL.get(code);
    }

    public static String getNameByCode(Integer code) {
        EContainPathVar e = instanceOf(code);
        return Objects.nonNull(e) ? e.getName() : "";
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
