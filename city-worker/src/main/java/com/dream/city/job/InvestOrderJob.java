package com.dream.city.job;

import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.service.InvestOrderService;
import com.dream.city.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * TODO 计算 玩家投资预约结果
 *
 * @author WVv
 */
@Slf4j
@Component
public class InvestOrderJob extends QuartzJobBean {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private InvestOrderService orderService;
    @Autowired
    private InvestService investService;

    /**
     *
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(new Date() + "    job2执行");
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
