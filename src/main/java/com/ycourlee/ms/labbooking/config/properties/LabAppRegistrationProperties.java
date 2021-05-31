package com.ycourlee.ms.labbooking.config.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yongjiang
 */
@Setter
@Getter
public class LabAppRegistrationProperties {

    /**
     * the phone list of administrators. Phone cannot be registered as administrator before write here.
     */
    private List<String> adminWhitelist;
}