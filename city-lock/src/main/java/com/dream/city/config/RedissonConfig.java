package com.dream.city.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.misc.ObjectInputFilter;

import java.io.IOException;

/*@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redisson() throws IOException {
        Config config = Config.fromYAML(
                RedissonConfig.class.getClassLoader().getResource("redisson.yml")
        );
        return Redisson.create(config);
    }
}*/
