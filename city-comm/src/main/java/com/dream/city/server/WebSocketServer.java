package com.dream.city.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.base.utils.SpringUtils;
import com.dream.city.service.HttpClientService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.*;


/**
 * @author Wvv
 */

@ServerEndpoint("/dream/city/{topic}/{name}")
@Component
public class WebSocketServer {

    private StringRedisTemplate redisTampate = SpringUtils.getBean(StringRedisTemplate.class);

    private RedisMessageListenerContainer redisMessageListenerContainer = SpringUtils.getBean(RedisMessageListenerContainer.class);

    private RedisUtils redisUtils = SpringUtils.getBean(RedisUtils.class);

    private HttpClientService httpClientService = SpringUtils.getBean(HttpClientService.class);
    private PublishServer publishService = SpringUtils.getBean(PublishServer.class);
    //@Autowired
    //HttpClientService httpClientService;


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

    private String username = "";

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
        //将连接加入到redis
        //redisUtils.set();

        //在线数加1
        addOnlineCount();

        SubscribeListener subscribeListener = new SubscribeListener();
        subscribeListener.setSession(session);
        subscribeListener.setStringRedisTemplate(redisTampate);
        //设置订阅topic
        redisMessageListenerContainer.addMessageListener(subscribeListener, new ChannelTopic(topic));
        redisMessageListenerContainer.setTaskExecutor(newFixedThreadPool(4));
        //redisMessageListenerContainer.start();

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
            data.setData(null);
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
        redisUtils.del("clientID-"+this.username);
        //在线数减1
        subOnlineCount();
        log.info("有一连接["+this.username+"]关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("topic") String topic, @PathParam("name") String username) {
        log.info("收到来自窗口client-" + sid + "的信息:" + topic + "/" + username);
        log.info(message);

        publishService.publish(topic, message);

        //根据sid 到服务上找对应的数据，=》校验 =》 推送数据到客户端
        try {
            //TODO 如果客户端发心跳包,回复success
            if ("ping".equals(message)) {
                System.out.println("心跳消息接收...");
                sendMessage("success");
                return;
            }

            //解析出客户端发来的消息
            Message msg = JSONObject.parseObject(message, Message.class);



            if (null != msg.getData().getData()) {
                Map data = JsonUtil.parseJsonToObj(msg.getData().getData().toString(),Map.class);
                String tokenStr = "token_"+data.get("username");
                String token = redisUtils.get(tokenStr).toString();
                if (!StringUtils.isEmpty(token)){
                   redisUtils.set("clientID-"+data.get("username").toString(),this.clientId,60);
                   for (WebSocketServer webSocketServer : webSocketSet){
                       if (webSocketServer.clientId.equals(this.clientId)){
                           webSocketServer.username = data.get("username").toString();
                           break;
                       }
                   }
                }
                String strT = "";
                if (msg.getData().getData() instanceof String){
                    strT = (String)msg.getData().getData();
                }else if (msg.getData().getData() instanceof Integer){
                    Integer intT = (Integer)msg.getData().getData();
                    if (null != intT){
                        strT = String.valueOf(intT);
                    }

                }else if (msg.getData().getData() instanceof JSONObject){
                    JSONObject jsonObjectT = (JSONObject)msg.getData().getData();
                    if (null != jsonObjectT){
                        strT = String.valueOf(jsonObjectT);
                    }
                    if ("{}".equals(strT)){
                        strT = null;
                    }
                }

            }

            //请求网关的restful接口，将数据发送给客户端
            //HttpClientUtil.post((Message) msg);
            //new HttpClientUtil().postService(msg);
            Message replay = new Message();
            replay.setSource("server");
            replay.setTarget(WebSocketServer.this.clientId);
            replay.setDesc("服务端消息中心同步通知");
            replay.setCreatetime(String.valueOf(System.currentTimeMillis()));
            replay.setData(new MessageData("replay","messageCenter",null));
            //TODO 第一步: 同步回复消息，通知客户端接收消息成功
            WebSocketServer.sendInfo(replay);

            //TODO 第二步: 将异步处理消息逻辑
            httpClientService.post(msg);

            //TODO 第三步: 由任务调度完成对客户端消息的推送

        }catch (IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("消息格式不正确！");
            e.printStackTrace();
        }


        //群发消息
        /*for (WebSocketServer item : webSocketSet) {
            try {
                if(item.clientId == session.getId()){
                    item.sendMessage(message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
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
     * 发自定义消息
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
                    log.info(">>>>>>"+ JSONObject.toJSONString(message));
                }
            } catch (IOException e) {
                e.printStackTrace();
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
