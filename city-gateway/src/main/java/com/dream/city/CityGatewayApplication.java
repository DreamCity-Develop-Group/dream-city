package com.dream.city;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

/**
 * @author Wvv
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.dream.city.base.model.mapper")
//@EnableZuulServer
@EnableZuulProxy
public class CityGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityGatewayApplication.class, args);
    }

}
