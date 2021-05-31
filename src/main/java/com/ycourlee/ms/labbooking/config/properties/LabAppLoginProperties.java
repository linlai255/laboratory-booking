package com.ycourlee.ms.labbooking.config.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yongjiang
 */
@Setter
@Getter
public class LabAppLoginProperties {

    /**
     * phone mode switch.
     */
    private boolean phoneModeEnabled;
    /**
     * email mode switch.
     */
    private boolean emailModeEnabled;
}