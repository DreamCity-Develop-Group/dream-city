package com.dream.city;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Wvv
 */
@SpringBootApplication
@EnableEurekaServer
public class CityServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityServerApplication.class, args);
    }

}
