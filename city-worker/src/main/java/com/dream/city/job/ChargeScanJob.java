package com.dream.city.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * TODO 充值扫描任务
 *
 * @author WVv
 */
public class ChargeScanJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date() +"   ChargeScanJob 执行");
    }
}
