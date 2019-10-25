package com.dream.city.service;

import com.dream.city.base.model.entity.EarnIncomeLog;
import com.dream.city.base.model.entity.PlayerEarning;
import com.dream.city.base.model.resp.PlayerEarningResp;

import java.util.List;

/**
 * @author Wvv
 */
public interface PlayerEarningService {

    List<PlayerEarning> getPlayerEarnByPlayerId(String playerId);

    PlayerEarningResp getPlayerEarnByPlayerId(String playerId, Integer investId);

    void add(PlayerEarning earning);

    void update(PlayerEarning playerEarning);

    void addEarnLog(EarnIncomeLog earnIncomeLog);
}
