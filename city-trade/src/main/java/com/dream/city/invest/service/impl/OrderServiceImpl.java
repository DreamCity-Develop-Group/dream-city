package com.dream.city.invest.service.impl;

import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.enu.OrderState;
import com.dream.city.invest.domain.mapper.InvestOrderMapper;
import com.dream.city.invest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private InvestOrderMapper orderMapper;



    @Override
    @Transactional
    public InvestOrder insertInvestOrder(InvestOrder record) {
        return orderMapper.insertSelective(record);
    }

    @Override
    @Transactional
    public int investOrderInvalid(InvestOrder record) {
        record.setOrderState(OrderState.INVALID.name());
        Integer integer = orderMapper.updateByPrimaryKeySelective(record);
        return integer ==null?0:integer;
    }

    @Override
    @Transactional
    public int investOrderCancel(InvestOrder record) {
        record.setOrderState(OrderState.CANCEL.name());
        Integer integer = orderMapper.updateByPrimaryKeySelective(record);
        return integer ==null?0:integer;
    }

    @Override
    public InvestOrder getInvestOrderById(InvestOrder record) {
        if (record.getOrderId() == null){
            return null;
        }
        return orderMapper.selectByPrimaryKey(record);
    }

    @Override
    public List<InvestOrder> getInvestOrderList(InvestOrder record) {
        return orderMapper.getInvestOrders(record);
    }

    @Override
    public int countOrdersByPayerIdInvestId(InvestOrder record) {
        return orderMapper.countOrdersByPayerIdInvestId(record);
    }


}
