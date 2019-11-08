package com.dream.city.service;

import java.util.Map;

/**
 * @author Wvv
 */
public interface MessageService {

    boolean pushRetry(String clientId, Map data);
    void pushJobMessage(String playerId, String desc);
}
