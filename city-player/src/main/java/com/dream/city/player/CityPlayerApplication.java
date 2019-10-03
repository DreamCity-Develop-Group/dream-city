package com.dream.city.player;

import com.dream.city.base.config.RedisConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

/**
 * @author Wvv
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.dream.city.base.model.mapper")
@EnableCaching
@Import({RedisConfig.class})
public class CityPlayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityPlayerApplication.class, args);
    }

}
