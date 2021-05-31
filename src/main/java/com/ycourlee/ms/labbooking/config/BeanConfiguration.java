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
    @ConfigurationProperties(prefix = LabAuthProperties.PREFIX)
    public LabAuthProperties labAuthProperties() {
        return new LabAuthProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = LabJwtProperties.PREFIX)
    public LabJwtProperties labJwtProperties() {
        return new LabJwtProperties();
    }

    @Bean("authenticationProperties")
    @ConfigurationProperties(prefix = "lab-app.authentication")
    public LabSwitchProperties labAuthenticationSwitchProperties() {
        return new LabSwitchProperties();
    }

    @Bean("authorizationProperties")
    @ConfigurationProperties(prefix = "lab-app.authorization")
    public LabSwitchProperties labAuthorizationProperties() {
        return new LabSwitchProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "lab-app.registration")
    public LabAppRegistrationProperties appRegistrationProperties() {
        return new LabAppRegistrationProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "lab-app.login")
    public LabAppLoginProperties appLoginProperties() {
        return new LabAppLoginProperties();
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
