package com.ycourlee.ms.labbooking.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yongjiang
 */
@ConfigurationProperties(prefix = AliyunDysmsProperties.PREFIX)
public class AliyunDysmsProperties {

    public static final String PREFIX = "aliyun.dysms";

    private Boolean enabled;
    private String  accessKeyId;
    private String  accessKeySecret;
    private String  endpoint;
    private Long    verifyCodeAliveTimeout;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Long getVerifyCodeAliveTimeout() {
        return verifyCodeAliveTimeout;
    }

    public void setVerifyCodeAliveTimeout(Long verifyCodeAliveTimeout) {
        this.verifyCodeAliveTimeout = verifyCodeAliveTimeout;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
