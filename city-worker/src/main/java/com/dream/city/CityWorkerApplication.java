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
@EnableCaching
@EnableEurekaClient
@MapperScan("com.dream.city.base.model.mapper")
public class CityWorkerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CityWorkerApplication.class,args);
    }
}
