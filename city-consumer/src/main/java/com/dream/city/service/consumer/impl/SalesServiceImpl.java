package com.dream.city.service.consumer.impl;

import com.dream.city.base.model.Result;
import com.dream.city.service.consumer.ConsumerSalesService;
import org.springframework.stereotype.Component;

/**
 * @author Wvv
 */
@Component
public class SalesServiceImpl implements ConsumerSalesService {

    @Override
    public Result getSalesNum(String playerId) {
        return null;
    }

    @Override
    public Result getSalesNumOverTime(String playerId) {
        return null;
    }

    @Override
    public Result getUsdtToMtRate(String playerId) {
        return null;
    }
}
