package com.dream.city.controller;

import com.dream.city.base.utils.RedisUtils;
import com.dream.city.base.model.MessageData;
import com.dream.city.domain.vo.ValiCode;
import com.dream.city.server.WebSocketServer;
import com.dream.city.domain.ApiReturnObject;
import com.dream.city.base.model.Message;
import com.dream.city.service.HttpClientService;
import com.dream.city.util.ApiUtil;
import com.dream.city.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author Wvv
 */
@RestController
@RequestMapping("/push")
public class PushController {
    @Autowired
    HttpClientService httpClientService;
    @Autowired
    RedisUtils redisUtils;
    /**
     * 页面请求
     */

    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav = new ModelAndView("/socket");
        mav.addObject("cid", cid);
        return mav;
    }

    /**
     * 推送数据接口
     *
     * @param message
     * @return
     */
    @ResponseBody
    @RequestMapping("/socket/push/")
    public ApiReturnObject pushToWeb(@RequestBody Message message) {
        if (message == null) {
            return ApiUtil.error("发送了错误消息!");
        }
        try {
            //ApiSendObject msg = ApiUtil.pack(message);
            WebSocketServer.sendInfo(message);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiUtil.error(message.getTarget() + "#" + e.getMessage());
        }
        return ApiUtil.success(message.getTarget());
    }

    @ResponseBody
    @RequestMapping("/socket/client/{client}")
    public Message pushTo(@PathVariable("client")String client) {
        Message message = new Message();
        ValiCode valiCode = new ValiCode("1378885471","256488");
        MessageData<ValiCode> messageData = new MessageData<>();
        if (null == client){
            message.setDesc("client不能为空");
        }

        messageData.setT(valiCode);
        message.setTarget(client);
        message.setSource("Server");
        message.setCreatetime(String.valueOf(System.currentTimeMillis()));
        message.setDesc("获取验证码成功！");
        message.setData(messageData);

        httpClientService.post(message);

        return  message;
    }

    @RequestMapping("/redis")
    public Message setMessage(){


        Message message = new Message();
        ValiCode valiCode = new ValiCode("1378885471","256488");
        MessageData<ValiCode> messageData = new MessageData<>();


        messageData.setT(valiCode);
        message.setTarget("client");
        message.setSource("Server");
        message.setCreatetime(String.valueOf(System.currentTimeMillis()));
        message.setDesc("获取验证码成功！");
        message.setData(messageData);

        redisUtils.set("msgg", JsonUtil.parseObjToJson(message));

        return message;
    }

}
