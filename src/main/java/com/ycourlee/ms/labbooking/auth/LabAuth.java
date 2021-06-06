package com.ycourlee.ms.labbooking.auth;

import com.ycourlee.ms.labbooking.config.properties.LabAuthProperties;
import com.ycourlee.root.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

/**
 * @author yongjiang
 */
@Configuration
public class LabAuth {

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Autowired
    protected LabAuthProperties properties;

    protected final boolean disabled() {
        return !properties.isEnabled();
    }

    protected final boolean greenLight(String uri) {
        // 默认都不放行
        if (CollectionUtil.isEmpty(properties.getPathWhitelist())) {
            return false;
        }
        for (String pathWhitelist : properties.getPathWhitelist()) {
            if (PATH_MATCHER.match(pathWhitelist, uri)) {
                return true;
            }
        }
        return false;
    }
}
