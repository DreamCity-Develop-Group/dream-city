package com.dream.city.job;

import com.dream.city.service.impl.UserServiceImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestJob2 extends QuartzJobBean {
    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date() + "    job2执行");
        userService.play();
    }
}
