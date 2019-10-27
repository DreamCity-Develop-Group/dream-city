package com.dream.city.service.consumer.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class SmsServiceImpl implements SmsService {
    @Autowired
    RedisUtils redisUtils;
    @Value("${sms.account}")
    private String account;

    @Value("${sms.url}")
    private String url;

    @Value("${sms.appKey}")
    private String appKey;

    //private static String Url = "http://api.isms.ihuyi.com/webservice/isms.php?method=Submit";


    public static void main(String[] args) {
        String phone  = args[0];
        new SmsServiceImpl().post(phone);

    }

    public void post(String phone) {
        log.info(">>>>>执行[httpClientService]=>Post");
        Message msg = new Message();

        CloseableHttpClient client = HttpClientBuilder.create().build();
        //网关地址

        String gateWayUrl = url;

        //响应模型
        CloseableHttpResponse response = null;
        // 创建Post请求
        HttpPost httpPost = null;
        try {
            int mobile_code = (int)((Math.random()*9+1)*100000);
            httpPost = new HttpPost(gateWayUrl);
            String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

            List<NameValuePair> pairs = new ArrayList<NameValuePair>();

            NameValuePair pair1 = new BasicNameValuePair("account",account);
            NameValuePair pair2 = new BasicNameValuePair("password",appKey );
            NameValuePair pair3 = new BasicNameValuePair("mobile", phone);
            NameValuePair pair4 = new BasicNameValuePair("content", content);

            pairs.add(pair1);
            pairs.add(pair2);
            pairs.add(pair3);
            pairs.add(pair4);

            httpPost.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));


            //String jsonString = JSON.toJSONString(data);

            //StringEntity entity = new StringEntity(jsonString, "UTF-8");

            // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
            //httpPost.setEntity(entity);

            //httpPost.setHeader("Content-Type", "application/json;charset=utf8");
            httpPost.setHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
            //method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");

            // 由客户端执行(发送)Post请求
            /**TODO**********完成客户端请求逻辑******************************/
            response = client.execute(httpPost);
            /**TODO**********完成客户端请求逻辑******************************/

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            log.info("Post=响应状态为:" + response.getStatusLine());
            int responseCode = response.getStatusLine().getStatusCode();
            //TODO 是否完成请求
            if (responseEntity != null && responseCode == ReturnStatus.SUCCESS.getStatus()) {
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

                if (message.getData() == null) {
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
                if (message.getData().getType().equals("login") || message.getData().getType().equals("codeLogin")) {
                    if (message.getData().getCode() == 200) {
                        Object dataGet = message.getData().getData();
                        String json = JsonUtil.parseObjToJson(dataGet);
                        Map map = JsonUtil.parseJsonToObj(json, Map.class);
                        String playerId = map.get("playerId").toString();
                        String playerName = map.get("playerName").toString();
                        Integer id = Integer.valueOf(map.get("id").toString());
                        boolean success = redisUtils.addOnlinePlayer(playerName, id);
                        if (!success) {
                            success = redisUtils.addOnlinePlayer(playerName, id);
                            if (!success) {
                                redisUtils.addOnlinePlayer(playerName, id);
                            }
                        }
                    }

                }
                /**TODO**********完*************************************/


                /**TODO**********完成请求，推送最终数据******************************/

                /**TODO**********完*************************************/

            } else if (responseEntity != null && responseCode == 707) {
                Message message = new Message();
                message.setSource("server");
                message.setTarget(msg.getSource());
                message.setDesc("尚未登录或登录已过期");
                message.setCreatetime(String.valueOf(System.currentTimeMillis()));
                message.setData(
                        new MessageData(
                                "token",
                                msg.getData().getModel(), null,
                                ReturnStatus.TOKEN_EXPIRED.getStatus()
                        )
                );


            } else {
                /**TODO**********完成任务创建***********未完成相应请求，创建任务*******************/

                /**TODO**********完成任务创建******************************/
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
