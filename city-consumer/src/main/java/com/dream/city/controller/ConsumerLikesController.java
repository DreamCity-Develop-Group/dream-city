package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.service.handler.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 点赞
 */
@Api(value = "点赞", description = "点赞")
@RestController
@RequestMapping("/consumer/player/like")
public class ConsumerLikesController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LikeService likeService;


    /**
     * 当天是否可以点赞
     * 好友
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "当天是否可以点赞", httpMethod = "POST", notes = "当天是否可以点赞（好友）", response = Message.class)
    @RequestMapping("/canLikePlayerToday")
    public Message canLikePlayerToday(@RequestBody Message msg) {
        try {
            return likeService.canLikePlayerToday(msg);
        } catch (Exception e) {
            return msg;
        }
    }


    /**
     * 当天是否可以点赞
     * 投资
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "当天是否可以点赞", httpMethod = "POST", notes = "当天是否可以点赞（投资）", response = Message.class)
    @RequestMapping("/canLikeInvestToday")
    public Message canLikeInvestToday(@RequestBody Message msg) {
        try {
            return likeService.canLikeInvestToday(msg);
        } catch (Exception e) {
            return msg;
        }
    }


    /**
     * 点赞
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "点赞", httpMethod = "POST", notes = "点赞", response = Message.class)
    @RequestMapping("/likefriend")
    public Message playerLike(@RequestBody Message msg) {
        try {
            return likeService.playerLike(msg);
        } catch (Exception e) {
            return msg;
        }
    }

    /**
     * 取消点赞
     * @param msg
     * @return
     */
    /*@RequestMapping("/cancelLike")
    public Message cancelLike(@RequestBody Message msg){
        try {
            return likeService.canLikePlayerToday(msg);
        }catch (Exception e){
            return msg;
        }
    }*/

    /**
     * 玩家被点赞总数
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "玩家被点赞总数", httpMethod = "POST", notes = "玩家被点赞总数", response = Message.class)
    @RequestMapping("/likesCount")
    public Message playerLikesCount(@RequestBody Message msg) {
        try {
            return likeService.playerLikesCount(msg);
        } catch (Exception e) {
            return msg;
        }
    }

    /**
     * 点赞项目
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "点赞项目", httpMethod = "POST", notes = "点赞项目", response = Message.class)
    @RequestMapping("/likesList")
    public Message playerLikesList(@RequestBody Message msg) {
        try {
            return likeService.playerLikesList(msg);
        } catch (Exception e) {
            return msg;
        }
    }

}
