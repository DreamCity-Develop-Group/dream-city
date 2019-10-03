package com.dream.city.service.impl;

import com.dream.city.base.model.entity.InvestAllow;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.mapper.InvestAllowMapper;
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

    @Override
    public BigDecimal getRateDirection() {

        return new BigDecimal(0.35);
    }

    @Override
    public BigDecimal getRateInterpolation() {
        return new BigDecimal(0.05);
    }

    /**
     * TODO 分配最终收益到平台账户
     *
     *
     * @param multiply
     */
    @Override
    public void allowcationUSDTToPlatform(BigDecimal multiply) {

    }

    /**
     * TODO 分配比例收益到玩家
     *
     * @param multiply
     * @param relationTree
     */
    @Override
    public void allowcationUSDTToPlayer(BigDecimal multiply, RelationTree relationTree) {

    }
}
