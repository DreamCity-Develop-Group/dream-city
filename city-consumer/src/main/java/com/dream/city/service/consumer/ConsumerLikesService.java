package com.dream.city.service.consumer;


import com.dream.city.base.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "city-player")
public interface ConsumerLikesService {


    /**
     * 当天是否可以点赞
     * 好友
     * @param jsonReq
     * @return
     */
    @RequestMapping("/likes/canPlayerCanLikesToday")
    Result<Integer> canPlayerCanLikesToday(@RequestBody String jsonReq);

    /**
     * 当天是否可以点赞
     * 投资
     * @param jsonReq
     * @return
     */
    @RequestMapping("/likes/canInvestLikesToday")
    Result<Integer> canInvestLikesToday(@RequestBody String jsonReq);

    /**
     * 点赞
     * @param jsonReq
     * @return
     */
    @RequestMapping("/likes/playerLike")
    Result<Integer> playerLike(@RequestBody String jsonReq);

    /**
     * 取消点赞
     * @param jsonReq
     * @return
     */
    @RequestMapping("/likes/cancelLike")
    Result<Integer> cancelLike(@RequestBody String jsonReq);

    /**
     * 玩家点赞总数
     * @param jsonReq
     * @return
     */
    @RequestMapping("/likes/playerLikesCount")
    Result<Integer> playerLikesCount(@RequestBody String jsonReq);

    /**
     * 点赞项目
     * @param jsonReq
     * @return
     */
    @RequestMapping("/likes/playerLikesList")
    Result<String> playerLikesList(@RequestBody String jsonReq);

}