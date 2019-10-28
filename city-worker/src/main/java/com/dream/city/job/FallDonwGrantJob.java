package com.dream.city.job;

import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RuleItem;
import com.dream.city.base.model.enu.InvestStatus;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.InvestOrderService;
import com.dream.city.service.InvestRuleService;
import com.dream.city.service.InvestService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *TODO 核算 计时玩家收益掉落并分配到新加入玩家
 * 1、取出掉落池中的收益
 * 2、遍历所有新加入玩家
 * 3、分配收益至每一个玩家
 *
 *
 * @author WVv
 */
@Slf4j
public class FallDonwGrantJob extends QuartzJobBean {

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
        log.info(new Date() + "    收益分配处理");
        //TODO 取出所有的投资项目
        List<CityInvest> invests = investService.getInvests();

        /**
         *TODO
         * 取出适应的所有规则
         */
        RuleItem ruleItem = ruleService.getInvestRuleItemByKey(RULE_CURRENT);
        List<InvestRule> rules = ruleService.getInvestRuleByKey(ruleItem.getItemId());

        int[]states = new int[]{InvestStatus.MANAGEMENT.getStatus()};
        invests.forEach(invest->{
            //直接找到所有应该受益的玩家
            Map<String,List<InvestOrder>> orders = orderService.getInvestOrdersByCurrentDay(invest.getInId(),rules,states);
            orders.forEach((key,order)->{
                
            });
        });
    }
}
