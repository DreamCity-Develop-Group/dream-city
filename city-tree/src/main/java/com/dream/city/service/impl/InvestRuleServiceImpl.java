package com.dream.city.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RuleItem;
import com.dream.city.base.model.mapper.InvestRuleMapper;
import com.dream.city.base.model.mapper.RuleItemMapper;
import com.dream.city.service.InvestRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Wvv
 */
@Service
public class InvestRuleServiceImpl implements InvestRuleService {
    @Autowired
    InvestRuleMapper investRuleMapper;

    @Autowired
    RuleItemMapper investRuleItemMapper;


    @LcnTransaction
    @Transactional
    @Override
    public RuleItem getRuleItemByFlag(String flag) throws BusinessException {

        return investRuleItemMapper.getRuleItemByFlag(flag);
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<InvestRule> getRulesByItem(Integer itemId)throws BusinessException {
        return investRuleMapper.getRulesByItem(itemId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public BigDecimal getLevelRuleRate(String playerId, Integer level)throws BusinessException {
        RuleItem ruleItem =  investRuleItemMapper.getRuleItemByFlag("PlAYER_LEVEL");
        List<InvestRule> rules = investRuleMapper.getRulesByItem(ruleItem.getItemId());
        BigDecimal rate = new BigDecimal(1.00);
        for (InvestRule rule : rules){
            if (rule.getRuleLevel().intValue() == level.intValue()){
                rate = rule.getRuleRatePre();
            }
        }
        return rate;
    }
}
