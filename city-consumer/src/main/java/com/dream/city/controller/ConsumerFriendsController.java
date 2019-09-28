package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.*;
import com.dream.city.base.model.req.PageReq;
import com.dream.city.service.ConsumerFriendsService;
import com.dream.city.service.ConsumerOrderHandleService;
import com.dream.city.service.ConsumerPlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "好友", description = "好友")
@RestController
@RequestMapping("/consumer/player/friend")
public class ConsumerFriendsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConsumerFriendsService consumerFriendsService;
    @Autowired
    ConsumerPlayerService consumerPlayerService;
    @Autowired
    ConsumerOrderHandleService orderHandleService;

    /**
     * 好友主页
     * @param msg
     * @return
     */
    @ApiOperation(value = "好友主页", notes = "好友主页", response = Message.class)
    @RequestMapping("/friendHomePage")
    public Message friendHomePage(@RequestBody Message msg){
        logger.info("好友主页", JSONObject.toJSONString(msg));
        return orderHandleService.getPlayerInvestOrders(msg);
    }



    @RequestMapping("/addFriend")
    @ApiOperation(value = "添加好友", notes = "添加好友", response = Message.class)
    public Message addFriend(@RequestBody Message msg){
        logger.info("添加好友", JSONObject.toJSONString(msg));
        Map map = getPlayerIdOrFriendId(msg);
        String playerId = map.containsKey("playerId")?(String)map.get("playerId"):null;
        String friendId = map.containsKey("friendId")?(String)map.get("friendId"):null;

        Result<Boolean> b = consumerFriendsService.addFriend(playerId,friendId);
        Message message = getResultMessage(b.getSuccess(),"添加好友",msg);
        return message;
    }

    @ApiOperation(value = "通过好友", notes = "通过好友", response = Message.class)
    @RequestMapping("/agreeAddFriend")
    public Message agreeAddFriend(@RequestBody Message msg){
        logger.info("通过好友", JSONObject.toJSONString(msg));
        Map map = getPlayerIdOrFriendId(msg);
        String playerId = map.containsKey("playerId")?(String)map.get("playerId"):null;
        String friendId = map.containsKey("friendId")?(String)map.get("friendId"):null;
        String agree = map.containsKey("agree")?(String)map.get("agree"):"disagreed";

        Result<Boolean> b = consumerFriendsService.agreeAddFriend(playerId, friendId,agree);
        Message message = getResultMessage(b.getSuccess(),"通过好友",msg);
        return message;
    }

    @ApiOperation(value = "获取好友列表", notes = "获取好友列表", response = Message.class)
    @RequestMapping("/friendList")
    public Message friendList(@RequestBody Message msg){
        logger.info("获取好友列表", JSONObject.toJSONString(msg));
        Message message = new Message();
        MessageData data = new MessageData(msg.getData().getType(),msg.getData().getModel());
        String desc = "获取好友成功";
        try {
            Map condition = getCheckCondition(msg);
            PageReq<Map> pageReq = new PageReq<>((Map)msg.getData().getData());
            pageReq.setCondition(condition);

            Result<Page> page = consumerFriendsService.friendList(pageReq);
            data.setData(page.getData());
        }catch (Exception e){
            desc = "获取好友失败";
            logger.error(desc,e);
        }
        message.setData(data);
        message.setDesc(desc);
        return message;
    }

    @ApiOperation(value = "获取好友申请列表", notes = "获取好友申请列表", response = Message.class)
    @RequestMapping("/applyFriend")
    public Message applyFriend(@RequestBody Message msg){
        logger.info("获取好友申请列表", JSONObject.toJSONString(msg));
        Message message = new Message();
        MessageData data = new MessageData(msg.getData().getType(),msg.getData().getModel());
        String desc = "获取好友申请列表成功";
        try {
            Map condition = getCheckCondition(msg);
            PageReq<Map> pageReq = new PageReq<>((Map)msg.getData().getData());
            pageReq.setCondition(condition);

            Result<Page> page = consumerFriendsService.applyFriendList(pageReq);
            data.setData(page.getData());
        }catch (Exception e){
            desc = "获取好友申请列表失败";
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
    private Message getResultMessage(boolean b,String desc,Message msg){
        Message message = new Message();
        MessageData<Map> data = new MessageData(msg.getData().getType(),msg.getData().getModel());
        String agree = "disagreed";
        if (b) {
            desc = desc + "成功";
            agree = "agreed";
        }else {
            desc = desc + "失败";
        }
        Map map = new HashMap();
        map.put("agree",agree);
        data.setData(map);
        message.setData(data);
        message.setDesc(desc);
        return message;
    }


    /**
     *
     * @param msg
     * @return
     */
    private Map getCheckCondition(Message msg){
        Map map = (Map)msg.getData().getData();
        String username = map.containsKey("username")?(String) map.get("username"):null;
        if (StringUtils.isBlank(username)){
            username = map.containsKey("playerName")?(String) map.get("playerName"):null;
        }
        String nick = map.containsKey("nick")?(String) map.get("nick"):null;

        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("username",username);
        resultMap.put("nick",nick);
        return resultMap;
    }


    /**
     * 从入参中获取playerId、friendId
     * @param msg
     * @return
     */
    private Map getPlayerIdOrFriendId(Message msg){
        Map map = (Map)msg.getData().getData();

        String agree = map.containsKey("agree")?(String) map.get("agree"):null;
        String playerName = map.containsKey("playerName")?(String) map.get("playerName"):null;
        if (StringUtils.isBlank(playerName)){
            playerName = map.containsKey("username")?(String) map.get("username"):null;
        }
        String playerId = map.containsKey("playerId")?(String) map.get("playerId"):null;
        if (StringUtils.isNotBlank(playerName)){
            JSONObject playerNamejsonObject = new JSONObject();
            playerNamejsonObject.put("playerName",playerName);
            Result<String> player = consumerPlayerService.getPlayerByName(playerNamejsonObject.toJSONString());
            Map<String,Object> playerMap = JSON.parseObject(player.getData(),Map.class);
            playerId = playerMap.containsKey("playerId")?(String)playerMap.get("playerId"):null;
        }

        String nick = map.containsKey("nick")?(String) map.get("nick"):null;
        String friendId = map.containsKey("friendId")?(String) map.get("friendId"):null;
        if (StringUtils.isNotBlank(nick)){
            JSONObject nickjsonObject = new JSONObject();
            nickjsonObject.put("playerNick",nick);
            Result<String> friend = consumerPlayerService.getPlayerByName(nickjsonObject.toJSONString());
            Map<String,Object> friendMap = JSON.parseObject(friend.getData(),Map.class);
            friendId = friendMap.containsKey("playerId")?(String)friendMap.get("playerId"):null;
        }
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("playerId",playerId);
        resultMap.put("friendId",friendId);
        resultMap.put("agree",agree);
        return resultMap;
    }

}
