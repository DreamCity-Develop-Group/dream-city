package com.dream.city;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableCaching
@EnableEurekaClient
@MapperScan("com.dream.city.base.model.mapper")

public class CityAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityAuthApplication.class, args);
    }

}
