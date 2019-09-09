package com.dream.city;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dream.city.domain.mapper")
//@EnableCaching
public class CityAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityAuthApplication.class, args);
    }

}
