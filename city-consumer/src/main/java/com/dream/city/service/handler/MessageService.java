package com.dream.city.service.handler;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;

public interface MessageService {
    Message checkCode(String code, String account) throws BusinessException;
    Message getCode(Message msg) throws BusinessException;
}
