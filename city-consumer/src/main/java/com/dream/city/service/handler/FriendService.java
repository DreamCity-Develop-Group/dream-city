package com.dream.city.service.handler;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import org.springframework.web.bind.annotation.RequestBody;

public interface FriendService {
    /**
     * 好友主页
     * @param msg
     * @return
     */
     Message friendHomePage(@RequestBody Message msg)throws BusinessException;

     Message addFriend(@RequestBody Message msg)throws BusinessException;

     Message agreeApply(@RequestBody Message msg)throws BusinessException;

     Message friendList(@RequestBody Message msg)throws BusinessException;

     Message applyFriend(@RequestBody Message msg)throws BusinessException;
}
