package com.dream.city.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Wvv
 */
@Configuration
@Data
public class GateWayConfig {

    @Value("${gate.zuul.url}")
    private String url;

    public GateWayConfig(){

    }
}
