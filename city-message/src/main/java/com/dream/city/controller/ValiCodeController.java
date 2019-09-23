package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.CodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    private static final Logger logger = LoggerFactory.getLogger(ValiCodeController.class);
    @Autowired
    CodeService coderService;
    @Autowired
    RedisUtils redisUtils;

    @RequestMapping("/getValiCode")
    public Message getValiCode(@RequestBody Message msg) {
        String code = String.valueOf(new Random().nextInt(999999));
        String target = msg.getTarget();
        msg.setTarget(msg.getSource());
        msg.setSource(target);

        MessageData data = new MessageData("getValiCode", "message");
        data.setData(code);
        msg.setData(data);
        return msg;
    }

    @RequestMapping("/valiCode")
    public Message valiCode(@RequestBody Message message) {
        Map<String, String> data = (Map<String, String>) message.getData().getData();
        String code = data.get("code");
        String phone = data.get("username");

        boolean ret =Boolean.FALSE;
        String redisCode = null;
        String redisKey = RedisKeys.REDIS_KEY_VALIDCODE + message.getSource();
        if (redisUtils.hasKey(redisKey)){
            redisCode = redisUtils.getStr(redisKey);
            if (!StringUtils.isEmpty(code) && !StringUtils.isEmpty(redisCode) &&
                    redisCode.equalsIgnoreCase(code)){
                ret =Boolean.TRUE;
            }
        }else {
            ret = coderService.valid(phone, code);
        }

        if (ret) {
            MessageData data1 = new MessageData(message.getData().getType(), message.getData().getModel());
            data1.setData(Boolean.TRUE);
            return new Message(
                    message.getTarget(),
                    message.getSource(),
                    data1,
                    "验证成功！",
                    String.valueOf(System.currentTimeMillis())
            );
        } else {
            MessageData data2 = new MessageData(message.getData().getType(), message.getData().getModel());
            data2.setData(Boolean.FALSE);
            return new Message(
                    message.getTarget(),
                    message.getSource(),
                    data2,
                    "验证失败！",
                    String.valueOf(System.currentTimeMillis())
            );
        }
    }

    @RequestMapping("/getCode")
    public Message getCode(@RequestBody Message message) {
        String code = String.valueOf(new Random().nextInt(999999));
        MessageData data = new MessageData("getcode", "message");
        data.setData(null);
        Message msg = new Message("source", "server",
                data,
                "获取认证码",
                String.valueOf(System.currentTimeMillis()));

        Map map = (Map) message.getData().getData();
        boolean insertCode = Boolean.FALSE;
        if (map.containsKey("username")){
            insertCode = coderService.insertCode((String) map.get("username"), code);
        }
        if (insertCode){
            msg.getData().setData(code);
            logger.info("############################## 用户[{}]获取认证码: {}",(String) map.get("username"),code);

            try {
                //保存10分钟
                redisUtils.set(RedisKeys.REDIS_KEY_VALIDCODE+message.getSource(),code,600);
            }catch (Exception e){
                logger.error("getCode Exception !",e);
            }
        }
        return msg;
    }

}
