package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.req.UserReq;
import com.dream.city.service.CityMessageService;
import com.dream.city.service.ConsumerPlayerService;
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
        return new Message(msg.getSource(),msg.getTarget(),
                new MessageData(map));
    }


    /**
     * 获取当前用户
     * @param msg
     * @return
     */
    @RequestMapping("/get/user")
    public Message getUser(@RequestBody Message msg){
        Map<String,Object> data = (Map<String, Object>) msg.getData().getT();
        String id = data.get("id").toString();
        Result player = consumerPlayerService.getPlayer(id);
        return new Message(msg.getSource(),msg.getTarget(),
                new MessageData(player.getData()));
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
        return new Message(msg.getSource(),msg.getTarget(),
                new MessageData(players.getData()));
    }


    /**
     * 忘记密码
     * @param msg
     * @return
     */
    @RequestMapping("/forget")
    public Message forget(@RequestBody Message msg){
        UserReq jsonReq = getUserReq(msg);
        Result result = consumerPlayerService.resetLoginPwd(jsonReq.getPlayerId(), jsonReq.getUserpass());
        return new Message(msg.getSource(),msg.getTarget(),
                new MessageData(result.getMsg()));
    }

    /**
     * 修改交易密码
     * @param msg
     * @return
     */
    @RequestMapping("/expwshop")
    public Message expwshop(@RequestBody Message msg){
        UserReq jsonReq = getUserReq(msg);
        Result result = consumerPlayerService.resetTraderPwd(jsonReq.getPlayerId(), jsonReq.getPwshop());
        return new Message(msg.getSource(),msg.getTarget(),
                new MessageData(result.getMsg()));
    }


    /**
     * 用户注册
     * @param message
     * @return
     */
    @RequestMapping("/reg")
    public Message reg(@RequestBody Message message){
        Message msg = new Message();
        msg.setData(new MessageData(CityGlobal.Constant.REG_FAIL));

        UserReq userReq = getUserReq(message);
        String jsonReq = JSON.toJSONString(userReq);
        Message retMsg = messageService.validCode(message);
        Result reg = consumerPlayerService.reg(jsonReq);
        if ((Boolean) retMsg.getData().getT() && reg.getSuccess()){
            msg.setData(new MessageData(CityGlobal.Constant.REG_SUCCESS));
            return msg;
        }
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

        return new Message(msg.getSource(),msg.getTarget(),
                new MessageData(result.getMsg()));
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
        return new Message(msg.getSource(),msg.getTarget(),
                new MessageData(result.getMsg()));
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
        }
        return userReq;
    }

}
