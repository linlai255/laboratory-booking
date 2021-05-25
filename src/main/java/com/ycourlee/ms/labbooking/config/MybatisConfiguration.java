package com.ycourlee.ms.labbooking.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yongjiang
 */
@Configuration
@MapperScan(basePackages = "com.ycourlee.ms.labbooking.mapper")
public class MybatisConfiguration {
}
