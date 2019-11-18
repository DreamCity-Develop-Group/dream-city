package com.dream.city.controller;

import com.dream.city.config.WorkItemsConfig;
import com.dream.city.job.*;

import com.dream.city.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 */
@RestController
@RequestMapping("/job")
@ConfigurationProperties(prefix = "worker")
public class DefaultController {

    @Autowired
    WorkItemsConfig workItemsConfig;

    @Autowired
    WorkerService workerService;

    @RequestMapping("/addJob")
    public void startSystemJob(String type, String name){
        if ("TestJob1".equals(type)){
            //workerService.addJob(ProfitGrantJob.class,"job1"+name,"test","0/10 * * * * ?");
        }else {
            //workerService.addJob(InvestOrderJob.class,"job2-"+name,"test","0/5 * * * * ?");
        }
        switch (name){
            case "ProfitGrantJobReload":
                workerService.addJob(ProfitGrantJobReload.class,"job-"+name,"test","0/20 * * * * ?");
            //订单预约结果计算任务
            case "InvestOrderJob":
                workerService.addJob(InvestOrderJob.class,"job-"+name,"test","0/5 * * * * ?");
                break;
                //收益掉落任务
            case "FallDownJob":
                workerService.addJob(FallDonwJob.class,"job-"+name,"test","0/5 * * * * ?");
                break;
                //掉落收益分配任务
            case "FallDownGrantJob":
                workerService.addJob(FallDonwGrantJob.class,"job-"+name,"test","0/5 * * * * ?");
                break;
                //Mt购买订单超时处理和商会关系处理任务
            case "SalesOrderJob":
                workerService.addJob(SalesOrderJob.class,"job-"+name,"test","0/5 * * * * ?");
                break;
            case "OrderOverTimeJob":
                workerService.addJob(OrderOverTimeJob.class,"job-"+name,"test","0/30 * * * * ?");
                break;
                //收益分配任务
            case "ProfitGrantJob":
                workerService.addJob(ProfitGrantJob.class,"job-"+name,"test","0/5 * * * * ?");
                break;
                //收益计算逻辑任务
            case "ProfitCalculateJob":
                workerService.addJob(ProfitCalculateJob.class,"job-"+name,"test","0/5 * * * * ?");
                break;
                //自动发货任务
            case "AutoSendJob":
                workerService.addJob(AutoSendJob.class,"job-"+name,"test","0/5 * * * * ?");
                break;
            case "ChargeScanJob":
                workerService.addJob(ChargeScanJob.class,"job-"+name,"test","0/5 * * * * ?");
                break;
                default:

                    break;
        }
    }

    @RequestMapping("/updateJob")
    public void updateJob(@RequestParam("jobName")String jobName,@RequestParam("jobGroupName")String jobGroupName) {
        workerService.updateJob(jobName, jobGroupName, "0/30 * * * * ?");
    }

    @RequestMapping("/deleteJob")
    public void deleteJob(@RequestParam("jobName")String jobName,@RequestParam("jobGroupName")String jobGroupName) {
        //workerService.deleteJob("job1", "test");
        workerService.deleteJob(jobName,jobGroupName);
    }

    @RequestMapping("/pauseJob")
    public void pauseJob(@RequestParam("jobName")String jobName,@RequestParam("jobGroupName")String jobGroupName) {
        workerService.pauseJob(jobName,jobGroupName);
    }

    @RequestMapping("/resumeJob")
    public void resumeJob(@RequestParam("jobName")String jobName,@RequestParam("jobGroupName")String jobGroupName) {
        workerService.resumeJob(jobName,jobGroupName);
    }

    @RequestMapping("/queryAllJob")
    public Object queryAllJob() {
        return workerService.queryAllJobs();
    }


    @RequestMapping("/queryRunJob")
    public Object queryRunJob() {
       /* List<String> topics = workItemsConfig.getTopics();
        topics.forEach(System.out::println);
        Map tasks = workItemsConfig.getTasks();
        tasks.forEach((key,item)-> System.out.println(key+"=>"+item));*/

        return workerService.queryRunJobs();
    }
}
