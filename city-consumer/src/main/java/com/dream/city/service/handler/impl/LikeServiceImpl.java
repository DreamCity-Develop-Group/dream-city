package com.dream.city.service.handler.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.service.consumer.ConsumerLikesService;
import com.dream.city.service.consumer.ConsumerPlayerService;
import com.dream.city.service.handler.LikeService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Wvv
 * @program: dream-city
 * @File: LikeServiceImpl
 * @description: 点赞服务类
 * @create: 2019/10/2019/10/27 22:58:01 [星期日]
 **/
@Service
public class LikeServiceImpl implements LikeService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConsumerLikesService likesService;
    @Autowired
    ConsumerPlayerService consumerPlayerService;



    /**
     * 当天是否可以点赞
     * 好友
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message canLikePlayerToday(Message msg){
        logger.info("点赞", JSONObject.toJSONString(msg));
        Message message = new Message(msg.getSource(), msg.getTarget(),
                new MessageData<Boolean>(msg.getData().getType(),msg.getData().getModel()));

        Map<String, Object> conditionMap = getConditionMap(msg);
        Result<Integer> result = likesService.canPlayerCanLikesToday(JSON.toJSONString(conditionMap));
        if (result.getSuccess() && result.getData() > 0){
            message.getData().setData(Boolean.FALSE);
        }else {
            message.getData().setData(Boolean.TRUE);
        }
        message.setDesc(result.getMsg());
        return message;
    }


    /**
     * 当天是否可以点赞
     * 投资
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message canLikeInvestToday(Message msg){
        logger.info("点赞", JSONObject.toJSONString(msg));
        Message message = new Message(msg.getSource(), msg.getTarget(),
                new MessageData<Boolean>(msg.getData().getType(),msg.getData().getModel()));

        Map<String, Object> conditionMap = getConditionMap(msg);
        Result<Integer> result = likesService.canInvestLikesToday(JSON.toJSONString(conditionMap));
        if (result.getSuccess() && result.getData() > 0){
            message.getData().setData(Boolean.FALSE);
        }else {
            message.getData().setData(Boolean.TRUE);
        }
        message.setDesc(result.getMsg());
        return message;
    }


    /**
     * 点赞
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message playerLike(Message msg){
        logger.info("点赞", JSONObject.toJSONString(msg));
        if (Objects.isNull(msg) || Objects.isNull(msg.getData().getData())){
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            msg.setDesc("消息不正确");
            return msg;
        }
        String json = JsonUtil.parseObjToJson(msg.getData().getData());
        JSONObject data = JsonUtil.parseJsonToObj(json,JSONObject.class);

        String from = data.getString("playerId");
        String to = data.getString("friendId");


        Result result = likesService.playerLike(from,to);
        if (result.getSuccess()){
            msg.getData().setCode(ReturnStatus.SUCCESS.getStatus());
            msg.setDesc("点赞成功");
            return msg;
        }
        msg.setDesc("点赞失败");
        msg.getData().setData(null);
        return msg;
    }

    /**
     * 取消点赞
     * @param msg
     * @return
     */
    /*
    @LcnTransaction
    @Transactional
    @Override
    public Message cancelLike(Message msg){
        logger.info("取消点赞，{}",msg);
        Message message = new Message(msg.getSource(), msg.getTarget(),
                new MessageData<Integer>(msg.getData().getType(),msg.getData().getModel()));

        Map<String, Object> conditionMap = getConditionMap(msg);
        Result<Integer> result = likesService.cancelLike(JSON.toJSONString(conditionMap));
        if (result.getSuccess()){
            message.getData().setData(result.getData());
        }
        message.setDesc(result.getMsg());
        return message;
    }*/

    /**
     * 玩家被点赞总数
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message playerLikesCount(Message msg){
        logger.info("获取玩家点赞总数，{}",msg);
        Message message = new Message(msg.getSource(), msg.getTarget(),
                new MessageData<Integer>(msg.getData().getType(),msg.getData().getModel()));

        Map<String, Object> conditionMap = getConditionMap(msg);
        Result<Integer> result = likesService.playerLikesCount(JSON.toJSONString(conditionMap));
        if (result.getSuccess()){
            message.getData().setData(result.getData());
        }
        message.setDesc(result.getMsg());
        return message;
    }

    /**
     * 点赞项目
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message playerLikesList(Message msg) {
        logger.info("获取点赞项目，{}", msg);
        Message message = new Message(msg.getSource(), msg.getTarget(),
                new MessageData<String>(msg.getData().getType(),msg.getData().getModel()));

        Map<String, Object> conditionMap = getConditionMap(msg);
        Result<String> result = likesService.playerLikesList(JSON.toJSONString(conditionMap));
        if (result.getSuccess()){
            message.getData().setData(result.getData());
        }
        message.setDesc(result.getMsg());
        return message;
    }



    @LcnTransaction
    @Transactional
    @Override
    public Map<String, Object> getConditionMap(Message msg){
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
