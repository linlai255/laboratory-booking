package com.ycourlee.ms.labbooking.config.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yongjiang
 */
@Setter
@Getter
public class AntPathSwitchProperties {

    private boolean      enabled = true;
    private List<String> pathWhitelist;
}