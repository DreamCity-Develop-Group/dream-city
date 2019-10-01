package com.dream.city.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.InvestAllow;
import com.dream.city.service.InvestAllowService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author Wvv
 */
@Slf4j
@RestController
@RequestMapping("/tree")
public class InvestController {
    @Autowired
    InvestAllowService investService;

    @RequestMapping("/invest/allowed")
    public Result isAllowedInvest(@RequestParam("playerId")String playerId){
        InvestAllow allow = investService.getInvestAllowByPlayerId(playerId);
        if (null != allow && StringUtils.isBlank(allow.getAllowed())){
            return Result.result(true);
        }
        return Result.result(false);
    }

    @RequestMapping("/invest/join")
    public Result joinInvestAllow(@RequestParam("playerId")String playerId, @RequestParam("amount")BigDecimal amount){
        boolean allowed = investService.addInvestAllow(playerId,amount);
        InvestAllow allow = investService.getInvestAllowByPlayerId(playerId);
        if (allowed && null != allow){
            return Result.result(true);
        }
        return Result.result(false);
    }


}
