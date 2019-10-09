package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerTradeResp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 交易流水
 */
@Mapper
public interface PlayerTradeMapper {

    Integer insertSelective(PlayerTrade record);

    PlayerTradeResp getPlayerTradeById(Integer tradeId);

    PlayerTrade getPlayerTrade(PlayerTrade record);

    List<PlayerTradeResp> getPlayerTradeList(PlayerTradeReq record);

    Integer updateByPrimaryKeySelective(PlayerTrade record);

}