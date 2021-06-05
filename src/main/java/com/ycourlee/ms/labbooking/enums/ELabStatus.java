package com.ycourlee.ms.labbooking.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yongjiang
 */
public enum ELabStatus {

    /**
     * 0开启 1关闭
     */
    AVAILABLE(0, "可用"),
    UNAVAILABLE(1, "不可用");

    private final Integer code;
    private final String  name;

    ELabStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private static final Map<Integer, ELabStatus> ALL = new HashMap<>();

    static {
        for (ELabStatus item : ELabStatus.values()) {
            ALL.put(item.getCode(), item);
        }
    }

    public static ELabStatus instanceOf(Integer code) {
        return ALL.get(code);
    }

    public static String getNameByCode(Integer code) {
        ELabStatus e = instanceOf(code);
        return Objects.nonNull(e) ? e.getName() : "";
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
