package com.dream.city.service.impl;

import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RuleItem;
import com.dream.city.base.model.mapper.InvestRuleItemMapper;
import com.dream.city.base.model.mapper.InvestRuleMapper;
import com.dream.city.service.InvestRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wvv
 */
@Service
public class InvestRuleServiceImpl implements InvestRuleService {
    @Autowired
    InvestRuleMapper investRuleMapper;

    @Autowired
    InvestRuleItemMapper investRuleItemMapper;

    @Override
    public RuleItem getRuleItemByFlag(String flag) {

        return investRuleItemMapper.getRuleItemByFlag(flag);
    }

    @Override
    public List<InvestRule> getRulesByItem(Integer itemId) {
        return investRuleMapper.getRulesByItem(itemId);
    }
}
