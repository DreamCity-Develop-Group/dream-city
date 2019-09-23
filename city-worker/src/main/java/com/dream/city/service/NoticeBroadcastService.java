package com.dream.city.service;

import java.util.Map;

/**
 * @author Wvv
 */
public interface NoticeBroadcastService {

    boolean pushNoticeBroadcast(String clientId, Map data);
}
