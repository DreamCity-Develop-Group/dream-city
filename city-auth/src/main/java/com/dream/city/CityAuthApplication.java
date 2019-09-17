package com.dream.city;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Wvv
 */
@SpringBootApplication
@MapperScan("com.dream.city.domain.mapper")
@EnableCaching
@EnableEurekaClient
public class CityAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityAuthApplication.class, args);
    }

}
