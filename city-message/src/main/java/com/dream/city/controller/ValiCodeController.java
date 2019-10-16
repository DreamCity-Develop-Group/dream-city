package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.AuthCode;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.CodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Result valiCode(@RequestBody Message message) {
        Map<String, String> data = (Map<String, String>) message.getData().getData();
        String code = data.get("code");
        String phone = data.get("username");
        Message msg;

        boolean ret = Boolean.FALSE;
        String redisCode = null;
        String redisKey = RedisKeys.REDIS_KEY_VALIDCODE + message.getSource();
        if (redisUtils.hasKey(redisKey)) {
            redisCode = redisUtils.getStr(redisKey);
            if (!StringUtils.isEmpty(code) && !StringUtils.isEmpty(redisCode) &&
                    redisCode.equalsIgnoreCase(code)) {
                ret = Boolean.TRUE;
            }
        } else {
            ret = coderService.valid(phone, code);
        }

        if (ret) {
            MessageData data1 = new MessageData(message.getData().getType(), message.getData().getModel());
            data1.setData(Boolean.TRUE);
            msg = new Message(
                    message.getTarget(),
                    message.getSource(),
                    data1,
                    "验证成功！",
                    String.valueOf(System.currentTimeMillis())
            );
            return new Result(false, "验证成功！", 200, msg);
        } else {
            MessageData data2 = new MessageData(message.getData().getType(), message.getData().getModel());
            data2.setData(Boolean.FALSE);
            msg = new Message(
                    message.getTarget(),
                    message.getSource(),
                    data2,
                    "验证失败！",
                    String.valueOf(System.currentTimeMillis())
            );
            return new Result(true, "验证失败！", 200, msg);
        }
    }

    /**
     * 验证验证码
     *
     * @param code
     * @param account
     * @return
     */
    @RequestMapping("/checkCode")
    public Result checkCode(@RequestParam("code")String code,@RequestParam("account")String account) {
        boolean ret = Boolean.FALSE;
        String redisCode = null;
        String redisKey = RedisKeys.REDIS_KEY_VALIDCODE + account;
        if (redisUtils.hasKey(redisKey)) {
            redisCode = redisUtils.getStr(redisKey);
            logger.info("用户验证码："+redisCode);
            if (!StringUtils.isEmpty(code) && !StringUtils.isEmpty(redisCode) &&
                    redisCode.equalsIgnoreCase(code)) {
                ret = Boolean.TRUE;

            }
        } else {
            ret = coderService.valid(account, code);
        }


        if (ret) {
            AuthCode code1 = coderService.getAuthCodeByPhone(account);
            if (code1 != null){
                //更改验证码状态，使其不再可用
                code1.setValid("false");
                Result result = coderService.updateCodeState(code1);
                redisUtils.delete(redisKey);

                if(result.getSuccess()){
                    return Result.result(true, "更新验证码状态成功！", ReturnStatus.SUCCESS.getStatus());
                }
            }

            return Result.result(false, "验证成功！", ReturnStatus.SUCCESS.getStatus());
        } else {
            return Result.result(false, "验证失败！", ReturnStatus.CODE_EXPIRED.getStatus());
        }
    }

    /**
     * 获取验证码
     *
     * @param message
     * @return
     */
    @RequestMapping("/getCode")
    public Result getCode(@RequestBody Message message) {
        String code = String.valueOf(new Random().nextInt(999999));
        MessageData data = new MessageData("getcode", "message");
        data.setData(null);
        Message msg = new Message("source", "server",
                data,
                "获取认证码",
                String.valueOf(System.currentTimeMillis()));

        Map map = (Map) message.getData().getData();
        boolean insertCode = Boolean.FALSE;
        if (null != map && map.containsKey("username")) {
            String account = (String) map.get("username");
            insertCode = coderService.insertCode(account, code);
            if (insertCode) {
                msg.getData().setData(code);
                logger.info("############################## 用户[{}]获取认证码: {}", (String) map.get("username"), code);

                try {
                    //保存10分钟
                    redisUtils.set(RedisKeys.REDIS_KEY_VALIDCODE + account, code);
                    redisUtils.expire(RedisKeys.REDIS_KEY_VALIDCODE + account,600);
                    return Result.result(true,"获取验证码成功",ReturnStatus.SUCCESS.getStatus(),code);
                } catch (Exception e) {
                    logger.error("getCode Exception !", e);
                }
            }
            return Result.result(false,"获取验证码失败",ReturnStatus.FAILED.getStatus(),null);
        }else{
            return Result.result(false,"账号/手机号不能为空",ReturnStatus.INVALID.getStatus(),null);
        }
    }

}
