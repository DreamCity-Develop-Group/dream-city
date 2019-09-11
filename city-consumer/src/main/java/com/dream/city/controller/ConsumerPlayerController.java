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
 * @author Wvv
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerPlayerController {

    @Autowired
    private ConsumerPlayerService consumerPlayerService;
    @Autowired
    private CityMessageService messageService;


    @RequestMapping("/get/user")
    public Object getUser(@RequestBody Message msg){
        Map<String,Object> data = (Map<String, Object>) msg.getData().getT();
        String id = data.get("id").toString();
        return consumerPlayerService.getPlayer(id);
    }


    @RequestMapping("/get/users")
    public Object getUsers(@RequestBody Message msg){
        UserReq userReq = getUserReq(msg);
        String jsonReq = JSON.toJSONString(userReq);
        return consumerPlayerService.getPlayers(jsonReq);
    }


    @RequestMapping("/forget")
    public Object forget(@RequestBody Message msg){
        UserReq jsonReq = getUserReq(msg);
        return consumerPlayerService.resetLoginPwd(jsonReq.getPlayerId(),jsonReq.getUserpass());
    }

    @RequestMapping("/expwshop")
    public Object expwshop(@RequestBody Message msg){
        UserReq jsonReq = getUserReq(msg);
        return consumerPlayerService.resetTraderPwd(jsonReq.getPlayerId(),jsonReq.getPwshop());
    }

    @RequestMapping("/getcode")
    public Object getCode(@RequestBody Message msg){
        Map<String,Object> map= new HashMap<>();
        Message code = messageService.getCode(msg);
        map.put("code",code.getData().getT());
        return map;
    }

    /**
     * 用户注册
     */
    @RequestMapping("/reg")
    public Message reg(@RequestBody Message message){
        Message msg = new Message();
        msg.setData(new MessageData(message.getData().getType(),
                message.getData().getModel(),
                CityGlobal.Constant.REG_FAIL));

        UserReq userReq = getUserReq(message);
        String jsonReq = JSON.toJSONString(userReq);
        Message retMsg = messageService.validCode(message);
        Result reg = consumerPlayerService.reg(jsonReq);
        if ((Boolean) retMsg.getData().getT() && reg.getSuccess()){
            msg.setData(new MessageData(message.getData().getType(),
                    message.getData().getModel(),
                    CityGlobal.Constant.REG_SUCCESS));
            return msg;
        }
        return msg;
    }


    @RequestMapping("/login")
    public Object login(@RequestBody Message msg){
        UserReq userReq = getUserReq(msg);
        String jsonReq = JSON.toJSONString(userReq);
        return consumerPlayerService.login(jsonReq);
    }

    @RequestMapping("/quit")
    public Object quit(@RequestBody Message msg){
        UserReq jsonReq = getUserReq(msg);
        consumerPlayerService.quit(jsonReq.getPlayerId());
        return null;
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
