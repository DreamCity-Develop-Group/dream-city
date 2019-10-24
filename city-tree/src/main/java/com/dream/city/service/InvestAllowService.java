package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.InvestAllow;
import com.dream.city.base.model.entity.PlayerEarning;
import com.dream.city.base.model.entity.RelationTree;

import java.math.BigDecimal;

/**
 * @author Wvv
 */
public interface InvestAllowService {
    InvestAllow getInvestAllowByPlayerId(String playerId);

    boolean addInvestAllow(String playerId, BigDecimal amount);

    BigDecimal getRateDirection();

    BigDecimal getRateInterpolation();

    void allowcationUSDTToPlatform(BigDecimal multiply);

    void allowcationUSDTToPlayer(BigDecimal multiply, RelationTree relationTree);

    PlayerEarning investCollectEarning(String playerId, Integer investId);

    void updateEarning(PlayerEarning earning);

    Result allocationToPlayer(String playerId,BigDecimal amount);
}
