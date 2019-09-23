package com.dream.city.service;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.resp.PlayerResp;

/**
 * Consumer的公共接口
 */
public interface ConsumerCommonsService {

    PlayerResp getPlayerByNameOrNicke(Message msg);
}
