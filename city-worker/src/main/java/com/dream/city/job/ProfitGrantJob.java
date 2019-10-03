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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private InvestOrderService orderService;

    @Autowired
    private InvestRuleService ruleService;

    private final String RULE_CURRENT = "PROFIT_GRANT";
    private final String PROFIT_QUEUE = "PROFIT_QUEUE";

    @Autowired
    RedisUtils redisUtils;


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

        int[] states = new int[]{InvestStatus.MANAGEMENT.getStatus()};
        invests.forEach(invest -> {
            rules.forEach(rule->{
                BigDecimal profitSum = new BigDecimal(redisUtils.lpop(PROFIT_QUEUE+"_"+invest.getInId()).toString());
                switch (rule.getRuleOptPre()){
                    case "OPT_RATE":
                        switch (rule.getRuleFlag()){
                            case "ALL_ORDERS":
                                //1、所有玩家，40%
                                BigDecimal allOrdersProfit = profitSum.multiply(new BigDecimal(rule.getRuleRate()));

                                int start = 0, end = 100;
                                //查出一共多少条数据
                                int sum = orderService.getInvestOrdersSum(invest.getInId(), states);
                                //每个玩家的获得利益数
                                BigDecimal everyOneProfit = allOrdersProfit.divide(new BigDecimal(sum));
                                while (sum > 0) {
                                    List<InvestOrder> orders = orderService.getInvestOrdersByCurrent(invest.getInId(), states, start, end);
                                    orders.forEach((order) -> {
                                        //设置玩家获利
                                        //
                                    });
                                    sum -= 100;
                                    start += 100;
                                    end += 100;
                                }
                                //2、新增会员最多前20 20%、
                                //3、第一次投资前20% 20%
                                //4、投资时间最长前10 10%
                                //获得点赞最多前20    10%
                                //直接找到所有应该受益的玩家
                                break;
                            case "FIRST_TIME":

                                break;
                            default:

                        }
                        break;
                    case "OPT_TOP":
                        switch (rule.getRuleFlag()){
                            case "TOP_MEMBERS":
                                break;
                            case "LIKES_GATHER":
                                break;
                            case "INVEST_LONG":
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
}
