package com.dream.city.server;

import java.io.IOException;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

import com.dream.city.util.JsonUtil;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author WVV 描述：订阅监听类
 */
public class SubscribeListener implements MessageListener {

    private StringRedisTemplate stringRedisTemplate;

    private Session session;

    /**
     * 订阅接收发布者的消息
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String msg = new String(message.getBody());
        com.dream.city.domain.Message _msg = JsonUtil.parseJsonToObj(msg, com.dream.city.domain.Message.class);
        System.out.println(new String(pattern) + "主题发布：" + msg);
        if(null!=session){
            try {
                RemoteEndpoint.Basic endpoint = session.getBasicRemote();
                if (null != _msg && _msg.getTarget().equals(session.getId())){
                    endpoint.sendText(msg);
                }
                //session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
