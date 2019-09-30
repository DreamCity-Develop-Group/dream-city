package com.dream.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityMessage;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.service.ConsumerCommonsService;
import com.dream.city.service.ConsumerMessageService;
import com.dream.city.service.ConsumerPlayerService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsumerCommonsServiceImpl implements ConsumerCommonsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerPlayerService playerService;
    @Autowired
    private ConsumerMessageService massegeService;

    @Override
    public Map<String,PlayerResp> getPlayerByNameOrNicke(Message msg) {
        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);
        Map map = new HashMap<>();

        PlayerResp player = null;
        String playerName = accountReq.getUsername();
        if (StringUtils.isNotBlank(playerName)){
            map.put("playerName",playerName);
            Result<String> playerResult = playerService.getPlayerByName(JSON.toJSONString(map));
            if (playerResult.getSuccess()){
                player = JSON.toJavaObject(JSON.parseObject(playerResult.getData()),PlayerResp.class);
            }
        }

        PlayerResp friend = null;
        String playerNick = accountReq.getNick();
        if (StringUtils.isNotBlank(playerNick)){
            map = new HashMap<>();
            map.put("playerNick",playerNick);
            Result<String>  friendResult = playerService.getPlayerByName(JSON.toJSONString(map));
            if (friendResult.getSuccess()){
                friend = JSON.toJavaObject(JSON.parseObject(friendResult.getData()),PlayerResp.class);
            }
        }

        Map<String,PlayerResp> result = new HashMap();
        result.put("player",player);
        result.put("friend",friend);
        return result;
    }

    @Override
    public PlayerResp getPlayerByUserName(Message msg) {
        Map<String,PlayerResp> playerOrFriend = getPlayerByNameOrNicke(msg);
        if (playerOrFriend != null && playerOrFriend.containsKey("player")){
            PlayerResp  player= playerOrFriend.get("player");
            if (player != null){
                return player;
            }
        }
        return null;
    }

    @Override
    public String getPlayerIdByUserName(Message msg) {
        Map<String,PlayerResp> playerOrFriend = getPlayerByNameOrNicke(msg);
        if (playerOrFriend != null && playerOrFriend.containsKey("player")){
            PlayerResp  player= playerOrFriend.get("player");
            if (player != null){
                return player.getPlayerId();
            }
        }
        return null;
    }

    @Override
    public PlayerResp getFriendByNick(Message msg) {
        Map<String,PlayerResp> playerOrFriend = getPlayerByNameOrNicke(msg);
        if (playerOrFriend != null && playerOrFriend.containsKey("friend")){
            PlayerResp  friend= playerOrFriend.get("friend");
            if (friend != null){
                return friend;
            }
        }
        return null;
    }

    @Override
    public String getFriendIdByNick(Message msg) {
        Map<String,PlayerResp> playerOrFriend = getPlayerByNameOrNicke(msg);
        if (playerOrFriend != null && playerOrFriend.containsKey("friend")){
            PlayerResp  friend= playerOrFriend.get("friend");
            if (friend != null){
                return friend.getPlayerId();
            }
        }
        return null;
    }

    @Override
    public boolean sendMessage(String playerId, String friendId, String content) {
        CityMessage record = new  CityMessage();
        record.setPlayerId(playerId);
        record.setFriendId(friendId);
        record.setContent(content);
        massegeService.insertMessage(record);
        return false;
    }


}
