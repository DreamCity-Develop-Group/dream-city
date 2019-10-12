package com.dream.city.base.model.mapper;

import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.entity.TradeDetail;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerTradeResp;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeDetailMapper {

    Integer deleteByPrimaryKey(Integer id);

    Integer insertSelective(TradeDetail record);

    TradeDetail selectByPrimaryKey(Integer id);

    List<PlayerTradeResp> getTradeDetailList(PlayerTradeReq record);

    Integer updateByPrimaryKeySelective(TradeDetail record);

}