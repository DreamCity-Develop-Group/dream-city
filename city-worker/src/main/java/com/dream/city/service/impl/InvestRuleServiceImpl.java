package com.dream.city.service.impl;

import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RuleItem;
import com.dream.city.base.model.mapper.InvestRuleMapper;
import com.dream.city.base.model.mapper.RuleItemMapper;
import com.dream.city.service.InvestRuleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InvestRuleServiceImpl implements InvestRuleService {
    @Autowired
    RuleItemMapper ruleItemMapper;
    @Autowired
    InvestRuleMapper ruleMapper;


    @Override
    public List<InvestRule> getInvestRuleByKey(Integer key) {
        return ruleMapper.getInvestRuleByKey(key);
    }

    @Override
    public RuleItem getInvestRuleItemByKey(String key) {

        return ruleItemMapper.getInvestRuleItemByKey(key);
    }
}
