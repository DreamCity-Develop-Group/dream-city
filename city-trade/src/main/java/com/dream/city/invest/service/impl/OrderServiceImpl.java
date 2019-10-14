package com.dream.city.invest.service.impl;

import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.enu.InvestStatus;
import com.dream.city.base.model.mapper.InvestOrderMapper;
import com.dream.city.base.model.req.InvestOrderReq;
import com.dream.city.base.model.resp.InvestOrderResp;
import com.dream.city.invest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private InvestOrderMapper orderMapper;



    @Override
    public InvestOrder insertInvestOrder(InvestOrder record) {
        Integer integer = orderMapper.insertSelective(record);
        return integer==null?null:record;
    }

    @Override
    public int playerInvesting(Integer orderId) {
        InvestOrder record = new InvestOrder();
        record.setOrderId(orderId);
        record.setOrderState(InvestStatus.MANAGEMENT.name());
        Integer integer = orderMapper.updateByPrimaryKeySelective(record);
        return integer ==null?0:integer;
    }

    @Override
    public int investOrderInvalid(InvestOrder record) {
        record.setOrderState(InvestStatus.INVALID.name());
        Integer integer = orderMapper.updateByPrimaryKeySelective(record);
        return integer ==null?0:integer;
    }

    @Override
    public int investOrderCancel(InvestOrder record) {
        record.setOrderState(InvestStatus.CANCEL.name());
        Integer integer = orderMapper.updateByPrimaryKeySelective(record);
        return integer ==null?0:integer;
    }

    @Override
    public InvestOrderResp getInvestOrderById(InvestOrder record) {
        if (record.getOrderId() == null){
            return null;
        }
        return orderMapper.selectByPrimaryKey(record.getOrderId());
    }

    @Override
    public List<InvestOrderResp> getInvestOrderList(InvestOrderReq record) {
        return orderMapper.getInvestOrders(record);
    }

    @Override
    public int countOrdersByPayerIdInvestId(InvestOrder record) {
        return orderMapper.countOrdersByPayerIdInvestId(record);
    }


}
