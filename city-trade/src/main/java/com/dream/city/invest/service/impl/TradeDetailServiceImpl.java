package com.dream.city.invest.service.impl;

import com.dream.city.base.model.entity.TradeDetail;
import com.dream.city.base.model.mapper.TradeDetailMapper;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerTradeResp;
import com.dream.city.invest.service.TradeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeDetailServiceImpl implements TradeDetailService {


    @Autowired
    TradeDetailMapper detailMapper;




    @Override
    public int insert(TradeDetail record) {
        Integer integer = detailMapper.insertSelective(record);
        return integer == null?0:integer;
    }

    @Override
    public TradeDetail getById(Integer id) {
        return detailMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PlayerTradeResp> getList(PlayerTradeReq record) {
        return detailMapper.getTradeDetailList(record);
    }
}
