package com.dream.city;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Wvv
 */
@SpringBootApplication
@EnableEurekaClient
//@MapperScan("com.dream.city.base.model.mapper")
@EnableFeignClients
@EnableAsync
public class CityCommApplication {
    public static void main(String[] args) {
        SpringApplication.run(CityCommApplication.class, args);
    }

}
