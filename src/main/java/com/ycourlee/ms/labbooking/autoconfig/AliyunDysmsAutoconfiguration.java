package com.ycourlee.ms.labbooking.autoconfig;

import com.ycourlee.ms.labbooking.manager.AliyunDysms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yongjiang
 */
@Configuration
@ConditionalOnClass(value = {AliyunDysms.class})
@EnableConfigurationProperties(AliyunDysmsProperties.class)
public class AliyunDysmsAutoconfiguration {

    @Autowired
    private AliyunDysmsProperties aliyunDysmsProperties;

    @Bean
    @ConditionalOnProperty(prefix = AliyunDysmsProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = false)
    public AliyunDysms aliyunDysms() {
        return new AliyunDysms(aliyunDysmsProperties);
    }
}
