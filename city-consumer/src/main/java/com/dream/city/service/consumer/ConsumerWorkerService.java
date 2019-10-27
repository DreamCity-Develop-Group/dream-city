package com.dream.city.service.consumer;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wvv
 */
@FeignClient(value = "city-worker")
public interface ConsumerWorkerService {

    /**
     * 添加任务
     * @param msg
     * @return
     */
    @RequestMapping("/worker/worker/createWorker")
    Result createWorker(Message msg);

}
