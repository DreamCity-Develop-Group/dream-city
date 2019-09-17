package com.dream.city.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import javax.annotation.Resource;

/**
 * @author Wvv
 * Redis Config
 */
/*@Configuration
public class RedisConfig {
    @Resource
    private JedisConnectionFactory jedisConnecFactory;

    *//**
     * @author Wvv 描述：需要手动注册RedisMessageListenerContainer加入IOC容器
     * @return
     *//*
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(jedisConnecFactory);

        return container;

    }
}*/
