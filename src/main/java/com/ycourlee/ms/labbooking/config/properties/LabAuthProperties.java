package com.ycourlee.ms.labbooking.config.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yongjiang
 */
@Setter
@Getter
public class LabAuthProperties {

    public static final String PREFIX = "lab-app.auth";

    /**
     * switch of authentication and authorization.
     */
    private boolean enabled = true;

    /**
     * whitelist of authentication and authorization.
     */
    private List<String> pathWhitelist;

    /**
     * token key in the header.
     */
    private String tokenKey = "Authorization";
}