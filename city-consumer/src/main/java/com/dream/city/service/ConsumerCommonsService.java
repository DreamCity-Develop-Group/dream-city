package com.dream.city.service;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.entity.Player;

/**
 * Consumer的公共接口
 */
public interface ConsumerCommonsService {

    Player getPlayerByNameOrNicke(Message msg);
}
