package com.dream.city.service;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.service.impl.FallBackPusherUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wvv
 */
@FeignClient(value = "city-tree", fallback = FallBackPusherUser.class)
public interface ConsumerTreeService {

    /**
     * 添加关系
     * @param parentId
     * @param playerId
     * @param invite
     * @return
     */
    @RequestMapping("/tree/add/tree")
    Result addTree(@RequestParam("parentId")String parentId, @RequestParam("playerId")String playerId,@RequestParam("invite")String invite);

    /**
     * 找到对应的关系玩家
     * @param playerId
     * @return
     */
    @RequestMapping("/tree/get/tree")
    Result getTree(@RequestParam("playerId")String playerId);
}
