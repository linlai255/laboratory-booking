package com.ycourlee.ms.labbooking.config.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author yongjiang
 */
@Setter
@Getter
public class LabDefaultRoleProperties {

    /**
     * default role id binding to the teacher or admin when they were created.
     */
    private Set<Integer> defaultRoleId;
}