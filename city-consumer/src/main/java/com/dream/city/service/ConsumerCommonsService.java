package com.dream.city.service;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.resp.PlayerResp;

import java.util.Map;

/**
 * Consumer的公共接口
 */
public interface ConsumerCommonsService {

    Map<String,PlayerResp> getPlayerByNameOrNicke(Message msg);

    PlayerResp getPlayerByUserName(Message msg);
    String getPlayerIdByUserName(Message msg);

    PlayerResp getFriendByNick(Message msg);
    String getFriendIdByNick(Message msg);

    boolean sendMessage(String playerId,String friendId,String content);
}
