package com.dream.city.service.impl;

import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RuleItem;
import com.dream.city.base.model.entity.User;
import com.dream.city.base.model.mapper.InvestRuleMapper;
import com.dream.city.base.model.mapper.RuleItemMapper;
import com.dream.city.base.model.mapper.UserMapper;
import com.dream.city.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wvv
 */
@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleItemMapper ruleItemMapper;

    @Autowired
    private InvestRuleMapper ruleMapper;

    @Override
    public InvestRule getRuleItemByFlag(String ruleKey) {
        RuleItem ruleItem = ruleItemMapper.getRuleItemByFlag(ruleKey);
        if (ruleItem!=null){
            List<InvestRule> rules = ruleMapper.getInvestRuleByItem(ruleItem.getItemId());
            if (rules.size()>0){
                return rules.get(0);
            }
            return null;
        }
        return null;
    }
}
