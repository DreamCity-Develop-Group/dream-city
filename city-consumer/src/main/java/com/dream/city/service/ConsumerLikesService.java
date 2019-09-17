package com.dream.city.service;


import com.dream.city.base.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "city-player")
public interface ConsumerLikesService {


    /**
     * 点赞
     * @param jsonReq
     * @return
     */
    @RequestMapping("/friends/playerLike")
    Result<Integer> playerLike(@RequestBody String jsonReq);

    /**
     * 取消点赞
     * @param jsonReq
     * @return
     */
    @RequestMapping("/friends/cancelLike")
    Result<Integer> cancelLike(@RequestBody String jsonReq);

    /**
     * 玩家点赞总数
     * @param jsonReq
     * @return
     */
    @RequestMapping("/friends/playerLikesCount")
    Result<Integer> playerLikesCount(@RequestBody String jsonReq);

    /**
     * 点赞项目
     * @param jsonReq
     * @return
     */
    @RequestMapping("/friends/playerLikesList")
    Result<String> playerLikesList(@RequestBody String jsonReq);

}