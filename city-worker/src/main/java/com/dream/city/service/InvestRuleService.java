package com.dream.city.service;

import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RuleItem;

import java.util.List;

/**
 * @author Wvv
 *
 */
public interface InvestRuleService {

    List<InvestRule> getInvestRuleByKey(Integer key);

    RuleItem getInvestRuleItemByKey(String rule_current);
}
