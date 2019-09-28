package com.dream.city.controller;

import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.service.ConsumerMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
}
