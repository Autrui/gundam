package com.gundam.gateway.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Dubbo 配置类
 *
 * @author Autrui
 * @date 2025/4/1
 */
@Configuration
public class DubboConfig {
    @Value("${dubbo.application.name}")
    private String applicationName;

    @Value("${dubbo.registry.address}")
    private String registryAddress;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(applicationName);
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(registryAddress);
        return registryConfig;
    }
}