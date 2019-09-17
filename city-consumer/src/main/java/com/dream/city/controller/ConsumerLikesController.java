package com.dream.city.controller;

import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.service.ConsumerLikesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 点赞
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerLikesController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConsumerLikesService likesService;



    /**
     * 点赞
     * @param msg
     * @return
     */
    @RequestMapping("/likefriend")
    public Message playerLike(@RequestBody Message msg){
        logger.info("添加好友", JSONObject.toJSONString(msg));
        Message message = new Message();



        return message;
    }

    /**
     * 取消点赞
     * @param msg
     * @return
     */
    @RequestMapping("/cancelLike")
    public Message cancelLike(@RequestBody Message msg){
        logger.info("取消点赞，{}",msg);
        Message message = new Message();



        return message;
    }

    /**
     * 玩家点赞总数
     * @param msg
     * @return
     */
    @RequestMapping("/likesCount")
    public Message playerLikesCount(@RequestBody Message msg){
        logger.info("获取玩家点赞总数，{}",msg);
        Message message = new Message();



        return message;
    }

    /**
     * 点赞项目
     * @param msg
     * @return
     */
    @RequestMapping("/likesList")
    public Message playerLikesList(@RequestBody Message msg) {
        logger.info("获取点赞项目，{}", msg);
        Message message = new Message();


        return message;
    }





}
