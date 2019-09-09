package com.dream.city;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Wvv
 */
@SpringBootApplication
@EnableEurekaClient
public class CityCommApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityCommApplication.class, args);
    }

}
