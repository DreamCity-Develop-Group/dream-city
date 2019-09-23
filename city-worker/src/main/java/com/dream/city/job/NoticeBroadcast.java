package com.dream.city.job;

import com.dream.city.base.utils.JsonUtil;
import com.dream.city.service.MessageService;
import com.dream.city.service.NoticeBroadcastService;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Map;

/**
 * @author Wvv
 */
public class NoticeBroadcast extends QuartzJobBean {
    @Autowired
    NoticeBroadcastService noticeBroadcastService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jsonString = dataMap.get("data").toString();
        Map data = JsonUtil.parseJsonToObj(jsonString,Map.class);
        //推送消息到客户端
        noticeBroadcastService.pushNoticeBroadcast("clients",data);
    }
}
