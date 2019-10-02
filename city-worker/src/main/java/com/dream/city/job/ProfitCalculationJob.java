package com.dream.city.job;

import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.entity.InvestRule;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;

/**
 * TODO 核算 玩家每日收益结果
 *
 * @author WVv
 */
public class ProfitCalculationJob extends QuartzJobBean {

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
        log.info(new Date() + "    收益规则计算处理");
        //TODO 取出所有的投资项目，分别根据投资项目的总预约数以当前规则的比例筛选成功的成员
        InvestRule rules = null;

        List<CityInvest> invests = investService.getInvests();

        invests.forEach(invest->{
            //直接找出合格的投资订单，修改相应的状态
            List<InvestOrder> orders = orderService.getInvestOrdersByCurrentDay(invest.getInId(),rules);
            orders.forEach(order->{
                orderService.setOrderSuccess(order);
            });
        });
    }
}
