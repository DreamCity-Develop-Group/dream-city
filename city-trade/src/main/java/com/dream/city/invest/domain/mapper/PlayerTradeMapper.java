package com.dream.city.invest.domain.mapper;


import com.dream.city.base.model.entity.PlayerTrade;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlayerTradeMapper {

    Integer insertSelective(PlayerTrade record);

    PlayerTrade getPlayerTrade(Integer tradeId);

    List<PlayerTrade> getPlayerTradeList(PlayerTrade record);

    Integer updateByPrimaryKeySelective(PlayerTrade record);

}