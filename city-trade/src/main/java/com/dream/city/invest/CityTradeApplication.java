package com.dream.city.invest;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.dream.city.base.config.RedisConfig;
import com.dream.city.base.service.impl.DictionaryServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.dream.city.base.model.mapper")
@Import({RedisConfig.class, DictionaryServiceImpl.class})
@EnableDistributedTransaction
public class CityTradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityTradeApplication.class, args);
    }

}
