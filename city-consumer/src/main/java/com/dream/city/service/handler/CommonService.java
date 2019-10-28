package com.dream.city.service.handler;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.resp.PlayerResp;

import java.util.Map;

public interface CommonService {
    Map<String, PlayerResp> getPlayerByNameOrNicke(Message msg);

    PlayerResp getPlayerByUserName(Message msg);

    PlayerResp getPlayerByStrUserName(String username);

    PlayerResp getPlayerByStrNick(String nick);

    PlayerResp getPlayerByUserNameOrNick(String username, String nick);

    String getPlayerIdByUserName(Message msg);

    PlayerResp getFriendByNick(Message msg);

    String getFriendIdByNick(Message msg);

    boolean sendMessage(String playerId, String friendId, String content);


}
