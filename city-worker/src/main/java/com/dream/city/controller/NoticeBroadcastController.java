package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.entity.Notice;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.job.MessagePushRetry;
import com.dream.city.job.NoticeBroadcast;
import com.dream.city.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Wvv
 */
@RestController
@RequestMapping("/notice")
public class NoticeBroadcastController {
    @Autowired
    WorkerService workerService;

    @RequestMapping("/addNoticeJob")
    public String addNoticeJob(@RequestBody Notice notice){
        String map = JsonUtil.parseObjToJson(notice);
        Map dataMap = JsonUtil.parseJsonToObj(map, Map.class);

        if (dataMap != null){
            int jobTime = 5;
            int jobTimes = 5;
            String jobGroup = "NoticeGroup";
            String jobName = "Notice-"+notice.getNoticeId();

            workerService.addJob(NoticeBroadcast.class,jobName,jobGroup,jobTime,jobTimes,map);
            return "success";
        }
        return "fail";
    }
}
