package com.dream.city;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Wvv
 */
@SpringBootApplication
@EnableEurekaClient
@EnableAsync
public class CityCommApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityCommApplication.class, args);
    }

}
