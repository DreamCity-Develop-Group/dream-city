package com.dream.city.service;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.service.impl.FallBackPusherUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wvv
 */
@FeignClient(value = "city-worker", fallback = FallBackPusherUser.class)
public interface ConsumerWorkerService {

    /**
     * 添加任务
     * @param parentId
     * @param playerId
     * @param invite
     * @return
     */
    @RequestMapping("/worker/worker/createWorker")
    Result createWorker(Message msg);

}
