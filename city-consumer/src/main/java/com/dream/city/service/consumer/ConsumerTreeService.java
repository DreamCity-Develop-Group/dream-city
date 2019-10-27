package com.dream.city.service.consumer;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.service.consumer.impl.FallBackPusherUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author wvv
 */
@FeignClient(value = "city-tree", fallback = FallBackPusherUser.class)
public interface ConsumerTreeService {

    /**
     * 添加关系
     *
     * @param parentId
     * @param playerId
     * @param invite
     * @return
     */
    @RequestMapping("/tree/add/tree")
    Result addTree(@RequestParam("parentId") String parentId, @RequestParam("playerId") String playerId, @RequestParam("invite") String invite);

    /**
     * 找到对应的关系玩家
     *
     * @param playerId
     * @return
     */
    @RequestMapping("/tree/get/tree/{playerId}")
    Result getTree(@PathVariable("playerId") String playerId);

    @RequestMapping("/tree/get/relationTree")
    RelationTree getRelationTree(@RequestParam("playerId") String playerId);

    /**
     * 获取投资许可
     *
     * @param playerId
     * @return
     */
    @RequestMapping("/tree/invest/allowed")
    Result getInvestAllowed(@RequestParam("playerId") String playerId);

    @RequestMapping("/tree/get/by/relation")
    RelationTree getTreeByRelation(@RequestParam("relation") String relation);

    @RequestMapping("/tree/invest/join")
    Result joinInvestAllow(@RequestParam("playerId") String playerId, @RequestParam("amount") BigDecimal amount);

    @RequestMapping("/tree/find/Level")
    Result getMembers(@RequestParam("playerId") String playerId, @RequestParam("level") Integer level);

    @RequestMapping("/tree/get/salesOrder")
    Result getSalesOrder(@RequestParam("playerId") String playerId, @RequestParam("page") Integer page);

    @RequestMapping("/tree/set/autoSend")
    Result setAutoSend(@RequestParam("playerId") String playerId);

    @RequestMapping("/tree/set/autoSendClose")
    Result setAutoSendClose(@RequestParam("playerId") String playerId);

    @RequestMapping("/sales/player/buy/mt")
    Result createOrder(@RequestParam("playerId") String playerId, @RequestParam("amount") BigDecimal amount);

    @RequestMapping("/sales/player/check/pass")
    Result checkOrderPass(@RequestParam("playerId") String playerId, @RequestParam("confirmPass") String confirmPass);

    @RequestMapping("/sales/get/salesOrder")
    Result getOrderList(@RequestParam("playerId") String playerId, @RequestParam("page") Integer page);

    @RequestMapping("/sales/player/seller/send")
    Result sendOrder(@RequestParam("playerId") String playerId, @RequestParam("ordersId") String ordersId);


}
