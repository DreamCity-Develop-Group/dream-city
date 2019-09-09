package com.dream.city;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Wvv
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CityConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityConsumerApplication.class, args);
    }

}
