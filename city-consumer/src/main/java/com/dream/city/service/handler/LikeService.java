package com.dream.city.service.handler;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;

import java.util.Map;

public interface LikeService {
    /**
     * 当天是否可以点赞
     * 好友
     * @param msg
     * @return
     */
     Message canLikePlayerToday(Message msg)throws BusinessException;


    /**
     * 当天是否可以点赞
     * 投资
     * @param msg
     * @return
     */
     Message canLikeInvestToday(Message msg)throws BusinessException;


    /**
     * 点赞
     * @param msg
     * @return
     */
     Message playerLike(Message msg)throws BusinessException;

    /**
     * 取消点赞
     * @param msg
     * @return
     */
    /*
     Message cancelLike(Message msg)throws BusinessException;*/

    /**
     * 玩家被点赞总数
     * @param msg
     * @return
     */
     Message playerLikesCount(Message msg)throws BusinessException;

    /**
     * 点赞项目
     * @param msg
     * @return
     */
     Message playerLikesList(Message msg)throws BusinessException;




     Map<String, Object> getConditionMap(Message msg)throws BusinessException;
}
