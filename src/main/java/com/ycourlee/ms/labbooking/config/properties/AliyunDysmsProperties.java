package com.ycourlee.ms.labbooking.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.convert.DurationUnit;

import java.time.temporal.ChronoUnit;

/**
 * @author yongjiang
 */
@Setter
@Getter
public class AliyunDysmsProperties {

    public static final String PREFIX = "aliyun.dysms";

    private Boolean enabled = true;
    private String  accessKeyId;
    private String  accessKeySecret;
    private String  endpoint;
    @DurationUnit(ChronoUnit.SECONDS)
    private Long    verifyCodeAliveTimeout;
}