package com.dream.city.base.model.mapper;

import com.dream.city.base.model.entity.TradeDetail;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerTradeResp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeDetailMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(TradeDetail record);

    TradeDetail selectByPrimaryKey(Integer id);

    List<PlayerTradeResp> getTradeDetailList(PlayerTradeReq record);

    int updateByPrimaryKeySelective(TradeDetail record);

}