package com.ycourlee.ms.labbooking.config;

import com.ycourlee.ms.labbooking.auth.AuthorizationInterceptor;
import com.ycourlee.ms.labbooking.config.properties.LabSwitchProperties;
import com.ycourlee.root.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

/**
 * @author yongjiang
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;
    @Autowired
    @Qualifier("authorizationProperties")
    private LabSwitchProperties      authorizationProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(CollectionUtil.isEmpty(authorizationProperties.getPathWhitelist()) ? Collections.emptyList() : authorizationProperties.getPathWhitelist());
    }
}
