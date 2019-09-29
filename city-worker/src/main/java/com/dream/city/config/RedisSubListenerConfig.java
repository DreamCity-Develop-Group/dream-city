package com.dream.city.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 */
@Configuration
@Slf4j
public class RedisSubListenerConfig {
    @Autowired
    WorkItemsConfig workItemsConfig;

    //不同的频道名
    //登录
    private static final String CHANNEL_LISTENER_LOGIN = "CHANNEL_LISTENER_LOGIN";
    //任务成功
    private static final String CHANNEL_LISTENER_PLATRANS = "CHANNEL_LISTENER_PLATRANS";
    //任务失败
    private static final String CHANNEL_LISTENER_SEREVERPUSH = "CHANNEL_LISTENER_SEREVERPUSH";


    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     * @param connectionFactory
     * @param listenerAdapterLogin
     * @param listenerAdapterPlatTrans
     * @param listenerAdapterServerPush
     * @return
     */

    @Bean
    //相当于xml中的bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapterLogin,
                                            MessageListenerAdapter listenerAdapterPlatTrans,
                                            MessageListenerAdapter listenerAdapterServerPush) {

        //list.forEach(System.out::println);
        //maps.forEach((key,value)->System.out.println(key+"=>"+value));

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);

        /*workItemsConfig.getTasks().forEach((key,value)->{
            String topic = workItemsConfig.getTasks().get(key);

            container.addMessageListener(
                    new MessageListenerAdapter(receiver,key),
                    new PatternTopic(topic)
            );
        });*/
        /*listenerAdapters.forEach((key,value)->{
            String topic = workItemsConfig.getTasks().get(key);

            container.addMessageListener(
                    new MessageListenerAdapter(receiver,key),
                    new PatternTopic(topic)
            );
        });*/

        //订阅了一个叫listenerAdapterLogin的通道
        container.addMessageListener(listenerAdapterLogin, new PatternTopic(RedisSubListenerConfig.CHANNEL_LISTENER_LOGIN));

        //订阅了一个叫listenerAdapterPlatTrans的通道
        container.addMessageListener(listenerAdapterPlatTrans, new PatternTopic(RedisSubListenerConfig.CHANNEL_LISTENER_PLATRANS));
        //订阅了一个叫listenerAdapterServerPush的频道
        container.addMessageListener(listenerAdapterServerPush, new PatternTopic(RedisSubListenerConfig.CHANNEL_LISTENER_SEREVERPUSH));

        return container;
    }

    /**
     * 用于：
     * 登录，注册，验证码，心跳、退出等
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapterLogin(MessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "listenerAdapterLogin");
    }

    /**
     * 用于业务数据处理
     *
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapterPlatTrans(MessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "listenerAdapterPlatTrans");
    }


    /**
     * 用于后台推送数据
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapterServerPush(MessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "listenerAdapterServerPush");
    }

    /**
     * redis 读取内容的template
     */
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

}
