package com.dream.city.service;

import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RuleItem;
import com.dream.city.base.model.entity.User;

import java.util.List;

/**
 * @author Wvv
 */
public interface RuleService {

    InvestRule getRuleItemByFlag(String ruleKey);
}
