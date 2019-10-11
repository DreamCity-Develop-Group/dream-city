package com.dream.city.config;

import com.dream.city.server.MessageReceiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author Wvv
 */
@Configuration
public class RedisSubListenerConfig {
    private static final String channel1 = "PUSHER_CHANNEL";
    private static final String channel2 = "notice1";

    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     *
     * @param connectionFactory
     * @param listenerAdapterTip
     * @return
     */
    @Bean
    //相当于xml中的bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapterTip,
                                            MessageListenerAdapter listenerAdapter2) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setTaskExecutor(newFixedThreadPool(4));
        //订阅了一个叫channel1 的通道
        container.addMessageListener(listenerAdapterTip, new PatternTopic(RedisSubListenerConfig.channel1));
        //订阅了一个叫chat的频道
        container.addMessageListener(listenerAdapter2, new PatternTopic(RedisSubListenerConfig.channel2));
        return container;
    }

    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     *
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapterTip(MessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessageTip");
    }

    @Bean
    MessageListenerAdapter listenerAdapter2(MessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage2");
    }

    /**
     * redis 读取内容的template
     */
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

}
