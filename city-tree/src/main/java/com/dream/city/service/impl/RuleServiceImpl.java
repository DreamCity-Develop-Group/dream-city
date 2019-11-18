package com.dream.city.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RuleItem;
import com.dream.city.base.model.mapper.InvestRuleMapper;
import com.dream.city.base.model.mapper.RuleItemMapper;
import com.dream.city.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @LcnTransaction
    @Transactional
    @Override
    public InvestRule getRuleItemByFlag(String ruleKey)  throws BusinessException {
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
