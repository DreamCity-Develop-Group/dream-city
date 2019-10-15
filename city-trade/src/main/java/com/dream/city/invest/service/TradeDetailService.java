package com.dream.city.invest.service;

import com.dream.city.base.model.entity.TradeDetail;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerTradeResp;

import java.util.List;


public interface TradeDetailService {


    int insert(TradeDetail record);

    TradeDetail getById(Integer id);

    List<PlayerTradeResp> getList(PlayerTradeReq record);


}