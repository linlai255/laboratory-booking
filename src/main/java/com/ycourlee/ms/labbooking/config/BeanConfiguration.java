package com.ycourlee.ms.labbooking.config;

import com.ycourlee.ms.labbooking.config.properties.AliyunDysmsProperties;
import com.ycourlee.ms.labbooking.config.properties.AntPathSwitchProperties;
import com.ycourlee.ms.labbooking.config.properties.AppRegistrationProperties;
import com.ycourlee.ms.labbooking.config.properties.DefaultRoleProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yongjiang
 */
@Configuration
public class BeanConfiguration {

    @Bean
    @ConfigurationProperties(prefix = AliyunDysmsProperties.PREFIX)
    public AliyunDysmsProperties aliyunDysmsProperties() {
        return new AliyunDysmsProperties();
    }

    @Bean("authenticationProperties")
    @ConfigurationProperties(prefix = "lab.app.authentication")
    public AntPathSwitchProperties authenticationProperties() {
        return new AntPathSwitchProperties();
    }

    @Bean("authorizationProperties")
    @ConfigurationProperties(prefix = "lab.app.authorization")
    public AntPathSwitchProperties authorizationProperties() {
        return new AntPathSwitchProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "lab.app.registration")
    public AppRegistrationProperties appRegistrationProperties() {
        return new AppRegistrationProperties();
    }

    @Bean("adminDefaultRole")
    @ConfigurationProperties(prefix = "lab.app.registration.admin")
    public DefaultRoleProperties adminDefaultRole() {
        return new DefaultRoleProperties();
    }

    @Bean("teacherDefaultRole")
    @ConfigurationProperties(prefix = "lab.app.registration.teacher")
    public DefaultRoleProperties teacherDefaultRole() {
        return new DefaultRoleProperties();
    }
}
