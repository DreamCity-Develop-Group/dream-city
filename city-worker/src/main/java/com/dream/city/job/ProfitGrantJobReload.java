package com.dream.city.job;

import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.job.thread.ThreadTask;
import com.dream.city.service.*;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO 核算 玩家每日收益结果
 *
 * @author WVv
 */
@Slf4j
public class ProfitGrantJobReload extends QuartzJobBean {

    @Autowired
    private InvestService investService;

    @Autowired
    private ThreadTask threadTask;

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
//        List<CityInvest> invests = investService.getInvests();
        CityInvest invests = investService.getInvestById(2);
//        for (CityInvest cityInvest:invests) {
        threadTask.profitGrant(invests);
//        }
    }

}
