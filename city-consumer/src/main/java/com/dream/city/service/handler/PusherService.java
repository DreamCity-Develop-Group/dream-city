package com.dream.city.service.handler;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;

public interface PusherService {

    void receive(Player player, int type) throws BusinessException;

    Result jobPush(Message message) throws BusinessException;

    /**
     * 推送广播消息
     *
     * @param message
     * @return
     */
    Result noticePush(Message message) throws BusinessException;
}
