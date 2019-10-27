package com.dream.city.service.consumer;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wvv
 */
@FeignClient(value = "city-comm")
public interface ConsumerPusherService {

    @RequestMapping("/comm/job/push")
    Result jobPush(@RequestBody Message message);

    @RequestMapping("/comm/job/push/notice")
    Result noticePush(Message message);


    void receive(Player player, int type);
}
