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

import java.util.List;

/**
 * @author Wvv
 */
@Service
public class InvestRuleServiceImpl implements InvestRuleService {
    @Autowired
    RuleItemMapper ruleItemMapper;
    @Autowired
    InvestRuleMapper ruleMapper;



    @LcnTransaction
    @Transactional
    @Override
    public List<InvestRule> getInvestRuleByKey(Integer key)throws BusinessException {
        return ruleMapper.getInvestRuleByKey(key);
    }

    @LcnTransaction
    @Transactional
    @Override
    public RuleItem getInvestRuleItemByKey(String key) throws BusinessException{

        return ruleItemMapper.getInvestRuleItemByKey(key);
    }
}
