package com.dream.city.service.handler.impl;

import com.alibaba.fastjson.JSON;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityMessage;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.service.consumer.ConsumerMessageService;
import com.dream.city.service.consumer.ConsumerPlayerService;
import com.dream.city.service.handler.CommonService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerPlayerService playerService;
    @Autowired
    private ConsumerMessageService massegeService;

    @LcnTransaction
    @Transactional
    @Override
    public Map<String, PlayerResp> getPlayerByNameOrNicke(Message msg) throws BusinessException {
        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);
        Map map = new HashMap<>();

        PlayerResp player = null;
        String playerName = accountReq.getUsername();
        if (StringUtils.isNotBlank(playerName)) {
            map.put("playerName", playerName);
            Result<String> playerResult = playerService.getPlayerByName(JSON.toJSONString(map));
            if (playerResult.getSuccess()) {
                player = JSON.toJavaObject(JSON.parseObject(playerResult.getData()), PlayerResp.class);
            }
        }

        PlayerResp friend = null;
        String playerNick = accountReq.getNick();
        if (StringUtils.isNotBlank(playerNick)) {
            map = new HashMap<>();
            map.put("playerNick", playerNick);
            Result<String> friendResult = playerService.getPlayerByName(JSON.toJSONString(map));
            if (friendResult.getSuccess()) {
                friend = JSON.toJavaObject(JSON.parseObject(friendResult.getData()), PlayerResp.class);
            }
        }

        Map<String, PlayerResp> result = new HashMap();
        result.put("player", player);
        result.put("friend", friend);
        return result;
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerResp getPlayerByUserName(Message msg) throws BusinessException {
        Map<String, PlayerResp> playerOrFriend = getPlayerByNameOrNicke(msg);
        if (playerOrFriend != null && playerOrFriend.containsKey("player")) {
            PlayerResp player = playerOrFriend.get("player");
            if (player != null) {
                return player;
            }
        }
        return null;
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerResp getPlayerByStrUserName(String username) throws BusinessException {
        return this.getPlayerByUserNameOrNick(username, null);
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerResp getPlayerByStrNick(String nick) throws BusinessException {
        return this.getPlayerByUserNameOrNick(null, nick);
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerResp getPlayerByUserNameOrNick(String username, String nick) throws BusinessException {
        Map<String, String> map = new HashMap();
        if (StringUtils.isNotBlank(username)) {
            map.put("playerName", username);
        }
        if (StringUtils.isNotBlank(nick)) {
            map.put("playerNick", nick);
        }
        String jsonReq = JSON.toJSONString(map);
        Result<String> playerResult = playerService.getPlayerByName(jsonReq);
        PlayerResp player = null;
        if (playerResult.getSuccess()) {
            player = JSON.toJavaObject(JSON.parseObject(playerResult.getData()), PlayerResp.class);
        }
        return player;
    }

    @LcnTransaction
    @Transactional
    @Override
    public String getPlayerIdByUserName(Message msg) throws BusinessException {
        Map<String, PlayerResp> playerOrFriend = getPlayerByNameOrNicke(msg);
        if (playerOrFriend != null && playerOrFriend.containsKey("player")) {
            PlayerResp player = playerOrFriend.get("player");
            if (player != null) {
                return player.getPlayerId();
            }
        }
        return null;
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerResp getFriendByNick(Message msg) throws BusinessException {
        Map<String, PlayerResp> playerOrFriend = getPlayerByNameOrNicke(msg);
        if (playerOrFriend != null && playerOrFriend.containsKey("friend")) {
            PlayerResp friend = playerOrFriend.get("friend");
            if (friend != null) {
                return friend;
            }
        }
        return null;
    }

    @LcnTransaction
    @Transactional
    @Override
    public String getFriendIdByNick(Message msg) throws BusinessException {
        Map<String, PlayerResp> playerOrFriend = getPlayerByNameOrNicke(msg);
        if (playerOrFriend != null && playerOrFriend.containsKey("friend")) {
            PlayerResp friend = playerOrFriend.get("friend");
            if (friend != null) {
                return friend.getPlayerId();
            }
        }
        return null;
    }

    @LcnTransaction
    @Transactional
    @Override
    public boolean sendMessage(String playerId, String friendId, String content) throws BusinessException {
        CityMessage record = new CityMessage();
        record.setPlayerId(playerId);
        record.setFriendId(friendId);
        record.setContent(content);
        massegeService.insertMessage(record);
        return false;
    }


}
