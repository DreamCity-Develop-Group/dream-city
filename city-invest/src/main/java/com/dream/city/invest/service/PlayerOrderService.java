package com.dream.city.invest.service;

import com.dream.city.base.model.req.PlayerOrderReq;
import com.dream.city.base.model.resp.PlayerOrderResp;

/**
 * 玩家订单
 */
public interface PlayerOrderService {


    /**
     * 玩家下单
     * @param orderReq
     * @return
     */
    PlayerOrderResp playerPlaceOrder(PlayerOrderReq orderReq);












}
