package com.dream.city.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Wvv
 */
@Component
@Slf4j
public class MessageReceiver {

    @Autowired
    WorkerService workerService;


    /**
     * 接收到的message 经外层转换后的Message
     * {
     *     "createtime":"1569745081608",
     *     "desc":"递交任务，创建业务执行任务",
     *     "source":"commCenter",
     *     "target":"worker",
     *     "data":{
     *         "model":"worker",
     *         "type":"createWorker"
     *         "data":{
     *             "todo":"jobCreate",
     *             "applyTo":"15911-dd",
     *             "channel":"CHANNEL_LISTENER_PLATTRANS",
     *             "sourceData":{
     *                 "createtime":"Wed Sep 04 14:31:01 CST 2019",
     *                 "data":{
     *                     "data":{
     *                         "username":"",
     *                         "token":"eyJjoXCP55c_JoRIg"
     *                     },
     *                     "model":"consumer/message",
     *                     "type":"getCode"
     *                 },
     *                 "desc":"验证码",
     *                 "source":"15911-dd",
     *                 "target":"14863-1"
     *             }
     *         }
     *
     *     }
     * }
     */

    /**
     * 用于平台业务
     *
     * @param message
     */
    public void listenerAdapterPlatTrans(String message) {
        System.out.println("listenerAdapterPlatTrans收到一条消息：");
        log.info(message);
        Message msgOuter = genericMessageOuter(message);
        MessageData data = msgOuter.getData();
        String model = data.getModel();
        String serviceOpt = data.getType();
        String json = JsonUtil.parseObjToJson(data.getData());
        JSONObject jsonObject = JSON.parseObject(json);

        Message msgInner = genericMessageInner(jsonObject);

        /**
         * TODO 解析数据包，完成相应的逻辑
         * 1、创建任务，由任务去完成后续的业务逻辑
         * 2、一旦任务完成相应的业务逻辑，检测客户端连接，发送即时通知到客户端
         * 3、生成相应的数据表通知，等待用户查收
         */
        //workerService.addJob();





    }

    /**
     * 用于服务推送
     *
     * @param message
     */
    public void listenerAdapterServerPush(String message) {
        System.out.println("listenerAdapterServerPush收到一条消息：");
        log.info(message);
        //log.info(JsonUtil.parseObjToJson(message));
    }

    /**
     * 用于账户出入
     *
     * @param message
     */
    public void listenerAdapterLogin(String message) {
        log.info("listenerAdapterLogin收到登录消息");
        log.info(message);
        //log.info(JsonUtil.parseObjToJson(message));
    }

    private Message genericMessageOuter(String messageStringWithDash) {
        String message1 = messageStringWithDash.replace("\\","");
                String message2 = message1.substring(1, message1.length() - 1);
        Message msg = JsonUtil.parseJsonToObj(message2, Message.class);

        log.info(msg.getDesc());
        return msg;
    }

    private Message genericMessageInner(Map message) {
        String msgStr = JsonUtil.parseObjToJson(message.get("sourceData"));
        Message msg = JsonUtil.parseJsonToObj(msgStr,Message.class);
        log.info(msg.getDesc());
        return msg;
    }

}
