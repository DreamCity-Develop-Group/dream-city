package com.dream.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.config.GateWayConfig;
import com.dream.city.base.model.Message;
import com.dream.city.server.Prop;
import com.dream.city.server.PublishServer;
import com.dream.city.server.WebSocketServer;
import com.dream.city.service.HttpClientService;
import com.dream.city.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wvv
 */
@Slf4j
@Service
public class HttpClientServiceImpl implements HttpClientService {
    @Autowired
    private GateWayConfig gateWayConfig;

    @Autowired
    private Prop myProps;

    @Value(value = "${spring.application.name}")
    private String appName;

    @Value(value = "${gate.zuul.url}")
    private String getWayUrl;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    PublishServer publishServer;

    /**
     * 登陆，注册，验证码，心跳，退出等
     */
    private static final String CHANNEL_LISTENER_LOGIN = "CHANNEL_LISTENER_LOGIN";
    /**
     * 平台业务逻辑
     */
    private static final String CHANNEL_LISTENER_PLATRANS = "CHANNEL_LISTENER_PLATTRANS";
    /**
     * 后台推送
     */
    private static final String CHANNEL_LISTENER_SEREVERPUSH = "CHANNEL_LISTENER_SEREVERPUSH";


    @Override
    public void send(Message message) {
        String gateWayUrl = gateWayConfig.getUrl();
        if (null == gateWayUrl) {
            log.info("simpleProp: " + myProps.getSimpleProp());
            log.info("arrayProps: " + (myProps.getArrayProps()));
            log.info("listProp1: " + (myProps.getListProp1()));
            log.info("listProp2: " + (myProps.getListProp2()));
            log.info("mapProps: " + (myProps.getMapProps()));
        }
        log.info("App-Name:" + appName);
        String url1 = gateWayConfig.getUrl();
        log.info("GateWay-Url:" + url1);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String gateWayPath = "consumer";
        String serviceModel = message.getTarget();
        String serviceOpt = message.getData().getType();

        String url = gateWayUrl + "/" + gateWayPath + "/" + serviceModel + "/" + serviceOpt;
        log.info("Request-Url:" + url);
        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);

        String jsonString = JSON.toJSONString(message.getData().getData());
        String channel = "";
        Object messageSource ="";
        String obj = JsonUtil.parseObjToJson(message.getData().getData());
        Map map = JSON.parseObject(jsonString);
        channel = map.get("channel").toString();
        messageSource = map.get("sourceData");

        String json = JsonUtil.parseObjToJson(messageSource);

        StringEntity entity = new StringEntity(json, "UTF-8");

        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        httpPost.setHeader("method", serviceOpt);
        httpPost.setHeader("authType", "");

        //响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            log.info("Send=响应状态为:" + response.getStatusLine());
            int code = response.getStatusLine().getStatusCode();
            if (responseEntity != null && code == ReturnStatus.SUCCESS.getStatus()) {
                log.info("Send=响应内容长度为:" + responseEntity.getContentLength());
                log.info(responseEntity.getContent().toString());
                String resp = EntityUtils.toString(responseEntity);
                log.info("Send=响应内容为:" + resp);

                Message msg = JSON.parseObject(resp, Message.class);

                //WebSocketServer.sendInfo(msg);
                log.info("加入任务成功！");
                log.info(msg.getDesc());
            } else {
                // 由客户端执行(发送)Post请求
                /**TODO**********将不成功的任务放入到redis，由任务中心自己调度处理******************************/
                //JSONObject jsonObject = JSON.parseObject(JsonUtil.parseObjToJson(message.getData().getData()));

                //String channel = jsonObject.getString("channel");
                String messageJson = JSON.toJSONString(message);
                publishServer.publishMessage(channel,messageJson);
                /**TODO**********不成功的任务放入到redis******************************/
                log.info("加入任务失败!");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Async
    @Override
    public void post(Message msg) {
        log.info(">>>>>执行[httpClientService]=>Post");
        log.info("{}",msg);

        CloseableHttpClient client = HttpClientBuilder.create().build();
        //网关地址
        String gateWayUrl = gateWayConfig.getUrl();
        MessageData data = msg.getData();
        //网关路由
        //String gateRoutePath = "";
        //模块地址
        String serviceModel = data.getModel();
        //请求模块操作行为
        String serviceOpt = data.getType();
        //响应模型
        CloseableHttpResponse response = null;
        // 创建Post请求
        HttpPost httpPost = null;
        try {

            //参数错误
            if (StringUtils.isEmpty(serviceOpt)) {
                Message message = msg;
                message.setSource("server");
                message.setTarget(msg.getSource());
                message.getData().setType(msg.getData().getType());
                message.getData().setModel(msg.getData().getModel());
                message.setDesc("参数错误");
                WebSocketServer.sendInfo(message);
                return;
            }
            log.info(">>>>>执行[httpClientService]=>Post["+serviceModel + "/" + serviceOpt+"]");

            //请求地址url
            String url = gateWayUrl + "/" + serviceModel + "/" + serviceOpt;
            log.info("Request-Url:" + url);

            httpPost = new HttpPost(url);
            if ("exit".equals(serviceOpt) || "login".equals(serviceOpt) || "reg".equals(serviceOpt)
                    || "getCode".equals(serviceOpt) || "codeLogin".equals(serviceOpt) || "pwforget".equals(serviceOpt)) {
                //这里不处理，表示正常放行
                httpPost.setHeader("method", serviceOpt);
                httpPost.setHeader("authType", "");
            } else {
                if (data.getData() != null) {
                    String dataStr = JsonUtil.parseObjToJson(data.getData());
                    Map dataMap = JsonUtil.parseJsonToObj(dataStr, Map.class);

                    if (dataMap.containsKey("token") && !StringUtils.isEmpty(dataMap.get("token"))) {
                        String token = dataMap.get("token").toString();
                        String username = dataMap.get("username").toString();
                        httpPost.setHeader("authType", "Bearer");
                        httpPost.setHeader("username", username);
                        httpPost.setHeader("method", serviceOpt);
                        httpPost.setHeader("Authorization", "Bearer " + token);
                    }
                }
            }

            String jsonString = JSON.toJSONString(msg);

            StringEntity entity = new StringEntity(jsonString, "UTF-8");

            // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/json;charset=utf8");


            // 由客户端执行(发送)Post请求
            /**TODO**********完成客户端请求逻辑******************************/
            response = client.execute(httpPost);
            /**TODO**********完成客户端请求逻辑******************************/

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            log.info("Post=响应状态为:" + response.getStatusLine());
            int responseCode = response.getStatusLine().getStatusCode();
            //TODO 是否完成请求
            if (responseEntity != null) {
                if (responseCode == ReturnStatus.SUCCESS.getStatus()){
                    log.info("Post=响应内容长度为:" + responseEntity.getContentLength());
                    log.info("Post=>内容：" + responseEntity.getContent().toString());
                    String resp = EntityUtils.toString(responseEntity);
                    log.info("Post=响应内容为:" + resp);

                    //转换请求的反馈
                    Message message = JSON.parseObject(resp, Message.class);
                    log.info("=================================message==================================");
                    log.info(message.toString());
                    log.info("===========================^^^^==message======^^^^^=====================");
                    //WebSocketServer.sendInfo(message);
                    //message.setData(new MessageData());
                    message.setSource("server");
                    message.setTarget(msg.getSource());
                    message.setDesc("响应数据回复");
                    message.setCreatetime(String.valueOf(System.currentTimeMillis()));

                    if(message.getData() == null) {
                        MessageData msgData = new MessageData();
                        msgData.setType(msg.getData().getType());
                        msgData.setModel(msg.getData().getModel());
                        msgData.setData(null);

                        message.setData(msgData);
                    }

                    if (resp.contains("data")) {
                        String json = JSON.toJSONString(JSON.parseObject(resp).get("data"));
                        JSONObject jsonObject = JSON.parseObject(json);

                        log.info("=================================message==================================");
                        log.info(message.toString());
                        log.info(jsonObject.getString("data"));
                        log.info("===========================^^^^==message======^^^^^=====================");
                    }
                    /**TODO**********如果是登陆成功，加入在线有序列表******************************/
                    if (message.getData().getType().equals("login") || message.getData().getType().equals("codeLogin")){
                        if (message.getData().getCode() == ReturnStatus.SUCCESS.getStatus()){
                            Object dataGet = message.getData().getData();
                            String json = JsonUtil.parseObjToJson(dataGet);
                            Map map = JsonUtil.parseJsonToObj(json,Map.class);
                            String playerId = map.get("playerId").toString();
                            String playerName = map.get("playerName").toString();
                            Integer id = Integer.valueOf(map.get("id").toString());
                            boolean success = redisUtils.addOnlinePlayer(playerName,id);
                            if (!success){
                                success = redisUtils.addOnlinePlayer(playerName,id);
                                if (!success){
                                    redisUtils.addOnlinePlayer(playerName,id);
                                }
                            }
                        }

                    }
                    /**TODO**********完*************************************/


                    /**TODO**********完成请求，推送最终数据******************************/
                    WebSocketServer.sendInfo(message);
                    /**TODO**********完*************************************/
                }else if (responseCode == ReturnStatus.ERROR_CODE.getStatus()){
                    log.info("超时....");
                }else if(responseCode == ReturnStatus.GATEWAY_TOKEN_ERROR.getStatus()){
                    Message message = new Message();
                    message.setSource("server");
                    message.setTarget(msg.getSource());
                    message.setDesc("尚未登录或登录已过期");
                    message.setCreatetime(String.valueOf(System.currentTimeMillis()));
                    message.setData(
                            new MessageData(
                                    "token",
                                    msg.getData().getModel(),null,
                                    ReturnStatus.TOKEN_EXPIRED.getStatus()
                            )
                    );

                    WebSocketServer.sendInfo(message);
                } else {
                    /**TODO**********完成任务创建***********未完成相应请求，创建任务*******************/
                    createWork(msg);
                    /**TODO**********完成任务创建******************************/
                }


            }else {
                log.info("请求出错 ... ");
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (client != null) {
                    client.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 需要后台
     * @param message
     */
    private void createWork(Message message) {
        // TODO ===> 调用自方法，创建任务处理
        Map<String, Object> job = new HashMap<>();
        //要做的任务
        job.put("todo", "jobCreate");
        //任务完成推送的对象
        job.put("applyTo", message.getSource());
        //job任务的源数据
        job.put("sourceData", message);
        //对应的处理Channel
        job.put("channel",CHANNEL_LISTENER_PLATRANS);


        Message jobMsg = new Message();
        MessageData messageData = new MessageData();

        messageData.setData(job);
        messageData.setType("createWorker");
        messageData.setModel("worker");

        //设置任务数据
        jobMsg.setData(messageData);
        //来源于消息中心
        jobMsg.setSource("commCenter");
        //任务中心
        jobMsg.setTarget("worker");
        //描述
        jobMsg.setDesc("递交任务，创建业务执行任务");
        jobMsg.setCreatetime(String.valueOf(System.currentTimeMillis()));
        send(jobMsg);
    }
}
