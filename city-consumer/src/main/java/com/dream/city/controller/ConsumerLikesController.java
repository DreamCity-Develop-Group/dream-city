package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.service.ConsumerLikesService;
import com.dream.city.service.ConsumerPlayerService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * 点赞
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerLikesController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConsumerLikesService likesService;
    @Autowired
    ConsumerPlayerService consumerPlayerService;


    /**
     * 点赞
     * @param msg
     * @return
     */
    @RequestMapping("/likefriend")
    public Message playerLike(@RequestBody Message msg){
        logger.info("点赞", JSONObject.toJSONString(msg));
        Message message = new Message(msg.getSource(), msg.getTarget(),
                new MessageData<Integer>(msg.getData().getType(),msg.getData().getModel()));

        Map<String, Object> conditionMap = getConditionMap(msg);
        Result<Integer> result = likesService.playerLike(JSON.toJSONString(conditionMap));
        if (result.getSuccess()){
            message.getData().setT(result.getData());
        }
        message.setDesc(result.getMsg());
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
        Message message = new Message(msg.getSource(), msg.getTarget(),
                new MessageData<Integer>(msg.getData().getType(),msg.getData().getModel()));

        Map<String, Object> conditionMap = getConditionMap(msg);
        Result<Integer> result = likesService.cancelLike(JSON.toJSONString(conditionMap));
        if (result.getSuccess()){
            message.getData().setT(result.getData());
        }
        message.setDesc(result.getMsg());
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
        Message message = new Message(msg.getSource(), msg.getTarget(),
                new MessageData<Integer>(msg.getData().getType(),msg.getData().getModel()));

        Map<String, Object> conditionMap = getConditionMap(msg);
        Result<Integer> result = likesService.playerLikesCount(JSON.toJSONString(conditionMap));
        if (result.getSuccess()){
            message.getData().setT(result.getData());
        }
        message.setDesc(result.getMsg());
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
        Message message = new Message(msg.getSource(), msg.getTarget(),
                new MessageData<String>(msg.getData().getType(),msg.getData().getModel()));

        Map<String, Object> conditionMap = getConditionMap(msg);
        Result<String> result = likesService.playerLikesList(JSON.toJSONString(conditionMap));
        if (result.getSuccess()){
            message.getData().setT(result.getData());
        }
        message.setDesc(result.getMsg());
        return message;
    }




    private Map<String, Object> getConditionMap(Message msg){
        Map<String, String> conditionAllMap = DataUtils.getCondition(msg);
        Integer like = conditionAllMap.containsKey("likes")?Integer.parseInt(conditionAllMap.get("likes")):0;
        String username = conditionAllMap.get("username");
        String nick = conditionAllMap.get("nick");
        String likedIdStr = conditionAllMap.containsKey("likedId")?conditionAllMap.get("likedId"):null;
        Integer likedId = StringUtils.isBlank(likedIdStr)?null:Integer.parseInt(likedIdStr);

        Map<String, Object> usernameConditionMap = new HashMap<>();
        usernameConditionMap.put("playerName",username);
        Result<String> playerNameResult = consumerPlayerService.getPlayerByName(JSON.toJSONString(usernameConditionMap));
        Map playerMap = JSON.parseObject(playerNameResult.getData(),Map.class);
        String playerId = (String)playerMap.get("playerId");

        Map<String, Object> nickConditionMap = new HashMap<>();
        nickConditionMap.put("playerNick",nick);
        Result<String> nickResult = consumerPlayerService.getPlayerByName(JSON.toJSONString(nickConditionMap));
        Map friendMap = JSON.parseObject(nickResult.getData(),Map.class);
        String friendId = (String)friendMap.get("playerId");

        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("likedInvestTotal",like);
        //收获玩家
        conditionMap.put("likedPlayerId",playerId);
        //点赞玩家ID
        conditionMap.put("likePlayerId",friendId);
        conditionMap.put("likedId",likedId);

        return conditionMap;
    }



}
