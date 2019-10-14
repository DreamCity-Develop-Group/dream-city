package com.dream.city.service;


import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.PlayerAccountLog;

import java.math.BigDecimal;
import java.util.List;

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

    Result lockUstdAmount(String playerId, BigDecimal amount);

    void subtractAmount(BigDecimal orderPayAmount, String playerId);

    void updatePlayerAccounts(List<PlayerAccount> accounts);

    int updatePlayerAccount(PlayerAccount account);

    void addAccountLog(PlayerAccountLog accountLog);
}
