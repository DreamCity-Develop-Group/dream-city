package com.dream.city.service.impl;

import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.mapper.InvestRuleMapper;
import com.dream.city.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    InvestRuleMapper ruleMapper;

    @Override
    public List<InvestRule> selectRules() {
        return ruleMapper.selectRules();
    }
}
