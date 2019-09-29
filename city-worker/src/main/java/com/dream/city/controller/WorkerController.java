package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.job.MessagePushRetry;
import com.dream.city.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Wvv
 */
@Slf4j
@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;


    @RequestMapping("/createWorker")
    public Result createWorker(@RequestBody Message message){
        log.info("{}",message);
        log.info("创建任务成功！");

        Object msgData = message.getData().getData();
        String map = JsonUtil.parseObjToJson(msgData);
        Map dataMap = JsonUtil.parseJsonToObj(map, Map.class);

        if (dataMap != null){
            int jobTime = Integer.parseInt(dataMap.get("jobTime").toString());
            int jobTimes = Integer.parseInt(dataMap.get("jobTimes").toString());
            String jobGroup = dataMap.get("jobGroup").toString();
            String jobName = dataMap.get("jobName").toString();

            workerService.addJob(MessagePushRetry.class,"MsgRetry-"+jobName,"Msg-"+jobGroup,jobTime,jobTimes,map);

            return Result.result(true,"创建任务成功！",200);
        }


        return Result.result(false,"没有任务数据！",500);
    }
}
