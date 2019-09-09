package com.dream.city;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Wvv
 */
@SpringBootApplication
@EnableEurekaClient
public class CityMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityMessageApplication.class, args);
    }

}
