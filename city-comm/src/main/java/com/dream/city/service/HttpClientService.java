package com.dream.city.service;

import com.dream.city.base.model.Message;

/**
 * @author Wvv
 */
public interface HttpClientService {

    void post(Message message);

    void send(Message message);
}
