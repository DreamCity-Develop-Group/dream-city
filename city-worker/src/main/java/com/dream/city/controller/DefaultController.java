package com.dream.city.controller;

import com.dream.city.job.TestJob1;
import com.dream.city.job.TestJob2;
import com.dream.city.service.impl.WorkerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wvv
 */
@RestController
@RequestMapping("/job")
public class DefaultController {

    @Autowired
    WorkerServiceImpl workerService;

    @RequestMapping("/addJob")
    public void startJob(String type, String name){
        if ("TestJob1".equals(type)){
            workerService.addJob(TestJob1.class,"job1"+name,"test","0/5 * * * * ?");
        }else {
            workerService.addJob(TestJob2.class,"job2-"+name,"test","0/5 * * * * ?");
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
        return workerService.queryRunJobs();
    }
}
