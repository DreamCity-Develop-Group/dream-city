package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.req.UserReq;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.AuthService;
import com.dream.city.service.CityMessageService;
import com.dream.city.service.ConsumerPlayerService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 玩家Controller
 * @author Wvv
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerPlayerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerPlayerService consumerPlayerService;
    @Autowired
    private CityMessageService messageService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    AuthService authService;


    /**
     * 获取认证码
     * @param msg
     * @return
     */
    @RequestMapping("/getcode")
    public Message getCode(@RequestBody Message msg){
        Map<String,Object> map= new HashMap<>();
        Message code = messageService.getCode(msg);
        map.put("code",code.getData().getT());
        MessageData messageData = new MessageData();
        messageData.setT(map);
        Message message = new Message(msg.getSource(),msg.getTarget(),messageData);
        return message;
    }


    /**
     * 获取当前用户
     * @param msg
     * @return
     */
    @RequestMapping("/get/user")
    public Message getUser(@RequestBody Message msg){
        Map<String,Object> map = (Map<String, Object>) msg.getData().getT();
        String id = map.get("id").toString();
        Result player = consumerPlayerService.getPlayer(id);

        Map<String,Object> t = new HashMap<>();
        t.put("user",player.getData());
        MessageData messageData = new MessageData();
        messageData.setT(t);
        Message message = new Message(msg.getSource(),msg.getTarget(),messageData);
        return message;
    }


    /**
     * 玩家列表
     * @param msg
     * @return
     */
    @RequestMapping("/get/users")
    public Message getUsers(@RequestBody Message msg){
        UserReq userReq = getUserReq(msg);
        String jsonReq = JSON.toJSONString(userReq);
        Result players = consumerPlayerService.getPlayers(jsonReq);

        Map<String,Object> t = new HashMap<>();
        t.put("users",players.getData());
        MessageData data = new MessageData();
        data.setT(t);
        Message message = new Message(msg.getSource(),msg.getTarget(),data);
        return message;
    }


    /**
     * 忘记密码
     * @param msg
     * @return
     */
    @RequestMapping("/forget")
    public Message forget(@RequestBody Message msg){
        UserReq jsonReq = getUserReq(msg);
        Result result = consumerPlayerService.forgetPwd(jsonReq.getUsername(), jsonReq.getOldpw());

        logger.info("##################### 忘记密码 ",msg);

        Map<String,String> t = new HashMap<>();
        t.put("desc",result.getMsg());
        MessageData data = new MessageData();
        data.setT(t);
        Message message = new Message(msg.getSource(),msg.getTarget(),data);
        return message;
    }

    /**
     * 修改密码
     * @param msg
     * @return
     */
    @RequestMapping("/expw")
    public Message resetLoginPwd(@RequestBody Message msg){
        UserReq jsonReq = getUserReq(msg);
        Result result = consumerPlayerService.resetLoginPwd(jsonReq.getPlayerId(), jsonReq.getOldpw(),jsonReq.getNewpw());

        logger.info("##################### 修改密码 ",msg);
        Map<String,String> t = new HashMap<>();
        t.put("desc",result.getMsg());

        MessageData data = new MessageData();
        data.setT(t);
        Message message = new Message(msg.getSource(),msg.getTarget(),data);
        return message;
    }

    /**
     * 修改交易密码
     * @param msg
     * @return
     */
    @RequestMapping("/expwshop")
    public Message expwshop(@RequestBody Message msg){
        UserReq jsonReq = getUserReq(msg);
        Result result = consumerPlayerService.resetTraderPwd(jsonReq.getPlayerId(), jsonReq.getOldpw(),jsonReq.getNewpw());
        logger.info("##################### 修改交易密码 ",msg);
        Map<String,String> t = new HashMap<>();
        t.put("desc",result.getMsg());

        MessageData data = new MessageData();
        data.setT(t);
        Message message = new Message(msg.getSource(),msg.getTarget(),data);
        return message;
    }


    /**
     * 用户注册
     * @param message
     * @return
     */
    @RequestMapping("/reg")
    public Message reg(@RequestBody Message message){
        Message msg = new Message();
        msg.setSource(message.getSource());
        msg.setTarget(message.getTarget());
        MessageData data = new MessageData();
        data.setType("reg");
        data.setModel("consumer");
        Map<String,String> t = new HashMap<>();

        UserReq userReq = getUserReq(message);
        String jsonReq = JSON.toJSONString(userReq);

        // 校验码  todo
        /*Message retMsg = messageService.validCode(message);
        if (!(Boolean) retMsg.getData().getT()){
            msg.getData().setT(new MessageData(CityGlobal.Constant.REG_FAIL));
            return msg;
        }*/

        Result reg = consumerPlayerService.reg(jsonReq);
        if (reg.getSuccess()){
            t.put("desc",CityGlobal.Constant.REG_SUCCESS);
            data.setT(t);
            msg.setData(data);
            logger.info("##################### 用户注册成功 ",msg);
            return msg;
        }
        t.put("desc",CityGlobal.Constant.REG_FAIL);
        data.setT(t);
        msg.setData(data);
        msg.setDesc(reg.getMsg());
        return msg;
    }


    /**
     * 密码登录
     * @param msg
     * @return
     */
    @RequestMapping("/pwlog")
    public Message pwlog(@RequestBody Message msg){
        UserReq userReq = getUserReq(msg);
        String jsonReq = JSON.toJSONString(userReq);
        Result result = consumerPlayerService.pwlog(jsonReq);

        Map<String,String> t = new HashMap<>();
        if (result.getSuccess()){
            t.put("desc",CityGlobal.Constant.LOGIN_SUCCESS);
            String token = authService.getAuth(userReq.getUsername());
            t.put("token",token);
        }else {
            t.put("desc",CityGlobal.Constant.LOGIN_FAIL);
        }
        logger.info("##################### 用户登录 ",msg);
        MessageData data = new MessageData("pwlog","consumer");
        data.setT(t);
        Message message = new Message(msg.getSource(),msg.getTarget(),data);
        message.setSource(msg.getSource());
        message.setTarget(msg.getTarget());
        message.setDesc(result.getMsg());
        return message;
    }

    /**
     * 验证码登录
     * @param msg
     * @return
     */
    @RequestMapping("/idlog")
    public Message idlog(@RequestBody Message msg){
        Message message = new Message();
        message.setSource(msg.getSource());
        message.setTarget(msg.getTarget());
        MessageData data = new MessageData("idlog","consumer");
        Map<String,String> t = new HashMap<>();

        UserReq userReq = getUserReq(msg);
        StringBuilder tip = new StringBuilder();

        // 校验认证码
        if (StringUtils.isBlank(userReq.getUserpass())){
            tip.append(CityGlobal.Constant.USER_VLCODE_NULL);
            t.put("desc",CityGlobal.Constant.LOGIN_FAIL);
            data.setT(t);
            message.setData(data);
            message.setDesc(tip.toString());
        }
        String redisKey = RedisKeys.REDIS_KEY_VALIDCODE + msg.getSource();
        if (redisUtils.hasKey(redisKey)){
            String redisCode = redisUtils.getStr(RedisKeys.REDIS_KEY_VALIDCODE+msg.getSource());
            if (!userReq.getUserpass().equals(redisCode)){
                tip.append(CityGlobal.Constant.USER_VLCODE_ERROR);
                t.put("desc",CityGlobal.Constant.LOGIN_FAIL);
                data.setT(t);
                message.setData(data);
                message.setDesc(tip.toString());
            }
        }else {
            // 校验码  todo
            /*Message retMsg = messageService.validCode(message);
            if (!(Boolean) retMsg.getData().getT()){
                msg.getData().setT(new MessageData(CityGlobal.Constant.REG_FAIL));
                return msg;
            }*/
        }
        if (StringUtils.isNotBlank(tip.toString())){
            return message;
        }

        Result idlog = consumerPlayerService.idlog(JSON.toJSONString(userReq));
        if (idlog.getSuccess()){
            t.put("desc",CityGlobal.Constant.LOGIN_SUCCESS);
        }else {
            t.put("desc",CityGlobal.Constant.LOGIN_FAIL);
        }
        data.setT(t);
        message.setData(data);
        message.setDesc(idlog.getMsg());
        return message;
    }


    /**
     * 登出
     * @param msg
     * @return
     */
    @RequestMapping("/exit")
    public Message exit(@RequestBody Message msg){
        UserReq jsonReq = getUserReq(msg);
        Result result = consumerPlayerService.quit(jsonReq.getPlayerId());
        logger.info("##################### 用户登出 ",msg);

        Map<String,String> t = new HashMap<>();
        t.put("desc",result.getMsg());
        MessageData data = new MessageData();
        data.setT(t);
        Message message = new Message(msg.getSource(),msg.getTarget(),data);
        return message;

    }


    private UserReq getUserReq(Message message){
        Map map = (Map)message.getData().getT();
        UserReq userReq = new UserReq();
        if (map != null) {
            userReq.setInvited(map.containsKey("invited")?(String) map.get("invited"):null);
            userReq.setNick(map.containsKey("nick")?(String) map.get("nick"):null);
            userReq.setPlayerId(map.containsKey("playerId")?(String) map.get("playerId"):null);
            userReq.setPwshop(map.containsKey("pwshop")?(String) map.get("pwshop"):null);
            userReq.setUsername(map.containsKey("username")?(String) map.get("username"):null);
            userReq.setUserpass(map.containsKey("userpass")?(String) map.get("userpass"):null);
            userReq.setCode(map.containsKey("code")?(String) map.get("code"):null);
            userReq.setOldpw(map.containsKey("oldpw")?(String) map.get("oldpw"):null);
            userReq.setNewpw(map.containsKey("newpw")?(String) map.get("newpw"):null);
        }
        return userReq;
    }

}
