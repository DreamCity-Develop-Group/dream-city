package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityMessage;
import com.dream.city.service.ConsumerMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 */

@RestController
@RequestMapping("/consumer/message")
@Slf4j
public class ConsumerMessageController {
    @Autowired
    private ConsumerMessageService messageService;


    /**
     * 获取认证码
     *
     * @param msg
     * @return
     */
    @RequestMapping("/getCode")
    public Message getCode(@RequestBody Message msg) {
        log.info("获取认证码:"+JSONObject.toJSONString(msg));
        Map<String, Object> map = new HashMap<>();
        Result code = messageService.getCode(msg);
        MessageData messageData = new MessageData(msg.getData().getType(),msg.getData().getModel());
        Message message = new Message(msg.getSource(), msg.getTarget(), messageData);

        map.put("code", code.getData());
        messageData.setData(map);
        message.setDesc(code.getMsg());
       return message;
    }

    /**
     * 验证获取的码
     * @param code
     * @param account
     * @return
     */
    @RequestMapping("/checkCode")
    public Message checkCode(@RequestParam("code")String code,@RequestParam("phone")String account) {
        MessageData messageData = new MessageData("checkCode","consumer/message");
        Message message = new Message();
        if (StringUtils.isBlank(code) || StringUtils.isBlank(account)){
            message.setDesc("参数为空");
            message.setData(messageData);
            message.setCreatetime(String.valueOf(System.currentTimeMillis()));
            message.setTarget("");
            message.setSource("server");
            return message;
        }
        Result result = messageService.checkCode(code,account);

        message.setDesc(result.getMsg());
        message.setData(messageData);
        message.setCreatetime(String.valueOf(System.currentTimeMillis()));
        message.setTarget("");
        message.setSource("server");

        return message;
    }


    @RequestMapping("/readMessage")
    public Message readMessage(@RequestBody Message msg) {
        CityMessage message = getCityMessage(msg);
        Result<CityMessage> result = messageService.getMessageById(message.getId());
        Map map = new HashMap();
        if (result != null){
            CityMessage cityMessage = result.getData();
            if (cityMessage != null){
                map.put("id",cityMessage.getId());
                map.put("title", cityMessage.getTitle());
                map.put("createtime",cityMessage.getCreateTime());
                map.put("content",cityMessage.getContent());
                map.put("readState",cityMessage.getHaveRead()==0?false:true);

                messageService.updateMessageHaveReadById(cityMessage);
            }
        }
        msg.setDesc(result.getMsg());
        msg.getData().setData(map);
        return msg;
    }


    @RequestMapping("/getMessageList")
    public Message getMessageList(@RequestBody Message msg) {
        CityMessage message = getCityMessage(msg);
        Result<List<CityMessage>> result = messageService.getCityMessageList(message);

        List<Map> resultList = new ArrayList<>();
        if (result != null){
            List<CityMessage> messageList = result.getData();
            if (!CollectionUtils.isEmpty(messageList)){
                Map map = new HashMap();
                for (CityMessage cityMessage:messageList){
                    map.put("id",cityMessage.getId());
                    map.put("title", cityMessage.getTitle());
                    map.put("createtime",cityMessage.getCreateTime());
                    map.put("content",cityMessage.getContent());
                    map.put("readState",cityMessage.getHaveRead()==0?false:true);
                    resultList.add(map);
                }
            }
        }
        msg.setDesc(result.getMsg());
        msg.getData().setData(resultList);
        return msg;
    }



    private CityMessage getCityMessage(Message msg){
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(msg));
        Long id = Long.parseLong(jsonObject.containsKey("id")?jsonObject.getString("id"):"0");
        String playerId = jsonObject.containsKey("playerId")?jsonObject.getString("playerId"):null;
        String friendId = jsonObject.containsKey("friendId")?jsonObject.getString("friendId"):null;
        String title = jsonObject.containsKey("title")?jsonObject.getString("title"):null;
        String haveReadStr = jsonObject.containsKey("readState")?jsonObject.getString("readState"):null;
        Boolean haveRead = StringUtils.isBlank(haveReadStr)?null:Boolean.parseBoolean(haveReadStr);
        id = id == 0? null: id;
        CityMessage message = new CityMessage();
        message.setHaveRead(haveRead == null? null: (haveRead==Boolean.TRUE)?1:0);
        message.setTitle(title);
        message.setPlayerId(playerId);
        message.setFriendId(friendId);
        message.setId(id);
        return message;
    }

}
