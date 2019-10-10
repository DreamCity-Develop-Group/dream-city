package com.dream.city.service;


import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;

import java.math.BigDecimal;

/**
 * @author Wvv
 */
public interface PlayerAccountService {
    BigDecimal getPlayerAccountUSDTAvailble(String playerId);
    BigDecimal getPlayerAccountMTAvailble(String playerId);

    Result checkPayPass(String playerId, String payPass);

    PlayerAccount getPlayerAccount(String playerId);

    void createAccount(String playerId,String address);

    void updatePlayerLevel(String playerId,Integer level);

    Player getPlayerByPlayerId(String playerId);
}
