package com.dream.city.service.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.service.consumer.ConsumerMessageService;
import com.dream.city.service.handler.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wvv
 * @program: dream-city
 * @File: MessageServiceImpl
 * @description: 消息和验证码服务类
 * @create: 2019/10/2019/10/27 23:05:08 [星期日]
 **/

@Slf4j
@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    @Autowired
    private ConsumerMessageService messageService;


    /**
     * 获取认证码
     *
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message getCode(Message msg) throws BusinessException {
        log.info("获取认证码:" + JSONObject.toJSONString(msg));
        Map<String, Object> map = new HashMap<>();
        Result code = messageService.getCode(msg);
        MessageData messageData = new MessageData(msg.getData().getType(), msg.getData().getModel());
        Message message = new Message(msg.getSource(), msg.getTarget(), messageData);

        map.put("code", code.getData());
        messageData.setData(map);
        message.setDesc(code.getMsg());
        return message;
    }

    /**
     * 验证获取的码
     *
     * @param code
     * @param account
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message checkCode(String code, String account) throws BusinessException {
        MessageData messageData = new MessageData("checkCode", "consumer/message");
        Message message = new Message();
        if (StringUtils.isBlank(code) || StringUtils.isBlank(account)) {
            message.setDesc("参数为空");
            message.setData(messageData);
            message.setCreatetime(String.valueOf(System.currentTimeMillis()));
            message.setTarget("");
            message.setSource("server");
            return message;
        }
        Result result = messageService.checkCode(code, account);

        message.setDesc(result.getMsg());
        message.setData(messageData);
        message.setCreatetime(String.valueOf(System.currentTimeMillis()));
        message.setTarget("");
        message.setSource("server");

        return message;
    }
}
