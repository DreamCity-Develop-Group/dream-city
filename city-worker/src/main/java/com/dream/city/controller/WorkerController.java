package com.dream.city.controller;

import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.job.MessagePushRetry;
import com.dream.city.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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


    @RequestMapping("/worker/createWorker")
    public Result createWorker(@RequestBody Message message){
        log.info("{}",message);
        log.info("创建任务成功！");

        Object msgData = message.getData().getData();
        String map = JsonUtil.parseObjToJson(msgData);
        JSONObject dataMap = JsonUtil.parseJsonToObj(map, JSONObject.class);

        if (dataMap != null){
            int jobTime = 5;
            if(dataMap.containsKey("jobTime")) {
                jobTime = dataMap.getInteger("jobTime");
            }

            int jobTimes = 5;
            if(dataMap.containsKey("jobTimes")) {
                jobTimes = dataMap.getInteger("jobTimes");
            }
            String jobGroup = "jobGroupDefault";
            if(dataMap.containsKey("jobGroup")){
                jobGroup =dataMap.getString("jobGroup");
            }else if (StringUtils.isNotBlank(message.getData().getModel())){
                jobGroup = message.getData().getModel();
            }
            String jobName = "jobNameDefault";
            if(dataMap.containsKey("jobGroup")){
                jobName = dataMap.getString("jobGroup");
            }else if (StringUtils.isNotBlank(message.getData().getType())) {
                jobName = message.getData().getType();
            }

            workerService.addJob(MessagePushRetry.class,"MsgRetry-"+jobName,"Msg-"+jobGroup,jobTime,jobTimes,map);

            return Result.result(true,"创建任务成功！",200);
        }


        return Result.result(false,"没有任务数据！",500);
    }
}
