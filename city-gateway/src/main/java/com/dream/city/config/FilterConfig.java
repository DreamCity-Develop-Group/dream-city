package com.dream.city.config;

import com.dream.city.filter.WebSocketFilter;
import org.springframework.context.annotation.Bean;

public class FilterConfig {

    @Bean
    public WebSocketFilter accessFilter(){
        return new WebSocketFilter();
    }
}
