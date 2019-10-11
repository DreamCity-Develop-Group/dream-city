package com.dream.city.job;

import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.InvestStatus;
import com.dream.city.base.model.enu.RuleKey;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.InvestRuleService;
import com.dream.city.service.SalesOrderService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO 购买订单处理逻辑
 *
 * @author WVv
 */
@Slf4j
public class SalesOrderJob extends QuartzJobBean {

    @Autowired
    InvestRuleService ruleService;
    @Autowired
    SalesOrderService salesOrderService;
    @Autowired
    RedisUtils redisUtils;

    /**
     *TODO
     * 1、取出所有【在线玩家】【等待处理】订单集合
     * 1.1、取出总累计超时次数
     * 2、遍历所有订单，推送提示到玩家
     * 3、在2中，将已经超时的订单转接给【上上家】，生成转接记录，累计超时次数
     * 4、
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(new Date() + "    收益分配处理");

        //TODO 取出所有的投资项目
        List<SalesOrder> salesOrders = salesOrderService.getSalesOrdersByState(3);

        /**
         *TODO
         * 取出适应的所有规则
         */
        RuleItem ruleItem = ruleService.getInvestRuleItemByKey(RuleKey.SALES_OVERTIME.getKey());
        List<InvestRule> rules = ruleService.getInvestRuleByKey(ruleItem.getItemId());

        int[]states = new int[]{InvestStatus.MANAGEMENT.getStatus()};

        if (salesOrders != null){
            salesOrders.forEach(invest->{
                //直接找到上级的玩家
                //Map<String,List<InvestOrder>> orders = orderService.getInvestOrdersByCurrentDay(invest.getInId(),rules,states);
                /*orders.forEach((key,order)->{

                });*/

            });
        }

    }
}
