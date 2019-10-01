package com.dream.city.service.impl;

import com.dream.city.base.model.entity.InvestAllow;
import com.dream.city.domain.mapper.InvestAllowMapper;
import com.dream.city.service.InvestAllowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Wvv
 */
@Service
public class InvestServiceImpl implements InvestAllowService {
    @Autowired
    InvestAllowMapper investMapper;
    @Override
    public InvestAllow getInvestAllowByPlayerId(String playerId) {
        return investMapper.getInvestAllowByPlayerId(playerId);
    }

    @Override
    public boolean addInvestAllow(String playerId, BigDecimal amount) {
        InvestAllow allow = investMapper.getInvestAllowByPlayerId(playerId);
        allow.setAmount(amount);
        investMapper.insert(allow);
        return true;
    }
}
