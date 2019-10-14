package com.dream.city.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.service.InvestAllowService;
import com.dream.city.service.PlayerAccountService;
import com.dream.city.service.RelationTreeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
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
    @Autowired
    PlayerAccountService accountService;

    /**
     * 允许投资准入
     *
     * @param playerId
     * @return
     */
    @RequestMapping("/invest/allowed")
    public Result isAllowedInvest(@RequestParam("playerId")String playerId){

        InvestAllow allow = investService.getInvestAllowByPlayerId(playerId);


        if (null != allow && StringUtils.isNotBlank(allow.getAllowed())){
            return Result.result(true);
        }
        return Result.result(false);
    }

    /**
     * 投资提取
     *
     * @param playerId
     * @return
     */
    @RequestMapping("/invest/collect/earning")
    public Result InvestCollect(@RequestParam("playerId")String playerId,@RequestParam("investId")Integer investId){

        PlayerEarning earning = investService.investCollectEarning(playerId,investId);

        if (null != earning && earning.getEarnMax().compareTo(earning.getEarnCurrent())==0 && earning.getIsWithdrew()==1){
            //1、修改账户
            PlayerAccount account = accountService.getPlayerAccount(playerId);
            BigDecimal taxTotal = earning.getEarnEnterpriseTax().add(earning.getEarnPersonalTax());

            BigDecimal earnTotal = earning.getEarnCurrent().subtract(taxTotal);

            account.setAccUsdt(account.getAccUsdt().add(earnTotal));
            account.setAccUsdtAvailable(account.getAccUsdtAvailable().add(earnTotal));
            //2、添加累计收益记录
            account.setAccIncome(account.getAccIncome().add(taxTotal));
            accountService.updatePlayerAccount(account);

            //3、添加  账户 记录
            PlayerAccountLog accountLog = new PlayerAccountLog();
            accountLog.setId(0L);
            accountLog.setAddress(account.getAccAddr());
            accountLog.setAmountMt(new BigDecimal(0));
            accountLog.setAmountUsdt(earnTotal);
            accountLog.setPlayerId(playerId);
            accountLog.setType(1);
            accountLog.setDesc("收入账户多余的额度");
            accountLog.setCreateTime(new Date());

            accountService.addAccountLog(accountLog);

            //4、改变earn表记录状态为3
            earning.setIsWithdrew(3);
            investService.updateEarning(earning);
            return Result.result(true,"提取完成",ReturnStatus.SUCCESS.getStatus());

        }
        return Result.result(false,"收益未满，暂不可提取", ReturnStatus.NOT_FINISHED.getStatus());
    }


    @RequestMapping("/invest/join")
    public Result joinInvestAllow(@RequestParam("playerId")String playerId, @RequestParam("amount")BigDecimal amount) {
        InvestAllow allow = investService.getInvestAllowByPlayerId(playerId);
        boolean allowed = false;
        if (allow == null) {
            allowed = investService.addInvestAllow(playerId, amount);
        } else {
            allowed = true;
        }

        //TODO锁定付费额度
        Result result = accountService.lockUstdAmount(playerId, amount);
        if (result.getSuccess()) {

            //TODO 遍历印记分配
            if (allowed && null != allow) {
                //直推接收
                Map<Integer, RelationTree> parents = relationTreeService.getParents(playerId);
                BigDecimal rateDirection = investService.getRateDirection();
                BigDecimal rateInterpolation = investService.getRateInterpolation();
                /**
                 *TODO
                 * 分配USDT印记收益,key>1 表示为直推的收益分配
                 */
                parents.forEach((key, value) -> {
                    if (key > 1) {
                        investService.allowcationUSDTToPlayer(amount.multiply(rateDirection), parents.get(key));
                    } else {
                        investService.allowcationUSDTToPlayer(amount.multiply(rateInterpolation), parents.get(key));
                    }
                });

                //剩余USDT归平台
                investService.allowcationUSDTToPlatform(amount.multiply(rateInterpolation));


                return Result.result(true);
            }
            return Result.result(false);
        }else{
            return Result.result(false,"锁定费用失败");
        }
    }


}
