package com.dream.city.controller;

import com.dream.city.job.MessagePushRetry;
import com.dream.city.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wvv
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    WorkerService workerService;

    @RequestMapping("/add5RetryJob")
    public String add5RetryJob(){
        workerService.addJob(MessagePushRetry.class,"messageRetry","message",1,5);
        return "success";
    }
}
