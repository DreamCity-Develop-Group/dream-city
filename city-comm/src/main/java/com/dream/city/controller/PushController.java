package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Notice;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.base.model.MessageData;
import com.dream.city.config.GateWayConfig;
import com.dream.city.domain.vo.ValiCode;
import com.dream.city.server.WebSocketServer;
import com.dream.city.domain.ApiReturnObject;
import com.dream.city.base.model.Message;
import com.dream.city.service.HttpClientService;
import com.dream.city.util.ApiUtil;
import com.dream.city.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author Wvv
 */
@RestController
@RequestMapping("/comm")
public class PushController {
    @Autowired
    HttpClientService httpClientService;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    GateWayConfig gateWayConfig;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 页面请求
     */

    @GetMapping("/gate")
    public String getGateUrl() {
        //GateWayConfig gateWayConfig = new GateWayConfig();
        MessageData data = new MessageData("test", "server", gateWayConfig);
        Message message = new Message("client", "server", data, "desc", String.valueOf(System.currentTimeMillis()));
        httpClientService.post(message);

        return gateWayConfig.getUrl();
    }

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
    public Message pushTo(@PathVariable("client") String client) {
        Message message = new Message();
        ValiCode valiCode = new ValiCode("1378885471", "256488");
        MessageData<ValiCode> messageData = new MessageData<>();
        if (null == client) {
            message.setDesc("client不能为空");
        }

        messageData.setData(valiCode);
        message.setTarget(client);
        message.setSource("Server");
        message.setCreatetime(String.valueOf(System.currentTimeMillis()));
        message.setDesc("获取验证码成功！");
        message.setData(messageData);

        httpClientService.post(message);

        return message;
    }

    @RequestMapping("/redis")
    public Message setMessage() {


        Message message = new Message();
        ValiCode valiCode = new ValiCode("1378885471", "256488");
        MessageData<ValiCode> messageData = new MessageData<>();


        messageData.setData(valiCode);
        message.setTarget("client");
        message.setSource("Server");
        message.setCreatetime(String.valueOf(System.currentTimeMillis()));
        message.setDesc("获取验证码成功！");
        message.setData(messageData);

        redisUtils.set("msgg", JsonUtil.parseObjToJson(message));

        return message;
    }

    @RequestMapping("/job/")
    public Message jobPush(@RequestBody Message message) {
        try {
            WebSocketServer.sendInfo(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return message;
    }

    @RequestMapping("/notice")
    public Result noticeAll() {
        //redisUtils.c
        Notice notice = new Notice();
        notice.setNoticeId(123);
        notice.setNoticeContent("你好啊，这是广播！！！");
        String channel = "notice";
        Message message = new Message(
                "server",
                "clients",
                new MessageData("notice", "notice", notice),
                "这是所有客户端的广播",
                String.valueOf(System.currentTimeMillis()));


        redisTemplate.convertAndSend(channel, com.dream.city.base.utils.JsonUtil.parseObjToJson(message));

        //redisTemplate.convertAndSend(channel,message);
        return new Result(true, "成功！", 200, notice);
    }

    @RequestMapping("job/push/notice")
    public Result pushNotice(@RequestBody Message message) {
        JSONObject jsonObject = JSONObject.parseObject(com.dream.city.base.utils.JsonUtil.parseObjToJson(message.getData().getData()));
        String channel = jsonObject.getString("channel");


        redisTemplate.convertAndSend(channel, com.dream.city.base.utils.JsonUtil.parseObjToJson(message));

        return new Result(true, "成功！", 200, message);
    }
}
