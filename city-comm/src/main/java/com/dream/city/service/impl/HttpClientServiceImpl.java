package com.dream.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.MessageData;
import com.dream.city.config.GateWayConfig;
import com.dream.city.base.model.Message;
import com.dream.city.server.Prop;
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


    @Override
    public void send(Message message) {
        String gateWayUrl = gateWayConfig.getUrl();
        if (null == gateWayUrl){
            System.out.println("simpleProp: " + myProps.getSimpleProp());
            System.out.println("arrayProps: " + (myProps.getArrayProps()));
            System.out.println("listProp1: " + (myProps.getListProp1()));
            System.out.println("listProp2: " + (myProps.getListProp2()));
            System.out.println("mapProps: " + (myProps.getMapProps()));
        }
        System.out.println(appName);
        String url1 = gateWayConfig.getUrl();
        System.out.println(url1);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();


        String gateWayPath = message.getTarget();
        String serviceModel = message.getTarget();
        String serviceOpt = message.getData().getType();

        String url = gateWayUrl + "/" +gateWayPath + "/" + serviceModel + "/" + serviceOpt;
        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);

        String jsonString = JSON.toJSONString(message);

        StringEntity entity = new StringEntity(jsonString, "UTF-8");

        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        //响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            int code = response.getStatusLine().getStatusCode();
            if (responseEntity != null && code == 200) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println(responseEntity.getContent());
                String resp = EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:" + resp);

                Message msg = JSON.parseObject(resp, Message.class);

                //WebSocketServer.sendInfo(msg);
                log.info("加入任务成功！");
                System.out.println(msg.getDesc());
            }else{
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

            //请求地址url
            String url = gateWayUrl + "/" +  serviceModel + "/" + serviceOpt;

            httpPost = new HttpPost(url);
            if ("login".equals(serviceOpt) || "reg".equals(serviceOpt) || "getCode".equals(serviceOpt)) {
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
                        httpPost.setHeader("method",serviceOpt);
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
            response = client.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseEntity != null && responseCode==200) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println(responseEntity.getContent());
                String resp = EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:" + resp);

                Message message = JSON.parseObject(resp, Message.class);
                System.out.println(message.toString());
                //message.setData(new MessageData());
                message.setSource("server");
                message.setTarget(msg.getSource());
                message.getData().setType(msg.getData().getType());
                message.getData().setModel(msg.getData().getModel());

                if (resp.contains("data") && resp.contains("t")) {
                    Object jsonObject = JSON.parseObject(JSON.toJSONString(JSON.parseObject(resp).get("data"))).get("t");
                    message.getData().setData(jsonObject);
                }

                // TODO 推送消息到客户端
                WebSocketServer.sendInfo(message);
            }else {
                // TODO ===> 调用自方法，创建任务处理
                Map<String,Object> job = new HashMap<>();
                //要做的任务
                job.put("todo","jobCreate");
                //任务完成推送的对象
                job.put("applyTo",msg.getSource());
                //job任务的源数据
                job.put("sourceData",msg);
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
}
