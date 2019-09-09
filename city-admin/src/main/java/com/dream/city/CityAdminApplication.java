package com.dream.city;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Wvv
 */
@SpringBootApplication
@EnableAdminServer
@EnableEurekaClient
public class CityAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityAdminApplication.class, args);
    }

}
