package com.dream.city.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * TODO 提现扫描任务
 *
 * @author WVv
 */
public class WithdrawVerifyJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date() +"   WithdrawVerifyJob 执行");
    }
}
