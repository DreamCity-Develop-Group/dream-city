package com.dream.city.invest.service.impl;

import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.enu.OrderState;
import com.dream.city.invest.domain.mapper.InvestOrderMapper;
import com.dream.city.invest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private InvestOrderMapper orderMapper;



    @Override
    public int insertInvestOrder(InvestOrder record) {
        Integer integer = orderMapper.updateByPrimaryKeySelective(record);
        return integer ==null?0:integer;
    }

    @Override
    public int investOrderInvalid(InvestOrder record) {
        record.setOrderState(OrderState.INVALID.getStatus());
        Integer integer = orderMapper.updateByPrimaryKeySelective(record);
        return integer ==null?0:integer;
    }

    @Override
    public int investOrderCancel(InvestOrder record) {
        record.setOrderState(OrderState.CANCEL.getStatus());
        Integer integer = orderMapper.updateByPrimaryKeySelective(record);
        return integer ==null?0:integer;
    }

    @Override
    public InvestOrder getInvestOrder(InvestOrder record) {
        return orderMapper.selectByPrimaryKey(record);
    }

    @Override
    public List<InvestOrder> getInvestOrders(InvestOrder record) {
        return orderMapper.getInvestOrders(record);
    }


}
