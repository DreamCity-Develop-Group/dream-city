package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.service.handler.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wvv
 */

@RestController
@RequestMapping("/consumer/message")
@Slf4j
public class ConsumerMessageController {
    @Autowired
    private MessageService messageService;


    /**
     * 获取认证码
     *
     * @param msg
     * @return
     */
    @RequestMapping("/getCode")
    public Message getCode(@RequestBody Message msg) {
        try {
            return messageService.getCode(msg);
        }catch (Exception e){
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }

    /**
     * 验证获取的码
     * @param code
     * @param account
     * @return
     */
    @RequestMapping("/checkCode")
    public Message checkCode(@RequestParam("code")String code,@RequestParam("phone")String account) {
        Message msg = new Message("Server","client",new MessageData(
                "checkCode",
                "consumer/message",
                ReturnStatus.FAILED.getStatus()
        ));
        try {
            return messageService.checkCode(code,account);
        }catch (Exception e){
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            msg.setDesc(e.getMessage());
            return msg;
        }
    }



}
