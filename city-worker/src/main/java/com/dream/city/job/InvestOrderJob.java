package com.dream.city.job;

import com.dream.city.base.model.entity.*;

import com.dream.city.base.model.enu.InvestStatus;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.mapper.InvestOrderMapper;
import com.dream.city.base.model.mapper.PlayerAccountLogMapper;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.base.model.mapper.PlayerEarningMapper;
import com.dream.city.service.InvestOrderService;
import com.dream.city.service.InvestRuleService;
import com.dream.city.service.InvestService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO 计算 玩家投资预约结果
 *
 * @author WVv
 */
@Slf4j
@Component
public class InvestOrderJob extends QuartzJobBean {
    @Autowired
    private InvestOrderService investOrderService;
    @Autowired
    private InvestService investService;
    @Autowired
    private InvestRuleService ruleService;

    @Autowired
    InvestOrderMapper investOrderMapper;

    @Autowired
    PlayerAccountMapper playerAccountMapper;

    @Autowired
    PlayerAccountLogMapper playerAccountLogMapper;

    @Autowired
    PlayerEarningMapper playerEarningMapper;

    private final String RULE_CURRENT = "INVEST_ORDER";


    /**
     *TODO
     * 1、取出所有投资项目
     * 2、遍历所有投资项目预约的（到截止时间，上一工作日的所有投资订单）
     * 3、在2中，直接根据规则返回所有能够取得成功的成员
     * 4、遍历所有成功的订单，设置为成功状态
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(new Date() + "    订单任务处理执行");
        //TODO 取出所有的投资项目，分别根据投资项目的总预约数以当前规则的比例筛选成功的成员

        List<CityInvest> invests = investService.getInvests();
        /**
         *TODO
         * 取出适应的所有规则
         */
        RuleItem ruleItem = ruleService.getInvestRuleItemByKey(RULE_CURRENT);

        List<InvestRule> rules = ruleService.getInvestRuleByKey(ruleItem.getItemId());

        invests.forEach(invest->{
            //直接找出合格的投资订单，修改相应的状态
            int[] states = new int[]{InvestStatus.MANAGEMENT.getStatus(),InvestStatus.EXTRACT.getStatus(),InvestStatus.FINISHED.getStatus()};
            //int[] states = new int[]{ReturnStatus.INVEST_MANAGEMENT.getStatus(),ReturnStatus.INVEST_EXTRACT.getStatus()};
            //states传过去，但没使用
            Map<String, List<InvestOrder>> orders = investOrderService.getInvestOrdersByCurrentDay(invest.getInId(),rules,states);

            List<InvestOrder> orderList = new ArrayList<>();
            orders.forEach((flag,order)->{
                orderList.addAll(order);
            });
            if(orderList.size()>0){
                investOrderService.setOrderState(orderList, InvestStatus.MANAGEMENT);
                //插入player_earning
                orderList.forEach((order)->{
                    PlayerEarning playerEarning = new  PlayerEarning();
                    playerEarning.setEarnInvestId(order.getOrderId());
                    playerEarning.setEarnInvestId(order.getOrderInvestId());
                    //以下有疑问？
                    playerEarning.setEarnCurrent(order.getOrderAmount());
                    if(playerEarning.getEarnCurrent().compareTo(new BigDecimal("1")) >= 0){
                        playerEarning.setIsWithdrew(2);
                    }
                    playerEarning.setEarnPlayerId(order.getOrderPayerId());
                    playerEarning.setUpdateTime(new Date());
                    playerEarning.setCreateTime(new Date());
                    playerEarningMapper.insertSelective(playerEarning);
                });
            }




        });


        //处理预约成功但不是经营中的。退款，置位。预约成功（临时状态）在上步中都置位。
        int state = InvestStatus.SUBSCRIBE_PASS.getStatus();
        List<InvestOrder> investOrdersFail = investOrderService.getInvestOrdersByState(state);

        investOrdersFail.forEach(order->{
            int currDay = (new Date()).getDay();
            int orderDay = order.getCreateTime().getDay();
            //不要把今天0点后投资的置位。一定要保证本job 零点后执行
            if (currDay != orderDay) {
                //置位
                order.setOrderState(InvestStatus.SUBSCRIBE_VERIFY_FAIL.getStatus());
                investOrderMapper.updateByPrimaryKeySelective(order);
                //退款
                String playerId = order.getOrderPayerId();
                BigDecimal order_amount = order.getOrderAmount();

                PlayerAccount playerAccount = playerAccountMapper.getPlayerAccountByPlayerId(playerId);
                playerAccount.setAccUsdt(playerAccount.getAccUsdt().add(order_amount));
                playerAccount.setAccUsdtAvailable(playerAccount.getAccUsdtAvailable().add(order_amount));
                playerAccountMapper.updatePlayerAccount(playerAccount);

                //出入账明细
                PlayerAccountLog playerAccountLog = new PlayerAccountLog();
                playerAccountLog.setCreateTime(new Date());
                playerAccountLog.setPlayerId(playerId);
                playerAccountLog.setAmountUsdt(order_amount);
                playerAccountLog.setType(1);//入账
                playerAccountLog.setDesc("投资预约审核失败,退款");
                playerAccountLogMapper.insertSelective(playerAccountLog);
            }

        });


    }
}
