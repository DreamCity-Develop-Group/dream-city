package com.dream.city.service;


import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;

import java.math.BigDecimal;

/**
 * @author Wvv
 */
public interface PlayerService {

    Player getPlayerByPlayerId(String playerId);

    Player getPlayer(String playerId);

    void updatePlayerLevel(String playerId, Integer level);
}
