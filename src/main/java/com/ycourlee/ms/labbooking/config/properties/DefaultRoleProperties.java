package com.ycourlee.ms.labbooking.config.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yongjiang
 */
@Setter
@Getter
public class DefaultRoleProperties {

    /**
     * default role id binding to the teacher or admin when they has created.
     */
    private List<Integer> defaultRoleId;
}