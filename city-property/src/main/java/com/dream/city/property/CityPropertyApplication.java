package com.dream.city.property;

import com.dream.city.base.config.RedisConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.dream.city.base.model.mapper")

@EnableCaching
@Import({RedisConfig.class})
public class CityPropertyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityPropertyApplication.class, args);
    }

}
