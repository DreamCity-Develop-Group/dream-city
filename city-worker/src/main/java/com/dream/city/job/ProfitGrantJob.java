package com.dream.city.job;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.InvestStatus;
import com.dream.city.base.model.resp.PlayerEarningResp;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.*;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * TODO 核算 玩家每日收益结果
 *
 * @author WVv
 */
@Slf4j
public class ProfitGrantJob extends QuartzJobBean {

    @Autowired
    InvestService investService;

    @Autowired
    PlayerAccountService accountService;

    @Autowired
    private InvestOrderService orderService;

    @Autowired
    private InvestRuleService ruleService;

    @Autowired
    private PlayerEarningService playerEarningService;

    @Autowired
    private RelationTreeService relationTreeService;

    @Autowired
    RedisUtils redisUtils;

    private final String RULE_CURRENT = "PROFIT_GRANT";
    private final String PROFIT_QUEUE = "PROFIT_QUEUE";
    private final String SYSTEM_ACCOUNT = "SYSTEM_DC_ACCOUNT";




    /**
     * TODO
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
        log.info(new Date() + "    收益分配处理");
        //TODO 取出所有的投资项目
        List<CityInvest> invests = investService.getInvests();

        /**
         *TODO
         * 取出适应的所有规则
         */
        RuleItem ruleItem = ruleService.getInvestRuleItemByKey(RULE_CURRENT);
        List<InvestRule> rules = ruleService.getInvestRuleByKey(ruleItem.getItemId());

        int state = InvestStatus.MANAGEMENT.getStatus();
        invests.forEach(invest -> {
            rules.forEach(rule -> {
                Object obj = redisUtils.lpop(PROFIT_QUEUE + "_" + invest.getInId());
                if (obj == null){
                    log.info(new Date() + "，invest.getInId() = "+invest.getInId()+",  redisUtils.lpop 为null，中途退出");
                    return;
                }
                BigDecimal profitSum = new BigDecimal(obj.toString());
                switch (rule.getRuleOptPre()) {
                    case "OPT_RATE":
                        switch (rule.getRuleFlag()) {
                            case "ALL_ORDERS":
                                //1、所有玩家，40%
                                BigDecimal allOrdersProfit = profitSum.multiply(rule.getRuleRate());

                                int start = 0, end = 100;
                                //查出一共多少条数据
                                int[] statesSum = new int[]{InvestStatus.MANAGEMENT.getStatus()};
                                int sum = orderService.getInvestOrdersSum(invest.getInId(), statesSum);
                                //每个玩家的获得利益数
                                BigDecimal everyOneProfit = allOrdersProfit.divide(new BigDecimal(sum));
                                while (sum > 0) {
                                    List<InvestOrder> orders = orderService.getInvestOrdersByCurrent(invest.getInId(), statesSum, start, end);
                                    for (InvestOrder order : orders) {//设置玩家获利
                                        setProfit(order,everyOneProfit);
                                        //
                                    }
                                    sum -= 100;
                                    start += 100;
                                    end += 100;
                                }
                                //2、新增会员最多前20 20%、

                                //4、投资时间最长前10 10%
                                //获得点赞最多前20    10%
                                //直接找到所有应该受益的玩家
                                break;
                            case "FIRST_TIME":
                                //3、第一次投资前20% 20%
                                BigDecimal firstTimeOrdersProfit = profitSum.multiply(rule.getRuleRate());
                                BigDecimal top = rule.getRuleRatePre();
                                Map<String,String> calTime = investService.getProfitCalculateTime(invest.getCreateTime());
                                String startTime = calTime.get("start");
                                String endTime = calTime.get("end");
                                //取出，预约辊的所有订单
                                //第一次的投资订单
                                List<InvestOrder> orders = orderService.getInvestOrdersFirstTime(invest.getInId());
                                //总人数
                                BigDecimal firstSum = new BigDecimal(orders.size());
                                int topSum = firstSum.multiply(top).intValue();
                                //每份收益
                                BigDecimal firstTimeOrdersProfitPer = firstTimeOrdersProfit.divide(firstSum);
                                orders.sort((o1,o2)->{
                                    return (int)(o2.getCreateTime().getTime() - o1.getCreateTime().getTime());
                                });
                                orders = orders.subList(0,topSum);
                                orders.forEach(order->{
                                    setProfit(order,firstTimeOrdersProfitPer);
                                });
                                break;
                            default:

                        }
                        break;
                    case "OPT_TOP":
                        switch (rule.getRuleFlag()) {
                            case "TOP_MEMBERS":
                                int start = 0, end = 100;
                                long top = rule.getRuleRatePre().longValue();
                                BigDecimal topMembersProfit = profitSum.multiply(rule.getRuleRate());
                                BigDecimal everyTopMembersProfit = topMembersProfit.divide(new BigDecimal(top));
                                //查出一共多少条数据
                                int[] statesSum = new int[]{InvestStatus.MANAGEMENT.getStatus()};
                                int sum = orderService.getInvestOrdersSum(invest.getInId(), statesSum);
                                List<InvestOrder> topOrders = new ArrayList<>();
                                while (sum > 0) {
                                    List<InvestOrder> orders = orderService.getInvestOrdersByCurrent(invest.getInId(), statesSum, start, end);
                                    List<InvestOrder> topOrders1 = orderService.getTopMembersOrders(orders, top);
                                    topOrders = filterTops(topOrders,topOrders1,top);
                                    sum -= 100;
                                    start += 100;
                                    end += 100;
                                }
                                topOrders.forEach(order->{
                                    setProfit(order,everyTopMembersProfit);
                                });

                                break;
                            case "LIKES_GATHER":
                                long topLike = rule.getRuleRatePre().longValue();
                                BigDecimal topLikesProfit = profitSum.multiply(rule.getRuleRate());
                                BigDecimal everyTopLikesProfit = topLikesProfit.divide(new BigDecimal(topLike));
                                //查出一共多少条数据
                                start = 0;
                                end = 100;
                                List<InvestOrder> topLikesOrders = new ArrayList<>();
                                statesSum = new int[]{InvestStatus.MANAGEMENT.getStatus()};
                                sum = orderService.getInvestOrdersSum(invest.getInId(), statesSum);
                                while (sum > 0) {
                                    List<InvestOrder> orders = orderService.getInvestOrdersByCurrent(invest.getInId(), statesSum, start, end);
                                    List<InvestOrder> topOrders1 = orderService.getLiksGatherOrders(orders, topLike);
                                    topLikesOrders = filterTops(topLikesOrders,topOrders1,topLike);
                                    sum -= 100;
                                    start += 100;
                                    end += 100;
                                }
                                topLikesOrders.forEach(order->{
                                    setProfit(order,everyTopLikesProfit);
                                });
                                break;
                            case "INVEST_LONG":
                                long topLong = rule.getRuleRatePre().longValue();
                                BigDecimal topLongProfit = profitSum.multiply(rule.getRuleRate());
                                BigDecimal everyTopLongProfit = topLongProfit.divide(new BigDecimal(topLong));
                                //查出一共多少条数据
                                start = 0;
                                end = 100;
                                statesSum = new int[]{InvestStatus.MANAGEMENT.getStatus()};
                                sum = orderService.getInvestOrdersSum(invest.getInId(), statesSum);
                                List<InvestOrder> topLongOrders = new ArrayList<>();
                                while (sum > 0) {
                                    List<InvestOrder> orders = orderService.getInvestOrdersByCurrent(invest.getInId(), statesSum, start, end);
                                    List<InvestOrder> topOrders1 = orderService.getInvestLongTimeOrders(orders, topLong);
                                    topLongOrders = filterTops(topLongOrders,topOrders1,topLong);
                                    sum -= 100;
                                    start += 100;
                                    end += 100;
                                }
                                topLongOrders.forEach(order->{
                                    setProfit(order,everyTopLongProfit);
                                });
                                break;
                            default:
                        }
                        break;
                    case "ORDER_OTHERS":
                        break;
                    default:
                }
            });
        });
    }
    private List<InvestOrder> filterTops(List<InvestOrder>source,List<InvestOrder>target,long sum){
        if (source.size()==0){
            return target;
        }
        source.addAll(target);
        source.sort((o1,o2)->{
            return relationTreeService.getMembersIncrement(
                    o2.getOrderPayerId(), investService.getEndTimeAt(o2.getOrderInvestId())
            ) - relationTreeService.getMembersIncrement(
                    o1.getOrderPayerId(), investService.getEndTimeAt(o1.getOrderInvestId())
            );
        });
        List<InvestOrder> orders = source.subList(0, (int) sum);
        return orders;
    }
    @LcnTransaction
    @Transactional
    public void setProfit(InvestOrder order,BigDecimal everyOneProfit){
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
            //相加之后超过
            if(current.add(everyOneProfit).compareTo(fullProfit)>0){
                isNotCanWithdraw = false;
                subProfit = fullProfit.subtract(current);
                playerEarning.setEarnCurrent(current.add(subProfit));
                playerEarning.setIsWithdrew(2);//设置为可提取状态
            }else{
                playerEarning.setEarnCurrent(current.add(everyOneProfit));
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
