package com.dream.city.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.base.utils.SpringUtils;
import com.dream.city.service.HttpClientService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * @author Wvv
 * <p>
 * topic :
 */

@ServerEndpoint("/dream/city/{topic}/{name}")
@Component
public class WebSocketServer {
    @Value("${server.port}")
    private String server;

    private StringRedisTemplate redisTampate = SpringUtils.getBean(StringRedisTemplate.class);

    //private RedisMessageListenerContainer redisMessageListenerContainer = SpringUtils.getBean(RedisMessageListenerContainer.class);
    //RedisSubListenerConfig config = SpringUtils.getBean(RedisSubListenerConfig.class);

    private static RedisUtils redisUtils = SpringUtils.getBean(RedisUtils.class);

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
    public void onOpen(Session session, @PathParam("topic") String topic, @PathParam("name") String name) {
        sid = session.getId();
        log.info(
                "有新窗口[" + name +
                        "@" + topic + "]开始监听:" + sid +
                        ",当前在线人数为" + getOnlineCount()+"||"+getOnlinePlayerCount());
        this.session = session;
        //加入set中
        webSocketSet.add(this);
        redisUtils.addOnlinePlayer("clientID_"+server+"_"+session.getId(),1);
        //将连接加入到redis
        //redisUtils.set();

        //在线数加1
        addOnlineCount();


        SubscribeListener subscribeListener = new SubscribeListener();
        subscribeListener.setSession(session);
        subscribeListener.setStringRedisTemplate(redisTampate);
        //设置订阅topic
        //redisMessageListenerContainer.addMessageListener(subscribeListener, new ChannelTopic(topic));
        //redisMessageListenerContainer.setTaskExecutor(newFixedThreadPool(4));

        //redisMessageListenerContainer.addMessageListener(subscribeListener, new ChannelTopic(topic+":"+name));
        //redisMessageListenerContainer.setTaskExecutor(newFixedThreadPool(4));

        //redisMessageListenerContainer.start();

        this.sid = sid;
        try {
            //初始化连接的客户端，返回clientId
            String clientId = String.valueOf(new Random().nextInt(99999)) + "-" + String.valueOf(sid);
            this.clientId = clientId;
            log.info("有新窗口[" + name + "@" + topic + "]开始监听:" + clientId + ",当前在线人数为" + getOnlineCount());


            //String data = "{\"cmd\":\"init\",\"clientId\":\""+clientId+"\",\"msg\":\"连接成功\"}";
            //sendMessage(data);
            String redisKey = RedisKeys.LOGIN_USER_TOKEN + username;
            boolean token = redisUtils.hasKey(redisKey);
            String newVersion  = redisUtils.hasKey("APP_VERSION")?redisUtils.getStr("APP_VERSION"):"1.0.0";
            if(Objects.isNull(newVersion)){
                newVersion = "1.0.0";
            }
            newVersion = newVersion.replace("\"","");
            String connect = token ? "重连成功" : "连接成功";
            log.info(connect);
            Map version = new HashMap();
            version.put("version",newVersion);
            Message message = new Message(
                    "server",
                    clientId,
                    new MessageData("init", "socket", version,ReturnStatus.SUCCESS.getStatus()),
                    connect,
                    new Date().toString()
            );

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
        redisUtils.del(RedisKeys.PLAYER_ONLINE_STATE_KEY + this.username);
        //在线数减1
        subOnlineCount();
        log.info("有一连接[" + this.username + "]关闭！当前在线人数为" + getOnlineCount());
        boolean ret = redisUtils.rmOnlinePlayer(this.username);
        if (!ret){
            ret = redisUtils.rmOnlinePlayer(this.username);
            if (!ret){
                ret = redisUtils.rmOnlinePlayer(this.username);
            }
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("topic") String topic, @PathParam("name") String username) {
        log.info("收到来自窗口client-" + sid + "的信息:" + topic + "/" + username);
        log.info("Message:" + message);
        //this.session.addMessageHandler(SocketMessageHandler.MessageHandler.handleTextMessage(this.session,message));
        publishService.publish(topic, message);
        //根据sid 到服务上找对应的数据，=》校验 =》 推送数据到客户端
        try {
            //TODO 1、如果客户端发心跳包[ping&&XXXX],回复success：包括登录期间的ping和登录之前的连接检测
            String[] msgArray = message.split("&&");
            if (msgArray.length > 1) {
                String ping = msgArray[0];
                String account = msgArray[1];
                String heartBeat = "ping";
                String tokenBeat = "token";

                if (heartBeat.equals(ping)) {
                    System.out.println("心跳消息接收...");
                    //TODO 保持连接状态，更新TOKEN：当用户Token即将过期，但此时用户实际在线，需要续期
                    String redisKey = RedisKeys.LOGIN_USER_TOKEN + account;
                    if (redisUtils.hasKey(redisKey)) {
                        long expire = redisUtils.getExpire(redisKey);
                        long expired = 60;
                        if (expire < expired && expire != 0) {
                            //取出token
                            String token = redisUtils.getStr(redisKey);
                            //延期token
                            redisUtils.set(redisKey, token, 30 * 60);
                        }
                    }
                    sendMessage("success");
                    return;
                }
                //Token检测
                if(tokenBeat.equals(ping)){
                    Message replay = new Message(
                            "server",
                            WebSocketServer.this.clientId,
                            new MessageData(
                                    "tokenCheck",
                                    "messageCenter",
                                    ReturnStatus.SUCCESS.getStatus()
                            ),
                            "服务端消息中心同步通知",
                            String.valueOf(System.currentTimeMillis())
                    );

                    String tokenStr = RedisKeys.LOGIN_USER_TOKEN + account;
                    String token = redisUtils.getStr(tokenStr);

                    if (StringUtils.isNotBlank(token)) {
                        sendInfo(replay);
                    }else{
                        log.info("token无效");
                        return;
                    }
                }
            } else {

                //解析出客户端发来的消息
                Message msg = JSONObject.parseObject(message, Message.class);

                Message replay = new Message(
                        "server",
                        WebSocketServer.this.clientId,
                        new MessageData(
                                "replay",
                                "messageCenter",
                                ReturnStatus.SUCCESS.getStatus()
                        ),
                        "服务端消息中心同步通知",
                        String.valueOf(System.currentTimeMillis())
                );
                //TODO 2、客户端断线重连，客户端已经有相应的逻辑处理
                boolean offline = false;
                if (offline) {
                    System.out.println("客户端断线重连消息接收...");
                    sendMessage("success");
                    return;
                }

                if (null != msg.getData().getData()) {
                    JSONObject data = JsonUtil.parseJsonToObj(msg.getData().getData().toString(), JSONObject.class);
                    String tokenStr = RedisKeys.LOGIN_USER_TOKEN + data.getString("username");

                    Optional<String> token0 = redisUtils.get(tokenStr);
                    String token = token0.isPresent()?token0.get():null;
                    //设置识别名称
                    if (StringUtils.isNotBlank(token)) {
                        //检测是否重复登录
                        if("login".equals(msg.getData().getType())|| "codeLogin".equals(msg.getData().getType())){
                            //将已经上线的客户端强制下线
                            //取出clientId
                            if(redisUtils.hasKey(RedisKeys.LOGIN_USER_TOKEN + data.get("username").toString())) {
                                if(redisUtils.isOnlinePlayer(data.get("username").toString())) {
                                    redisUtils.rmOnlinePlayer(data.get("username").toString());
                                    redisUtils.del(RedisKeys.LOGIN_USER_TOKEN + data.get("username").toString());
                                    String client = redisUtils.get(RedisKeys.PLAYER_ONLINE_STATE_KEY + data.get("username").toString()).get();
                                    replay.getData().setCode(ReturnStatus.RELOGIN_OPT.getStatus());
                                    replay.setDesc(ReturnStatus.RELOGIN_OPT.getDesc());
                                    replay.setTarget(client);

                                    sendInfo(replay);
                                }
                            }
                            redisUtils.delete(tokenStr);
                        }

                        redisUtils.set(RedisKeys.PLAYER_ONLINE_STATE_KEY + data.get("username").toString(), this.clientId, 60);
                        for (WebSocketServer webSocketServer : webSocketSet) {
                            if (webSocketServer.clientId.equals(this.clientId)) {
                                webSocketServer.username = data.get("username").toString();
                                break;
                            }
                        }

                    }else {
                        HashSet<String> set = new HashSet<>();
                        set.add("login");
                        set.add("codeLogin");
                        set.add("reg");
                        set.add("getCode");
                        set.add("jobPush");
                        set.add("createWorker");
                        set.add("pwforget");
                        set.add("exit");
                        if (set.contains(msg.getData().getType())){
                            log.info("Access method is Ok! ");
                            //逻辑继续
                        }else{
                            //TODO TOKEN 无效通知
                            replay.getData().setType("token");
                            replay.getData().setCode(ReturnStatus.TOKEN_EXPIRED.getStatus());
                            replay.setDesc("当前token已经失效，不能操作");
                            WebSocketServer.sendInfo(replay);
                            return;
                        }

                    }

                    //请求网关的restful接口，将数据发送给客户端
                    //HttpClientUtil.post((Message) msg);
                    //new HttpClientUtil().postService(msg);

                    //TODO 第一步: 同步回复消息，通知客户端接收消息成功
                    WebSocketServer.sendInfo(replay);

                    //TODO 第二步: 将异步处理消息逻辑
                    httpClientService.post(msg);

                    //TODO 第三步: 由任务调度完成对客户端消息的推送

                } else {
                    log.info("没有收到相应的消息数据，无法完成相应的业务逻辑！");
                    replay.setDesc("消息数据不可用");
                    replay.setCode(ReturnStatus.INVALID.getStatus());
                    WebSocketServer.sendInfo(replay);
                }
            }
        } catch (IOException e) {
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
        log.error("发生错误:" + error.getMessage());

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
                    //String messageTo = JSONObject.toJSONString(message);
                    String messageTo = JsonUtil.parseObjToJson(message);
                    item.sendAsyncObject(message);

                    item.sendMessage(messageTo);
                    log.info(">>>>>>" + JSONObject.toJSONString(message));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发自定义消息
     */
    public static void pushInfo(Message message) throws IOException {
        log.info("推送消息到窗口" + message.getTarget() + "，推送内容:" + message);
        String clientId = message.getTarget();
        MessageData data = message.getData();
        Object obj = data.getData();
        String json = JsonUtil.parseObjToJson(obj);
        JSONObject jsonObject = JSONObject.parseObject(json);
        String from = jsonObject.getString("fromPlayerId");
        String to = jsonObject.getString("toPlayerId");

        //message.getData().setCode(222);
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (clientId.equals(item.username)) {
                    item.sendAsyncObject(message);
                    log.info(">>>>>>" + JSONObject.toJSONString(message));
                    item.sendMessage(JSONObject.toJSONString(message));
                } else {
                    // item.sendAsyncObject(message);
                    //item.sendMessage(JSONObject.toJSONString(message));
                    log.info(">>>>>>" + item.username + "[" + item.clientId + "]");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized long getOnlinePlayerCount() {
        return redisUtils.getOnlinePlayerCount();
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
