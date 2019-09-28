package com.dream.city.server;

import java.io.IOException;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.util.Assert;

/**
 * @author WVV 描述：订阅监听类
 */
@Slf4j
public class SubscribeListener extends MessageListenerAdapter implements MessageListener{

    private StringRedisTemplate stringRedisTemplate;

    private Session session;

    /**
     * 订阅接收发布者的消息
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String topic = new String(pattern);
        log.info("当前监听收到channel["+topic+"]消息");
        String msg = new String(message.getBody()).trim();

        msg = msg.replace("\\","");
        msg = msg.substring(1,msg.length()-1);

        com.dream.city.base.model.Message myMsg = JsonUtil.parseJsonToObj(msg, com.dream.city.base.model.Message.class);
        log.info(new String(pattern) + "主题发布：" + msg);
        if(null!=session){
            try {
                RemoteEndpoint.Basic endpoint = session.getBasicRemote();
                log.info("clientId:"+session.getId());

                if (myMsg == null){
                    log.error("消息数据为空！");
                }else{
                    String[] strs = myMsg.getTarget().trim().split("-");
                    if (strs.length>1){
                        String id = strs[1];
                        if (id.equals(session.getId())){
                            endpoint.sendText(msg);
                        }
                    }
                    //发给所有的用户
                    else if("clients".equals(myMsg.getTarget())){
                        endpoint.sendText(msg);
                    }
                    //session.getBasicRemote().sendText(msg);
                }

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
