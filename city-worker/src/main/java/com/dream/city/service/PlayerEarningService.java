package com.dream.city.service;

import com.dream.city.base.model.entity.EarnIncomeLog;
import com.dream.city.base.model.entity.PlayerEarning;
import com.dream.city.base.model.resp.PlayerEarningResp;

import java.math.BigDecimal;
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

    /**
     * 获取一定时间范围内符合捡漏的player eaning
     * @param withdrewState
     * @param afterHours

     * @return
     */
    List<PlayerEarning> getPlayerEarningByAfterHours(Integer withdrewState, Integer afterHours);

    List<PlayerEarning> getPlayerEarningCanFallDown(String time);

    int updateEarningWithRawStatus(Integer earnId,Integer status);

    int updateEarningFallDown(Integer earnId, BigDecimal amount);
}
