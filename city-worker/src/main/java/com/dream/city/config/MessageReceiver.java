package com.dream.city.config;

import com.dream.city.base.model.Message;
import com.dream.city.base.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Wvv
 */
@Component
@Slf4j
public class MessageReceiver {
    /**
     * 用于平台业务
     * @param message
     */
    public void listenerAdapterPlatTrans(Message message){
        System.out.println("listenerAdapterPlatTrans收到一条消息：");
        log.info(JsonUtil.parseObjToJson(message));
    }

    /**
     * 用于服务推送
     * @param message
     */
    public void listenerAdapterServerPush(Message message){
        System.out.println("listenerAdapterServerPush收到一条消息：");
        log.info(JsonUtil.parseObjToJson(message));
    }

    /**
     * 用于账户出入
     * @param message
     */
    public void listenerAdapterLogin(Message message){
        log.info("listenerAdapterLogin收到登录消息");
        log.info(JsonUtil.parseObjToJson(message));
    }

}
