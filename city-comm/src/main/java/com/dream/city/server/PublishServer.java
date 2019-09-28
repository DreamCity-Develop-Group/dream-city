package com.dream.city.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Wvv
 */
@Component
public class PublishServer {
    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 消息发布
     *
     * @param channel
     * @param message
     */
    public void publish(String channel,Object message){
        // 该方法封装的 connection.publish(rawChannel, rawMessage);
        redisTemplate.convertAndSend(channel,message);
    }

}
