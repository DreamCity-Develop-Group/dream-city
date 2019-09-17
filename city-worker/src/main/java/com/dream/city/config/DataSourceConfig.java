package com.dream.city.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@Configuration
public class DataSourceConfig {

    @Resource
    Environment environment;

    @Bean
    public DataSource
}
