package com.dream.city.server;

import java.io.IOException;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
        String msg = new String(message.getBody()).trim();
        //
        msg = msg.replace("\\","");
        msg = msg.substring(1,msg.length()-1);
        //com.dream.city.base.model.Message jsonObject = JSON.parseObject(msg,com.dream.city.base.model.Message.class);

        com.dream.city.base.model.Message _msg = JsonUtil.parseJsonToObj(msg, com.dream.city.base.model.Message.class);
        System.out.println(new String(pattern) + "主题发布：" + msg);
        if(null!=session){
            try {
                RemoteEndpoint.Basic endpoint = session.getBasicRemote();
                System.out.println(session.getId());
                String[] strs = _msg.getTarget().trim().split("-");
                if (strs.length>1){
                    String id = strs[1];
                    if (id.equals(session.getId())){
                        endpoint.sendText(msg);
                    }
                }
                else if(_msg.getTarget().equals("clients")){
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
