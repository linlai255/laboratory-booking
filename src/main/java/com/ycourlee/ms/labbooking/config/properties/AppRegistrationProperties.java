package com.ycourlee.ms.labbooking.config.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yongjiang
 */
@Setter
@Getter
public class AppRegistrationProperties {

    /**
     * the phone list of administrators.
     */
    private List<String> adminPhoneWhitelist;
}