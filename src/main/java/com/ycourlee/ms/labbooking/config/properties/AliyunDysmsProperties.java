package com.ycourlee.ms.labbooking.config.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yongjiang
 */
@Setter
@Getter
public class AliyunDysmsProperties {

    public static final String PREFIX = "aliyun.dysms";

    private Boolean enabled;
    private String  accessKeyId;
    private String  accessKeySecret;
    private String  endpoint;
    private Long    verifyCodeAliveTimeout;
}