package com.dream.city.controller;

import com.dream.city.config.WorkItemsConfig;
import com.dream.city.job.ProfitCalculationJob;
import com.dream.city.job.InvestOrderJob;
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
            workerService.addJob(ProfitCalculationJob.class,"job1"+name,"test","0/5 * * * * ?");
        }else {
            workerService.addJob(InvestOrderJob.class,"job2-"+name,"test","0/5 * * * * ?");
        }
    }

    @RequestMapping("/updateJob")
    public void updateJob(@RequestParam("jobName")String jobName,@RequestParam("jobGroupName")String jobGroupName) {
        workerService.updateJob(jobName, jobGroupName, "0/10 * * * * ?");
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
        List<String> topics = workItemsConfig.getTopics();
        topics.forEach(System.out::println);
        Map tasks = workItemsConfig.getTasks();
        tasks.forEach((key,item)-> System.out.println(key+"=>"+item));

        return workerService.queryRunJobs();
    }
}
