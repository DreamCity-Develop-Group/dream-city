package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.service.ConsumerWorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;

/**
 * @author Wvv
 */

@RestController
@RequestMapping("/consumer/worker")
@Slf4j
public class ConsumerWorkerController {

    @Autowired
    ConsumerWorkerService workerService;

    @RequestMapping("/createWorker")
    public Message createWorker(Message message) {
        Message ret;
        String task = message.getData().getType();
        Result result = workerService.createWorker(message);

        MessageData data = new MessageData(message.getData().getType(), message.getData().getModel(), result);
        ret = new Message(
                "city-worker",
                "city-comm",
                 data,
                "创建任务[" + task + "]失败",
                String.valueOf(System.currentTimeMillis())
        );

        if (result.getSuccess()) {
            log.info("创建任务成功");
            ret = new Message(
                    "city-worker",
                    "city-comm",
                     data,
                    "创建任务[" + task + "]成功",
                    String.valueOf(System.currentTimeMillis())
            );
        }
        log.info("创建任务失败");


        return ret;
    }
}
