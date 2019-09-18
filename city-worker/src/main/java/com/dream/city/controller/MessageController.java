package com.dream.city.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.job.MessagePushRetry;
import com.dream.city.service.WorkerService;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Wvv
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    WorkerService workerService;

    @RequestMapping("/add5RetryJob")
    public String add5RetryJob(@RequestBody Message message){
        Object msgData = message.getData().getT();
        String map = JsonUtil.parseObjToJson(msgData);
        Map dataMap = JsonUtil.parseJsonToObj(map, Map.class);

        if (dataMap != null){
            int jobTime = Integer.parseInt(dataMap.get("jobTime").toString());
            int jobTimes = Integer.parseInt(dataMap.get("jobTimes").toString());
            String jobGroup = dataMap.get("jobGroup").toString();
            String jobName = dataMap.get("jobName").toString();

            workerService.addJob(MessagePushRetry.class,"MsgRetry-"+jobName,"Msg-"+jobGroup,jobTime,jobTimes,map);
            return "success";
        }
        return "fail";
    }
}
