package com.ycourlee.ms.labbooking.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yongjiang
 */
public enum EBookingRecordStatus {

    /**
     * 1待上课 2.已下课 3.已取消
     */
    WAITING_CLASS(1, "待上课"),
    AFTER_CLASS(2, "已下课"),
    CANCELED(3, "已取消"),
    ;

    private final Integer code;
    private final String  name;

    EBookingRecordStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private static final Map<Integer, EBookingRecordStatus> ALL = new HashMap<>();

    static {
        for (EBookingRecordStatus item : EBookingRecordStatus.values()) {
            ALL.put(item.getCode(), item);
        }
    }

    public static EBookingRecordStatus instanceOf(Integer code) {
        return ALL.get(code);
    }

    public static String getNameByCode(Integer code) {
        EBookingRecordStatus e = instanceOf(code);
        return Objects.nonNull(e) ? e.getName() : "";
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
