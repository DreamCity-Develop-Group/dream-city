package com.dream.city.base.utils;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author Wvv
 */
public class HttpClientUtil {

    private String initPost(Message msg){
        //网关地址
        String gateWayUrl = "http://localhost:8020/consumer";
        MessageData data = msg.getData();
        //网关路由
        String gateRoutePath = "v1";
        //模块地址
        String serviceModel = data.getModel();
        //请求模块操作行为
        String serviceOpt = data.getType();

        //请求地址url
        String url = gateWayUrl + "/" + gateRoutePath + "/" + serviceModel + "/" + serviceOpt;

        return url;
    }

    public static void post(Message msg) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        //网关地址
        String gateWayUrl = "http://localhost:8020/consumer";
        MessageData data = msg.getData();
        //网关路由
        String gateRoutePath = "v1";
        //模块地址
        String serviceModel = data.getModel();
        //请求模块操作行为
        String serviceOpt = data.getType();

        //请求地址url
        //String url = gateWayUrl + "/" + gateRoutePath + "/" + serviceModel + "/" + serviceOpt;

        //响应模型
        CloseableHttpResponse response = null;
        // 创建Post请求
        HttpPost httpPost = null;
        try {
            //参数错误
            if (StringUtils.isEmpty(serviceOpt)) {
                msg.setSource("server");
                msg.setTarget(msg.getSource());
                msg.getData().setType(msg.getData().getType());
                msg.getData().setModel(msg.getData().getModel());
                msg.setDesc("参数错误");
               // WebSocketServer.sendInfo(message);
            }

            //请求地址url
            String url = gateWayUrl + "/" + gateRoutePath + "/" + serviceModel + "/" + serviceOpt;

            httpPost = new HttpPost(url);

            if (serviceOpt.equals("login") || serviceOpt.equals("reg") || serviceOpt.equals("getCode")||serviceOpt.equals("jobPush")) {
                //这里不处理，表示正常放行
                httpPost.setHeader("method", serviceOpt);
                httpPost.setHeader("authType", "");
            } else {
                if (null != data.getData()) {
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
            if (responseEntity != null) {
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

                if (resp.contains("data") && resp.contains("data")) {
                    Object jsonObject = JSON.parseObject(JSON.toJSONString(JSON.parseObject(resp).get("data"))).get("data");
                    message.getData().setData(jsonObject);
                }

                //WebSocketServer.sendInfo(message);
                return;
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
