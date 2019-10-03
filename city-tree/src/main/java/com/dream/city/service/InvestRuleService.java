package com.dream.city.service;

import com.dream.city.base.model.entity.InvestAllow;
import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.entity.RuleItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Wvv
 */
public interface InvestRuleService {

    RuleItem getRuleItemByFlag(String plAYER_flag);

    List<InvestRule> getRulesByItem(Integer itemId);
}
