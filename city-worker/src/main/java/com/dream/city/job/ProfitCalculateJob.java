package com.dream.city.job;

import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.InvestOrderService;
import com.dream.city.service.InvestRuleService;
import com.dream.city.service.InvestService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * TODO 核算 玩家每日收益结果
 *
 * @author WVv
 */
@Slf4j
public class ProfitCalculateJob extends QuartzJobBean {
    @Autowired
    InvestService investService;

    @Autowired
    private InvestOrderService orderService;

    @Autowired
    private InvestRuleService ruleService;

    private final String RULE_CURRENT = "PROFIT_GRANT";
    private final String ProfitQueue =  "PROFIT_QUEUE";

    @Autowired
    RedisUtils redisUtils;

    /**
     * TODO
     * 1、取出所有投资项目
     * 2、遍历所有投资项目预约的成功项目
     * 3、在2中，直接取得所有的新的增加的资金
     * 4、将资金分成以下几份（几份也就是几波可视利益）：【不平均的15份】
     * 【8点档，9点档，10点档，11点档，12点档，13点档，14点档，15点档，16点档，17点档，18点档，19点档，20点档，21点档，22点档】
     * 5、根据规则，遍历根据规则找出所有的用户，按相应的分配利益给到每一个玩家
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info(new Date() + "    收益规则计算处理");
        //TODO 取出所有的投资项目
        List<CityInvest> invests = investService.getInvests();


        invests.forEach(invest -> {
            long size = redisUtils.lGetListSize(ProfitQueue+"_"+invest.getInId());
            if(size>0){
                return;
            }
            Map<String,String> calTime = investService.getProfitCalculateTime(invest.getInStart());
            String start = calTime.get("start");
            String end = calTime.get("end");

            //直接找出成功的投资订单，计算新增的资金额度
            List<InvestOrder> orders = orderService.getInvestOrdersAmountByDayInterval(invest.getInId(), start, end);
            if(orders.size()<=0){
                return ;
            }
            //计算总的新增资金总额度
            BigDecimal total = BigDecimal.ZERO;
            for (InvestOrder order : orders) {
                total = total.add(order.getOrderAmount());
            }

            BigDecimal temp = BigDecimal.ZERO;//累计已添加到队列的收益
            BigDecimal rate = new BigDecimal(0.7);//随机数
            BigDecimal onceProfit = BigDecimal.ZERO;//每次添加的收益

            for (int i = 0; i < 10; i++) {
                int num = (int) (Math.random() * 5 + 1);
                rate = new BigDecimal(num).divide(BigDecimal.TEN);
                onceProfit = total.multiply(rate);
                if(i==9){
                    redisUtils.lpush(ProfitQueue+"_"+invest.getInId(),String.valueOf(total.subtract(temp)));
                }else{
                    redisUtils.lpush(ProfitQueue+"_"+invest.getInId(),String.valueOf(onceProfit));
                    temp = temp.add(onceProfit);
                }
            }


//            //平均分成15份
//            BigDecimal average = total.divide(new BigDecimal(10));
//            BigDecimal[] averages = new BigDecimal[10];
//            for (int i = 0; i < 15; i++) {
//                double x = Math.floor(Math.random() * 10);
//                double y = x / 40;
//                System.out.println(y);
//                BigDecimal subtract = average.multiply(new BigDecimal(y));
//                averages[i] = subtract.add(average);
//                averages[i + 7] = average.subtract(subtract);
//                if (i==7){
//                    break;
//                }
//            }
            /**
             *TODO
             * 将计算结果放入 Redis队列 ProfitQueue
             */

//            for(int i=0;i<averages.length;i++){
//                System.out.println("i="+i+","+averages[i]);
//                redisUtils.lpush(ProfitQueue+"_"+invest.getInId(),String.valueOf(averages[i]));
//            }

        });
    }
}
