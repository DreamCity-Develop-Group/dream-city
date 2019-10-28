package com.dream.city;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CityLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityLockApplication.class, args);
    }

}
