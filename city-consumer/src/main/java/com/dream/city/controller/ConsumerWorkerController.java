package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.service.handler.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wvv
 */

@RestController
@RequestMapping("/consumer/worker")
@Slf4j
public class ConsumerWorkerController {

    @Autowired
    WorkerService workerService;

    @RequestMapping("/createWorker")
    public Message createWorker(@RequestBody Message msg) {
        try {
            return workerService.createWorker(msg);
        }catch (Exception e){
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }
}
