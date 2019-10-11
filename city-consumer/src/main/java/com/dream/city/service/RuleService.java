package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.service.impl.SalesServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Wvv
 */
@FeignClient(value = "city-user", fallback = SalesServiceImpl.class)
//@RequestMapping("/rule")
public interface RuleService {

    @RequestMapping("/rule/get/item")
    Result getRuleItem(@RequestParam("ruleKey") String ruleKey);
}
