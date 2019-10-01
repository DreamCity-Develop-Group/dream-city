package com.dream.city.service;

import com.dream.city.base.model.entity.InvestAllow;

import java.math.BigDecimal;

/**
 * @author Wvv
 */
public interface InvestAllowService {
    InvestAllow getInvestAllowByPlayerId(String playerId);

    boolean addInvestAllow(String playerId, BigDecimal amount);
}
