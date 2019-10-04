package com.dream.city.service;

import com.dream.city.base.model.entity.PlayerEarning;

/**
 * @author Wvv
 */
public interface PlayerEarningService {

    PlayerEarning getPlayerEarnByPlayerId(String playerId);
}
