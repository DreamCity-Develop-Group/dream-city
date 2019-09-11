package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.req.UserReq;
import com.dream.city.service.CityMessageService;
import com.dream.city.service.ConsumerPlayerService;
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
        MessageData data = new MessageData();
        msg.setData(data);
        Map<String,String> t = new HashMap<>();

        UserReq userReq = getUserReq(message);
        String jsonReq = JSON.toJSONString(userReq);

        /*Message retMsg = messageService.validCode(message);
        if (!(Boolean) retMsg.getData().getT()){
            msg.getData().setT(new MessageData(CityGlobal.Constant.REG_FAIL));
            return msg;
        }*/

        Result reg = consumerPlayerService.reg(jsonReq);
        if (reg.getSuccess()){
            t.put("desc",CityGlobal.Constant.REG_SUCCESS);
            msg.getData().setT(t);
            logger.info("##################### 用户注册成功 ",msg);
            return msg;
        }

        msg.getData().setT(t.put("desc",CityGlobal.Constant.REG_FAIL));
        return msg;
    }


    /**
     * 登录
     * @param msg
     * @return
     */
    @RequestMapping("/login")
    public Message login(@RequestBody Message msg){
        UserReq userReq = getUserReq(msg);
        String jsonReq = JSON.toJSONString(userReq);
        Result result = consumerPlayerService.login(jsonReq);
        logger.info("##################### 用户登录 ",msg);

        Map<String,String> t = new HashMap<>();
        t.put("desc",result.getMsg());
        MessageData data = new MessageData();
        data.setT(t);
        Message message = new Message(msg.getSource(),msg.getTarget(),data);
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
