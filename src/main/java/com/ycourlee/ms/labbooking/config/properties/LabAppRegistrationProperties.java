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
     * the phone(or email) list of administrators. Phone(or email) cannot be registered as administrator before write here.
     */
    private List<String> adminWhitelist;
}