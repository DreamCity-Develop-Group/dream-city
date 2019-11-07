package com.dream.city.service;


import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.PlayerAccountLog;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 *
 */
public interface PlayerAccountService {
    void addAccountLog(PlayerAccountLog accountLog);
    void updateProfitToAccount(PlayerAccount account);

    PlayerAccount getPlayerAccount(String playerId);
    Player getPlayerByPlayerId(String playerId);

    void updatePlayerAccounts(List<PlayerAccount> accounts);
}
