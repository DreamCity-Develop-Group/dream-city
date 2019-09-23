package com.dream.city.invest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.dream.city.invest.domain.mapper")
@EnableCaching
public class CityInvestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityInvestApplication.class, args);
    }

}
