package com.dream.city.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import javax.annotation.Resource;

/**
 * @author Wvv
 * Redis Config
 */
/*@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate redisTemplate(RedisTemplate redisTemplate){
        return redisTemplate;
    }
}*/
