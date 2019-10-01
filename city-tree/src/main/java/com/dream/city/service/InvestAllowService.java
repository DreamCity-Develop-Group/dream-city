package com.dream.city.service;

import com.dream.city.base.model.entity.InvestAllow;
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
}
