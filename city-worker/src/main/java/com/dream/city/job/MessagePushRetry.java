package com.dream.city.job;

import com.dream.city.base.utils.JsonUtil;
import com.dream.city.service.MessageService;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Map;

/**
 * @author Wvv
 */
public class MessagePushRetry extends QuartzJobBean {
    @Autowired
    MessageService messageService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        //String clientId = dataMap.get("data").toString();
        String jsonString = dataMap.get("data").toString();
        Map data = JsonUtil.parseJsonToObj(jsonString,Map.class);
        //推送消息到客户端
        messageService.pushRetry(data.get("username").toString(),data);
    }


}
