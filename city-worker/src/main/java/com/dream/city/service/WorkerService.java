package com.dream.city.service;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.*;

/**
 * @author Wvv
 */
public interface WorkerService {
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, int jobTime, int jobTimes,Map data);
    /**
     * 增加一个job
     *
     * @param jobClass     任务实现类
     * @param jobName      任务名称
     * @param jobGroupName 任务组名
     * @param jobTime      时间表达式 (这是每隔多少秒为一次任务)
     * @param jobTimes     运行的次数 （<0:表示不限次数）
     */
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, int jobTime, int jobTimes);
    /**
     * 增加一个job
     *
     * @param jobClass     任务实现类
     * @param jobName      任务名称
     * @param jobGroupName 任务组名
     * @param jobTime      时间表达式 （如：0/5 * * * * ? ）
     */
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, String jobTime) ;

    public void updateJob(String jobName, String jobGroupName, String jobTime);

    public void deleteJob(String jobName, String jobGroupName) ;

    /**
     * 暂停一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    public void pauseJob(String jobName, String jobGroupName) ;

    /**
     * 恢复一个已经暂停的job
     * @param jobName
     * @param jobGroupName
     */
    public void resumeJob(String jobName, String jobGroupName) ;

    /**
     * 执行一个job
     * @param jobName
     * @param jobGroupName
     */
    public void runJobNow(String jobName,String jobGroupName);

    /**
     * 查询当前所有的job列表
     * @return
     */
    public List<Map<String,Object>> queryAllJobs();

    /**
     * 查询所有运行的job列表
     * @return
     */
    public List<Map<String,Object>> queryRunJobs();
}
