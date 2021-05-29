package com.ycourlee.ms.labbooking.auth;

import com.ycourlee.ms.labbooking.config.properties.LabAuthProperties;
import com.ycourlee.root.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author yongjiang
 */
@Configuration
public class LabAuth {

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Autowired
    protected LabAuthProperties properties;

    protected final boolean disabled() {
        return !properties.isEnabled();
    }

    protected final boolean greenLight(String uri) throws IOException, ServletException {
        // 默认都不放行
        if (CollectionUtil.isEmpty(properties.getPathWhitelist())) {
            return false;
        }
        for (String pathWhitelist : properties.getPathWhitelist()) {
            if (PATH_MATCHER.matchStart(pathWhitelist, uri)) {
                return true;
            }
        }
        return false;
    }
}
