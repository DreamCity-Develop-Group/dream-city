package com.dream.city.controller;

import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.service.CityMessageService;
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
    private CityMessageService messageService;


    /**
     * 获取认证码
     *
     * @param msg
     * @return
     */
    @RequestMapping("/getCode")
    public Result getCode(@RequestBody Message msg) {
        log.info("获取认证码:"+JSONObject.toJSONString(msg));
        Map<String, Object> map = new HashMap<>();
        Message code = messageService.getCode(msg);
        map.put("code", code.getData().getData());
        MessageData messageData = new MessageData(msg.getData().getType(),msg.getData().getModel());
        messageData.setData(map);
        Message message = new Message(msg.getSource(), msg.getTarget(), messageData);


        return new Result(true,"验证码",200,message);
    }

    /**
     * 验证获取的码
     * @param code
     * @param account
     * @return
     */
    @RequestMapping("/checkCode")
    public Result checkCode(@RequestParam("code")String code,@RequestParam("phone")String account) {
        if (StringUtils.isBlank(code) || StringUtils.isBlank(account)){
            return Result.result(false,"参数为空",500);
        }
        Result result = messageService.checkCode(code,account);

        return result;
    }
}
