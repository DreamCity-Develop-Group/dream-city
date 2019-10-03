package com.dream.city;

import com.dream.city.base.config.RedisConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableEurekaClient

@EnableCaching
@Import({RedisConfig.class})
public class CitySetApplication {

    public static void main(String[] args) {
        SpringApplication.run(CitySetApplication.class, args);
    }

}
