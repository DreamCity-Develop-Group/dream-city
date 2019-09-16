package com.dream.city.controller;

import com.dream.city.base.model.*;
import com.dream.city.service.ConsumerFriendsService;
import com.dream.city.service.ConsumerPlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/consumer")
public class ConsumerFriendsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConsumerFriendsService consumerFriendsService;
    @Autowired
    ConsumerPlayerService consumerPlayerService;



    @RequestMapping("/addfriend")
    public Message addFriend(@RequestBody Message msg){
        Map map = getPlayerIdOrFriendId(msg);
        String playerId = map.containsKey("playerId")?(String)map.get("playerId"):null;
        String friendId = map.containsKey("playerId")?(String)map.get("playerId"):null;

        boolean b = consumerFriendsService.addFriend(playerId,friendId);
        Message message = getResultMessage(b,"添加好友");
        return message;
    }


    @RequestMapping("/agreeAddFriend")
    public Message agreeAddFriend(@RequestBody Message msg){
        Map map = getPlayerIdOrFriendId(msg);
        String playerId = map.containsKey("playerId")?(String)map.get("playerId"):null;
        String friendId = map.containsKey("playerId")?(String)map.get("playerId"):null;

        boolean b = consumerFriendsService.agreeAddFriend(playerId, friendId);
        Message message = getResultMessage(b,"通过好友");
        return message;
    }


    @RequestMapping("/friendList")
    public Message friendList(@RequestBody Message msg){
        Message message = new Message();
        MessageData data = new MessageData("addfriend","consumer");
        String desc = "获取好友成功";
        try {
            Map map = getPlayerIdOrFriendId(msg);
            String playerId = map.containsKey("playerId")?(String)map.get("playerId"):null;
            Page page = consumerFriendsService.friendList(playerId);
            data.setT(page);
        }catch (Exception e){
            desc = "获取好友失败";
            logger.error(desc,e);
        }
        message.setData(data);
        message.setDesc(desc);
        return message;
    }



    /**
     * Message
     * @param b
     * @param desc
     * @return
     */
    private Message getResultMessage(boolean b,String desc){
        Message message = new Message();
        MessageData<String> data = new MessageData<>("addfriend","consumer");
        String t = CityGlobal.ResultCode.fail.name();
        desc = desc + "失败";
        if (b) {
            t = CityGlobal.ResultCode.success.name();
            desc = desc + "成功";
        }
        data.setT(t);
        message.setData(data);
        message.setDesc(desc);
        return message;
    }

    /**
     * 从入参中获取playerId、friendId
     * @param msg
     * @return
     */
    private Map getPlayerIdOrFriendId(Message msg){
        Map map = (Map)msg.getData().getT();

        String playerName = (String) map.get("username");
        Result player = consumerPlayerService.getPlayerByName(playerName, null);
        Map playerMap = (Map)player.getData();
        String playerId = playerMap.containsKey("playerId")?(String)playerMap.get("playerId"):null;

        String nick = (String) map.get("nick");
        Result friend = consumerPlayerService.getPlayerByName(null, nick);
        Map friendMap = (Map)friend.getData();
        String friendId = friendMap.containsKey("playerId")?(String)friendMap.get("playerId"):null;

        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("playerId",playerId);
        resultMap.put("friendId",friendId);
        return resultMap;
    }

}
