package com.dream.city.service.routes;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;

public interface PusherService {

    Result jobPush(Message message) throws BusinessException;

    /**
     * 推送广播消息
     *
     * @param message
     * @return
     */
    Result noticePush(Message message) throws BusinessException;
}
