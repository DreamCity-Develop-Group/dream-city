package com.dream.city.job.thread;


import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.Constants;
import com.dream.city.base.model.mapper.AccountMapper;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.base.model.resp.PlayerEarningResp;
import com.dream.city.base.utils.JSONHelper;
import com.dream.city.service.InvestService;
import com.dream.city.service.PlayerAccountService;
import com.dream.city.service.PlayerEarningService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2019/7/4 0004.
 */
@Slf4j
public class OrderProfitThead implements Runnable {

    private BigDecimal everyOneProfit;
    private InvestOrder order;
    private CountDownLatch endGate;
    private InvestService investService;
    private PlayerAccountMapper playerAccountMapper;
    private PlayerEarningService playerEarningService;
    private PlayerAccountService accountService;


    public OrderProfitThead(BigDecimal everyOneProfit, InvestOrder order,InvestService investService,PlayerAccountMapper playerAccountMapper,
                            PlayerAccountService accountService,PlayerEarningService playerEarningService, CountDownLatch endGate) {
        this.playerAccountMapper = playerAccountMapper;
        this.playerEarningService = playerEarningService;
        this.accountService = accountService;
        this.investService = investService;
        this.everyOneProfit = everyOneProfit;
        this.order = order;
        this.endGate = endGate;
    }

    @Override
    public void run() {
        try {
            setProfit(order,everyOneProfit);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("用户收益计算错误，用户ID为:{}" + order.getOrderPayerId());
        } finally {
            endGate.countDown();
        }

    }
    private void setProfit(InvestOrder order,BigDecimal everyOneProfit){
        log.info("order:======================================={}",order.getOrderId());
        PlayerEarningResp playerEarning = playerEarningService.getPlayerEarnByPlayerId(order.getOrderPayerId(), order.getOrderInvestId());
        log.info("playerEarning:{}", JSONHelper.toJson(playerEarning));
        Integer status = 2;
        if (null == playerEarning) {
            PlayerEarning earning = new PlayerEarning();
            CityInvest cityInvest = investService.getCityInvest(order.getOrderInvestId());
            earning.setEarnId(0);
            earning.setEarnInvestId(order.getOrderInvestId());
            earning.setEarnPlayerId(order.getOrderPayerId());
            earning.setEarnPreProfit(everyOneProfit);
            earning.setEarnMax(cityInvest.getInEarning().multiply(cityInvest.getInLimit()));
            earning.setEarnCurrent(everyOneProfit);
            earning.setEarnPersonalTax(cityInvest.getInPersonalTax());
            earning.setEarnEnterpriseTax(cityInvest.getInEnterpriseTax());
            if(everyOneProfit.compareTo(BigDecimal.ONE)>=0){
                earning.setIsWithdrew(2);
            }else{
                earning.setIsWithdrew(1);
            }
            earning.setCreateTime(new Date());
            earning.setUpdateTime(new Date());
            playerEarningService.add(earning);
        } else {
            CityInvest invest = investService.getInvestById(order.getOrderInvestId());
            BigDecimal fullProfit = invest.getInEarning().multiply(invest.getInLimit());
            log.info("fullProfit:{}",fullProfit);
            BigDecimal current = playerEarning.getEarnCurrent();
            log.info("current:{}",current);
            if(current.add(everyOneProfit).compareTo(BigDecimal.ONE)<0){
                status =1;
            }
            BigDecimal subProfit = new BigDecimal(0);
            boolean isNotCanWithdraw = true;
            //掉落+已提取+当前收益+此次发放收益 之后大于等于 最大收益额
            BigDecimal allProfit = current.add(everyOneProfit).
                    add(playerEarning.getWithdrewTotal()).add(playerEarning.getDropTotal());
            log.info("allProfit:{}",allProfit);
            if(allProfit.compareTo(fullProfit)==1){//如果收益满了，不再增加，并设置为可提取状态,将多余的加入到系统平台账户
                subProfit = allProfit.subtract(fullProfit);//超出的金额
                everyOneProfit = everyOneProfit.subtract(subProfit);//需要给用户增加的金额
            }
            log.info("OrderProfitThead=========================================================everyOneProfit:{}",everyOneProfit);
            playerEarningService.updateCurrentAmount(playerEarning.getEarnId(),everyOneProfit,status,playerEarning.getEarnPlayerId());
                //插入日志记录
            EarnIncomeLog earnIncomeLog = new EarnIncomeLog(order.getOrderInvestId(),order.getOrderPayerId(),everyOneProfit);
            playerEarningService.addEarnLog(earnIncomeLog);
            if(subProfit.compareTo(BigDecimal.ZERO)>0) {
                //将剩余的收益加到平台
                PlayerAccount account = accountService.getPlayerAccount(Constants.SYSTEM_ACCOUNT);
                playerAccountMapper.addPlayerUsdtAmount(account.getAccPlayerId(),subProfit);
                PlayerAccountLog accountLog = new PlayerAccountLog(Constants.SYSTEM_ACCOUNT,account.getAccAddr(),subProfit,BigDecimal.ZERO,1,"收入账户多余的额度");
                accountService.addAccountLog(accountLog);
            }
        }

    }
}
