package com.dream.city.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.InvestAllow;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.service.InvestAllowService;
import com.dream.city.service.RelationTreeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Wvv
 */
@Slf4j
@RestController
@RequestMapping("/tree")
public class InvestController {
    @Autowired
    InvestAllowService investService;
    @Autowired
    RelationTreeService relationTreeService;

    /**
     * 允许投资准入
     *
     * @param playerId
     * @return
     */
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
        //TODO 遍历印记分配
        if (allowed && null != allow){
            //直推接收
            Map<Integer,RelationTree> parents = relationTreeService.getParents(playerId);
            BigDecimal rateDirection = investService.getRateDirection();
            BigDecimal rateInterpolation =investService.getRateInterpolation();
            /**
             *TODO
             * 分配USDT印记收益
             */

            parents.forEach((key,value)->{
                if (key>1){
                    investService.allowcationUSDTToPlayer(amount.multiply(rateDirection),parents.get(key));
                }else{
                    investService.allowcationUSDTToPlayer(amount.multiply(rateInterpolation),parents.get(key));
                }
            });

            //剩余USDT归平台
            investService.allowcationUSDTToPlatform(amount.multiply(rateInterpolation));


            return Result.result(true);
        }
        return Result.result(false);
    }


}
