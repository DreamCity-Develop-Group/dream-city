package com.dream.city.server;

import com.dream.city.domain.ApiSendObject;
import com.dream.city.domain.Message;
import com.dream.city.domain.MessageData;
import com.dream.city.service.HttpClientService;
import com.dream.city.util.HttpClientUtil;
import com.dream.city.util.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * @author Wvv
 */

@ServerEndpoint("/dream/city/{topic}/{name}")
@Component
public class WebSocketServer {

    private StringRedisTemplate redisTampate = SpringUtils.getBean(StringRedisTemplate.class);

    private RedisMessageListenerContainer redisMessageListenerContainer = SpringUtils.getBean(RedisMessageListenerContainer.class);

    @Autowired
    HttpClientService httpClientService;

    static Log log = LogFactory.getLog(WebSocketServer.class);

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */

    private Session session;

    /**
     * 接收sid
     */

    private String sid = "";

    private String clientId = "";

    /**
     * 连接建立成功调用的方法
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("topic") String topic) {
        sid = session.getId();
        log.info("有新窗口开始监听:" + sid + ",当前在线人数为" + getOnlineCount());
        this.session = session;
        //加入set中
        webSocketSet.add(this);
        //在线数加1
        addOnlineCount();

        SubscribeListener subscribeListener = new SubscribeListener();
        subscribeListener.setSession(session);
        subscribeListener.setStringRedisTemplate(redisTampate);
        //设置订阅topic
        redisMessageListenerContainer.addMessageListener(subscribeListener, new ChannelTopic(topic));


        this.sid = sid;
        try {
            //初始化连接的客户端，返回clientId
            String clientId = String.valueOf(new Random().nextInt(99999)) + "-" + String.valueOf(sid);
            this.clientId = clientId;
            log.info("有新窗口开始监听:" + clientId + ",当前在线人数为" + getOnlineCount());

            //String data = "{\"cmd\":\"init\",\"clientId\":\""+clientId+"\",\"msg\":\"连接成功\"}";
            //sendMessage(data);
            Message message = new Message();
            message.setSource("server");
            message.setTarget(clientId);
            message.setCreatetime(new Date().toString());
            message.setDesc("连接成功");
            MessageData data = new MessageData();
            data.setType("init");
            data.setModel("socket");
            data.setT(null);
            message.setData(data);
            String msg = JSON.toJSON(message).toString();
            sendMessage(msg);
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从set中删除
        webSocketSet.remove(this);
        //在线数减1
        subOnlineCount();
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("topic") String topic, @PathParam("name") String username) {
        log.info("收到来自窗口" + sid + "的信息:" + message + "/" + username);

        PublishServer publishService = SpringUtils.getBean(PublishServer.class);
        publishService.publish(topic, message);

        //根据sid 到服务上找对应的数据，=》校验 =》 推送数据到客户端
        try {
            //如果客户端发心跳包,回复success
            if (message.equals("ping")) {
                System.out.println("心跳消息接收...");
                sendMessage("success");
                return;
            }

            Message msg = JSONObject.parseObject(message, Message.class);

            if (((String) msg.getData().getT()).equals("order")) {

                Map<String, Object> data = new HashMap<>();
                data.put("orderId", "oid_12547");
                data.put("order_good", "一支铅笔");

                msg.setSource("server");
                msg.setTarget(msg.getSource());
                msg.setDesc("下单成功");
                msg.setCreatetime(String.valueOf(System.currentTimeMillis()));

                MessageData dataMsg = new MessageData();
                dataMsg.setType("createOrder");
                dataMsg.setModel("order");
                dataMsg.setT(data);
                msg.setData(dataMsg);

                String msgRet = JSON.toJSON(msg).toString();
                sendMessage(msgRet);
                return;
            }

            //请求restful接口，将数据发送给客户端
            HttpClientUtil.post((Message) msg);
            //new HttpClientUtil().postService(msg);
            //httpClientService.post(msg);
        }catch (IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("消息格式不正确！");
            e.printStackTrace();
        }


        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                if(item.clientId == session.getId()){
                    item.sendMessage(message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 异步发送对象数据
     *
     * @param object
     * @throws IOException
     */
    public void sendAsyncObject(Object object) throws IOException {
        this.session.getAsyncRemote().sendObject(object);
    }

    public void sendAsyncMessage(String message) throws IOException {
        this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 基本方式
     *
     * @param object
     * @throws IOException
     * @throws EncodeException
     */
    public void sendBasicObject(Object object) throws IOException, EncodeException {
        this.session.getBasicRemote().sendObject(object);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(Message message) throws IOException {
        log.info("推送消息到窗口" + message.getTarget() + "，推送内容:" + message);
        String clientId = message.getTarget();
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (clientId == null) {
                    item.sendAsyncObject(message);
                } else if (item.clientId.equals(clientId)) {
                    item.sendAsyncObject(message);
                    item.sendMessage(JSONObject.toJSONString(message));
                }
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
