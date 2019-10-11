package com.dream.city.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RuleItem;
import com.dream.city.base.model.mapper.RuleItemMapper;
import com.dream.city.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rule")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @GetMapping("/get/item")
    public Result getItem(@RequestParam("ruleKey")String ruleKey) {
        InvestRule ruleItem = ruleService.getRuleItemByFlag(ruleKey);
        if (null != ruleItem){
            Map<String,Integer> map = new HashMap<>();
            map.put("hour",Integer.valueOf(ruleItem.getRuleRatePre().intValue()));
            map.put("rate",Integer.valueOf(ruleItem.getRuleRate().intValue()));
            return Result.result(true,ruleItem);
        }
        return Result.result(false);
    }

}
