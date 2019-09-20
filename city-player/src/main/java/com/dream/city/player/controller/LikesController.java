package com.dream.city.player.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerLikes;
import com.dream.city.base.model.entity.PlayerLikesLog;
import com.dream.city.player.domain.mapper.PlayerLikesLogMapper;
import com.dream.city.player.domain.req.PlayerLikesReq;
import com.dream.city.player.service.LikesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 点赞
 */
@RestController
@RequestMapping("/likes")
public class LikesController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LikesService likesService;
    @Autowired
    PlayerLikesLogMapper likesLogMapper;



    /**
     * 点赞
     * @param jsonReq
     * @return
     */
    @RequestMapping("/playerLike")
    Result<Integer> playerLike(@RequestBody String jsonReq){
        logger.info("点赞，{}",jsonReq);
        Result<Integer> result = new Result<>();
        boolean b = Boolean.FALSE;
        String msg = CityGlobal.Constant.USER_LIKES_FAIL;

        PlayerLikesReq playerLikes = getPlayerLikes(jsonReq);

        int i = 0;
        //当天点赞次数
        Integer countToday = likesService.playerTodayLikesCountToday(playerLikes);
        if (countToday != null && countToday > 0){
            msg = CityGlobal.Constant.USER_LIKES_ONLY_ONE_ETIME;
        }else {
            i = likesService.playerLike(playerLikes);
            if (i>0){
                b = Boolean.TRUE;
                msg = CityGlobal.Constant.USER_LIKES_SUCCESS;
            }
        }

        result.setData(i);
        result.setSuccess(b);
        result.setMsg(msg);
        return result;
    }




    /**
     * 取消点赞
     * @param jsonReq
     * @return
     */
    @RequestMapping("/cancelLike")
    Result<Integer> cancelLike(@RequestBody String jsonReq){
        logger.info("取消点赞，{}",jsonReq);
        Result<Integer> result = new Result<>();
        boolean b = Boolean.FALSE;
        String msg = "取消点赞失败";

        PlayerLikesReq playerLikes = getPlayerLikes(jsonReq);
        int i = likesService.cancelLike(playerLikes);

        if (i>0){
            b = Boolean.TRUE;
            msg = "取消点赞成功";
        }

        result.setData(i);
        result.setSuccess(b);
        result.setMsg(msg);
        return result;
    }

    /**
     * 玩家点赞总数
     * @param jsonReq
     * @return
     */
    @RequestMapping("/playerLikesCount")
    Result<Integer> playerLikesCount(@RequestBody String jsonReq){
        logger.info("获取玩家点赞总数，{}",jsonReq);
        Result<Integer> result = new Result<>();
        boolean b = Boolean.FALSE;
        String msg = "获取玩家点赞总数失败";

        PlayerLikesReq playerLikes = getPlayerLikes(jsonReq);
        int i = likesService.playerLikesCount(playerLikes);

        if (i>0){
            b = Boolean.TRUE;
            msg = "获取玩家点赞总数成功";
        }

        result.setData(i);
        result.setSuccess(b);
        result.setMsg(msg);
        return result;
    }

    /**
     * 点赞项目
     * @param jsonReq
     * @return
     */
    @RequestMapping("/playerLikesList")
    Result<String> playerLikesList(@RequestBody String jsonReq){
        logger.info("获取点赞项目，{}",jsonReq);
        Result<String> result = new Result<>();
        boolean b = Boolean.FALSE;
        String msg = "获取点赞项目失败";

        String data = null;
        try {
            PlayerLikesReq playerLikes = getPlayerLikes(jsonReq);
            List<PlayerLikes> likesList = likesService.playerLikesList(playerLikes);
            b = Boolean.TRUE;
            msg = "获取点赞项目成功";

            data = JSONObject.toJSONString(likesList);
        }catch (Exception e){
            b = Boolean.FALSE;
            msg = "获取点赞项目失败";
            logger.error(msg,e);
        }

        result.setData(data);
        result.setSuccess(b);
        result.setMsg(msg);
        return result;
    }



    private PlayerLikesReq getPlayerLikes(String jsonReq){
        Map map = JSON.parseObject(jsonReq,Map.class);
        String likedIdStr = map.containsKey("likedId")?(String)map.get("likedId"):null;
        String likedInvestIdStr = map.containsKey("likedInvestId")?(String)map.get("likedInvestId"):null;
        Integer likedInvestTotal = map.containsKey("likedInvestTotal")?(Integer)map.get("likedInvestTotal"):1;
        String likedPlayerId = map.containsKey("likedPlayerId")?(String)map.get("likedPlayerId"):null;
        String likePlayerId = map.containsKey("likePlayerId")?(String)map.get("likePlayerId"):null;

        Integer likedId = likedIdStr == null? null: Integer.parseInt(likedIdStr);
        Integer likedInvestId = likedInvestIdStr == null? null: Integer.parseInt(likedInvestIdStr);

        PlayerLikesReq likes = new PlayerLikesReq();
        likes.setLikedId(likedId);
        likes.setLikedPlayerId(likedPlayerId);
        likes.setLikedInvestId(likedInvestId);
        likes.setLikedInvestTotal(likedInvestTotal);
        likes.setLikePlayerId(likePlayerId);
        return likes;
    }



}
