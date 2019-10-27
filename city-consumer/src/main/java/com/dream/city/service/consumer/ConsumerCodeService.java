package com.dream.city.service.consumer;

import com.dream.city.base.model.Result;

/**
 * @author Wvv
 */
public interface ConsumerCodeService {

    Result checkCode(String code, String phone);
}
