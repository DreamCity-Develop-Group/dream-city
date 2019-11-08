package com.dream.city.player.controller;

import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerLikes;
import com.dream.city.base.model.req.PlayerLikesReq;
import com.dream.city.base.model.resp.PlayerLikesResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.model.mapper.PlayerLikesLogMapper;
import com.dream.city.player.service.LikesService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
     * 取消点赞
     * @param from
     * @param to
     * @return
     */
    @RequestMapping("/like")
    public Result like(@RequestParam("from") String from,@RequestParam("to") String to){
        logger.info("好友点赞，{}",from,"==>",to);

        return likesService.like(from,to);
    }


    /**
     * 当天是否可以点赞
     * 好友
     * @param jsonReq
     * @return
     */
    @RequestMapping("/canPlayerCanLikesToday")
    public Result<Integer> canPlayerCanLikesToday(@RequestBody String jsonReq){
        logger.info("当天是否可以点赞，{}",jsonReq);
        Result<Integer> result = new Result<>();
        boolean success = Boolean.TRUE;
        String msg = "今天是可以点赞";
        int countToday = 0;

        PlayerLikesReq playerLikes = DataUtils.getPlayerLikes(jsonReq);
        if (StringUtils.isBlank(playerLikes.getLikedPlayerId()) || StringUtils.isBlank(playerLikes.getLikedPlayerId())){
            msg = "点赞人和被赞人都不能为空";
        }else {
            //当天点赞次数
            countToday = likesService.playerLikesCountToday(playerLikes);
            if (countToday > 0){
                success = Boolean.FALSE;
                msg = CityGlobal.Constant.USER_LIKES_ONLY_ONE_ETIME;
            }
        }

        result.setData(countToday);
        result.setSuccess(success);
        result.setMsg(msg);
        return result;
    }


    /**
     * 当天是否可以点赞
     * 投资
     * @param jsonReq
     * @return
     */
    @RequestMapping("/canInvestLikesToday")
    public Result<Integer> canInvestLikesToday(@RequestBody String jsonReq){
        logger.info("当天是否可以点赞，{}",jsonReq);
        Result<Integer> result = new Result<>();
        boolean success = Boolean.TRUE;
        String msg = "今天是可以点赞";
        int countToday = 0;

        PlayerLikesReq playerLikes = DataUtils.getPlayerLikes(jsonReq);
        if (StringUtils.isBlank(playerLikes.getLikedPlayerId())
                || StringUtils.isBlank(playerLikes.getLikedPlayerId())
                || playerLikes.getLikedInvestId() == null){
            msg = "点赞人、被赞人和项目不能为空";
        }else {
            //当天点赞次数
            countToday = likesService.investLikesCountToday(playerLikes);
            if (countToday > 0){
                success = Boolean.FALSE;
                msg = CityGlobal.Constant.USER_LIKES_ONLY_ONE_ETIME;
            }
        }

        result.setData(countToday);
        result.setSuccess(success);
        result.setMsg(msg);
        return result;
    }


    /**
     * 点赞
     * @param jsonReq
     * @return
     */
    @RequestMapping("/playerLike")
    public Result<Integer> playerLike(@RequestBody String jsonReq){
        logger.info("点赞，{}",jsonReq);
        Result<Integer> result = new Result<>();
        boolean b = Boolean.FALSE;
        String msg = CityGlobal.Constant.USER_LIKES_FAIL;

        PlayerLikesReq playerLikes = DataUtils.getPlayerLikes(jsonReq);

        int i = 0;
        //当天点赞次数
        int countToday = likesService.playerLikesCountToday(playerLikes);
        if (countToday > 0){
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
    public Result<Integer> cancelLike(@RequestBody String jsonReq){
        logger.info("取消点赞，{}",jsonReq);
        Result<Integer> result = new Result<>();
        boolean b = Boolean.FALSE;
        String msg = "取消点赞失败";

        PlayerLikesReq playerLikes = DataUtils.getPlayerLikes(jsonReq);
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
    public Result<Integer> playerLikesCount(@RequestBody String jsonReq){
        logger.info("获取玩家点赞总数，{}",jsonReq);
        Result<Integer> result = new Result<>();
        boolean b = Boolean.FALSE;
        String msg = "获取玩家点赞总数失败";

        PlayerLikesReq playerLikes = DataUtils.getPlayerLikes(jsonReq);
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
    public Result<String> playerLikesList(@RequestBody String jsonReq){
        logger.info("获取点赞项目，{}",jsonReq);
        Result<String> result = new Result<>();
        boolean b = Boolean.FALSE;
        String msg = "获取点赞项目失败";

        String data = null;
        try {
            PlayerLikesReq playerLikes = DataUtils.getPlayerLikes(jsonReq);
            Page pageReq = new Page();
            pageReq.setCondition(playerLikes);
            pageReq.setPages(1000000);
            PageInfo<PlayerLikesResp> pageInfo = likesService.playerLikesList(pageReq);
            List<PlayerLikesResp> likesList = pageInfo.getList();
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






}
