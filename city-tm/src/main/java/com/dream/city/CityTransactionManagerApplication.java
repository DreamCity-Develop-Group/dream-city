package com.dream.city;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTransactionManagerServer
public class CityTransactionManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityTransactionManagerApplication.class, args);
    }
}
