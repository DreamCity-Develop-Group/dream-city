package com.dream.city.controller;

import com.dream.city.domain.Message;
import com.dream.city.domain.MessageData;
import com.dream.city.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

/**
 * @author Wvv
 */
@RestController
@RequestMapping("/message")
public class ValiCodeController {
    @Autowired
    CodeService coderService;

    @RequestMapping("/getValiCode")
    public Message getValiCode(@RequestBody Message msg) {
        String code = String.valueOf(new Random().nextInt(999999));
        String target = msg.getTarget();
        msg.setTarget(msg.getSource());
        msg.setSource(target);
        msg.setData(new MessageData("getValiCode", "message", code));
        return msg;
    }

    @RequestMapping("/valiCode")
    public Message valiCode(@RequestBody Message message) {
        Map<String, String> data = (Map<String, String>) message.getData().getT();
        String code = data.get("code");
        String phone = data.get("phone");

        boolean ret = coderService.valid(phone, code);
        if (ret) {
            return new Message(
                    message.getTarget(),
                    message.getSource(),
                    new MessageData(message.getData().getType(), message.getData().getModel(), null),
                    "验证成功！",
                    String.valueOf(System.currentTimeMillis())
            );
        } else {
            return new Message(
                    message.getTarget(),
                    message.getSource(),
                    new MessageData(message.getData().getType(), message.getData().getModel(), null),
                    "验证失败！",
                    String.valueOf(System.currentTimeMillis())
            );
        }
    }

    @RequestMapping("/get/code")
    public Message getCode() {
        String code = String.valueOf(new Random().nextInt(999999));
        Message msg = new Message("source", "target", new MessageData("testMsg", "message", code), "this is msg", String.valueOf(System.currentTimeMillis()));
        return msg;
    }

}
