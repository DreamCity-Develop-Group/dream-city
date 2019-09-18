package com.dream.city.service;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.service.impl.FallBackPlayer;
import com.dream.city.service.impl.FallBackPusherUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wvv
 */
@FeignClient(value = "city-comm", fallback = FallBackPusherUser.class)
public interface ConsumerPusherService {

    @RequestMapping("/comm/job/push")
    Result jobPush(@RequestBody Message message);
}
