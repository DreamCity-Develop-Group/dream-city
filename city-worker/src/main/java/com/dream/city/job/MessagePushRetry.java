package com.dream.city.job;

import com.dream.city.service.MessageService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author Wvv
 */
public class MessagePushRetry extends QuartzJobBean {
    @Autowired
    MessageService messageService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //推送消息到客户端
        messageService.pushRetry();
    }
}
