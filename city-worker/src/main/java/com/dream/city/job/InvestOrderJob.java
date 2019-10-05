package com.dream.city.job;

import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RuleItem;
import com.dream.city.base.model.enu.InvestStatus;
import com.dream.city.service.InvestOrderService;
import com.dream.city.service.InvestRuleService;
import com.dream.city.service.InvestService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

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
    private InvestOrderService orderService;
    @Autowired
    private InvestService investService;
    @Autowired
    private InvestRuleService ruleService;

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
            Map<String, List<InvestOrder>> orders = orderService.getInvestOrdersByCurrentDay(invest.getInId(),rules,states);
            List<InvestOrder> orderList = new ArrayList<>();
            orders.forEach((flag,order)->{
                orderList.addAll(order);
            });
            orderService.setOrderState(orderList, InvestStatus.MANAGEMENT);
        });
    }
}
