package com.dream.city.service.handler;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;

public interface GameService {
    Object voice(Message msg) throws BusinessException;
}
