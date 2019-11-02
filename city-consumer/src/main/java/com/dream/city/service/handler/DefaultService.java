package com.dream.city.service.handler;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import org.springframework.web.bind.annotation.RequestBody;

public interface DefaultService {
    Message enterMainPage(@RequestBody Message message) throws BusinessException;

    void setAppVersion(String version);

    String getAppVersion();
}
