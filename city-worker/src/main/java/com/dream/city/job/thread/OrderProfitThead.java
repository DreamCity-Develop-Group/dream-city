package com.dream.city.job.thread;


import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.resp.PlayerEarningResp;
import com.dream.city.service.InvestService;
import com.dream.city.service.PlayerAccountService;
import com.dream.city.service.PlayerEarningService;
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
public class OrderProfitThead implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(OrderProfitThead.class);
    private BigDecimal everyOneProfit;
    private InvestOrder order;
    private CountDownLatch endGate;
    private InvestService investService;
    private PlayerEarningService playerEarningService;
    private PlayerAccountService accountService;
    private final String SYSTEM_ACCOUNT = "SYSTEM_DC_ACCOUNT";

    public OrderProfitThead(BigDecimal everyOneProfit, InvestOrder order,InvestService investService,
                            PlayerAccountService accountService,PlayerEarningService playerEarningService, CountDownLatch endGate) {
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
            logger.info("用户收益计算错误，用户ID为：" + order.getOrderPayerId());
        } finally {
            endGate.countDown();
        }

    }
    private void setProfit(InvestOrder order,BigDecimal everyOneProfit){
        PlayerEarningResp playerEarning = playerEarningService.getPlayerEarnByPlayerId(order.getOrderPayerId(), order.getOrderInvestId());
        if (null == playerEarning) {
            PlayerEarning earning = new PlayerEarning();
            CityInvest cityInvest = investService.getCityInvest(order.getOrderInvestId());
            earning.setEarnId(0);
            earning.setEarnInvestId(order.getOrderInvestId());
            earning.setEarnPlayerId(order.getOrderPayerId());
            earning.setEarnMax(cityInvest.getInEarning().multiply(cityInvest.getInLimit()));
            earning.setEarnCurrent(everyOneProfit);
            earning.setEarnPersonalTax(cityInvest.getInPersonalTax());
            earning.setEarnEnterpriseTax(cityInvest.getInEnterpriseTax());
            earning.setIsWithdrew(0);
            earning.setCreateTime(new Date());
            earning.setUpdateTime(new Date());
            playerEarningService.add(earning);
        } else {
            //如果收益满了，不再增加，并设置为可提取状态,将多余的加入到系统平台账户
            CityInvest invest = investService.getInvestById(order.getOrderInvestId());
            //满收益额度
            BigDecimal fullProfit = invest.getInEarning().multiply(invest.getInLimit());
            BigDecimal current = playerEarning.getEarnCurrent();
            BigDecimal subProfit = new BigDecimal(0);
            boolean isNotCanWithdraw = true;
            //掉落+已提取+当前收益+此次发放收益 之后大于等于 最大收益额
            BigDecimal allProfit = current.add(everyOneProfit).
                    add(playerEarning.getWithdrewTotal()).add(playerEarning.getDropTotal()).add(everyOneProfit);
            if(allProfit.compareTo(fullProfit)>=0){
                subProfit = allProfit.subtract(fullProfit);
                playerEarning.setEarnCurrent(current.add(subProfit));
            }else{
                playerEarning.setEarnCurrent(current.add(everyOneProfit));
            }

            if(playerEarning.getEarnCurrent().compareTo(BigDecimal.ONE)>=0){
                isNotCanWithdraw = false;
                playerEarning.setIsWithdrew(2);//设置为可提取状态
            }
            if (isNotCanWithdraw){
                //插入日志记录
                EarnIncomeLog earnIncomeLog = new EarnIncomeLog();
                earnIncomeLog.setInLogId(0);
                earnIncomeLog.setInInvestId(order.getOrderInvestId());
                earnIncomeLog.setInPlayerId(order.getOrderPayerId());
                earnIncomeLog.setInAmount(everyOneProfit);
                playerEarningService.addEarnLog(earnIncomeLog);
            }else {
                if(subProfit.compareTo(BigDecimal.ZERO)>0) {
                    //将剩余的收益加到平台
                    BigDecimal remainProfit = everyOneProfit.subtract(subProfit);
                    PlayerAccount account = accountService.getPlayerAccount(SYSTEM_ACCOUNT);
                    account.setAccUsdt(account.getAccUsdt().add(remainProfit));
                    account.setAccMtAvailable(account.getAccUsdtAvailable().add(remainProfit));
                    accountService.updateProfitToAccount(account);


                    PlayerAccountLog accountLog = new PlayerAccountLog();
                    accountLog.setId(0L);
                    accountLog.setAddress(account.getAccAddr());
                    accountLog.setAmountMt(new BigDecimal(0));
                    accountLog.setAmountUsdt(remainProfit);
                    accountLog.setPlayerId(SYSTEM_ACCOUNT);
                    accountLog.setType(1);
                    accountLog.setDesc("收入账户多余的额度");
                    accountLog.setCreateTime(new Date());

                    accountService.addAccountLog(accountLog);
                }
            }
        }

    }
}
