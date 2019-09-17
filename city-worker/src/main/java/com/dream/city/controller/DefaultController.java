package com.dream.city.controller;

import com.dream.city.job.TestJob1;
import com.dream.city.job.TestJob2;
import com.dream.city.service.impl.WorkerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public void updateJob() {
        workerService.updateJob("job1", "test", "0/10 * * * * ?");
    }

    @RequestMapping("/deleteJob")
    public void deleteJob() {
        workerService.deleteJob("job1", "test");
    }

    @RequestMapping("/pauseJob")
    public void pauseJob() {
        workerService.pauseJob("job1", "test");
    }

    @RequestMapping("/resumeJob")
    public void resumeJob() {
        workerService.resumeJob("job1", "test");
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
