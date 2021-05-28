package com.ycourlee.ms.labbooking.config;

import com.ycourlee.ms.labbooking.config.properties.*;
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

    @Bean
    @ConfigurationProperties(prefix = LabJwtProperties.PREFIX)
    public LabJwtProperties labJwtProperties() {
        return new LabJwtProperties();
    }

    @Bean("authenticationProperties")
    @ConfigurationProperties(prefix = "lab-app.authentication")
    public LabAntPathSwitchProperties authenticationProperties() {
        return new LabAntPathSwitchProperties();
    }

    @Bean("authorizationProperties")
    @ConfigurationProperties(prefix = "lab-app.authorization")
    public LabAntPathSwitchProperties authorizationProperties() {
        return new LabAntPathSwitchProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "lab-app.registration")
    public LabAppRegistrationProperties appRegistrationProperties() {
        return new LabAppRegistrationProperties();
    }

    @Bean("adminDefaultRole")
    @ConfigurationProperties(prefix = "lab-app.registration.admin")
    public LabDefaultRoleProperties adminDefaultRole() {
        return new LabDefaultRoleProperties();
    }

    @Bean("teacherDefaultRole")
    @ConfigurationProperties(prefix = "lab-app.registration.teacher")
    public LabDefaultRoleProperties teacherDefaultRole() {
        return new LabDefaultRoleProperties();
    }
}
