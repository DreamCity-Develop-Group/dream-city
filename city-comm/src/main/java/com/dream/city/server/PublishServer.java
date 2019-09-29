package com.dream.city.server;

import com.dream.city.base.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Wvv
 */
@Component
@Slf4j
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

    /**
     * 消息发布
     *
     * @param channel
     * @param message
     */
    public void publishMessage(String channel, String message){
        // 该方法封装的 connection.publish(rawChannel, rawMessage);

        redisTemplate.convertAndSend(channel,message);
        log.info("publishServer：已经提交至订阅区");
    }

}
