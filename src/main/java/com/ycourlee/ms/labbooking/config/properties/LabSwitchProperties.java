package com.ycourlee.ms.labbooking.config.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yongjiang
 */
@Setter
@Getter
public class LabSwitchProperties {

    /**
     * switch of authentication or authorization.
     */
    private boolean      enabled = true;
    /**
     * whitelist of authentication or authorization.
     */
    private List<String> pathWhitelist;
}