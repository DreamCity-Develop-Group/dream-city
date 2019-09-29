package com.dream.city;

import com.dream.city.base.config.RedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

/**
 * @author Wvv
 */
@SpringBootApplication
@EnableCaching
@EnableEurekaClient
public class CityWorkerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CityWorkerApplication.class,args);
    }
}
