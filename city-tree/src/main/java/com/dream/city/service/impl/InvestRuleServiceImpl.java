package com.dream.city.service.impl;

import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RuleItem;
import com.dream.city.base.model.mapper.InvestRuleMapper;
import com.dream.city.base.model.mapper.RuleItemMapper;
import com.dream.city.service.InvestRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public RuleItem getRuleItemByFlag(String flag) {

        return investRuleItemMapper.getRuleItemByFlag(flag);
    }

    @Override
    public List<InvestRule> getRulesByItem(Integer itemId) {
        return investRuleMapper.getRulesByItem(itemId);
    }

    @Override
    public BigDecimal getLevelRuleRate(String playerId, Integer level) {
        RuleItem ruleItem =  investRuleItemMapper.getRuleItemByFlag("PlAYER_LEVEL");
        List<InvestRule> rules = investRuleMapper.getRulesByItem(ruleItem.getItemId());
        BigDecimal rate = new BigDecimal(1.00);
        for (InvestRule rule : rules){
            if (rule.getRuleLevel() == level){
                rate = rule.getRuleRatePre();
            }
        }
        return rate;
    }
}
