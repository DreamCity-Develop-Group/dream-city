package com.dream.city.invest.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.enu.InvestStatus;
import com.dream.city.base.model.mapper.InvestOrderMapper;
import com.dream.city.base.model.req.InvestOrderReq;
import com.dream.city.base.model.resp.InvestOrderResp;
import com.dream.city.invest.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private InvestOrderMapper orderMapper;




    @LcnTransaction
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public InvestOrder insertInvestOrder(InvestOrder record)  throws BusinessException {
        Integer integer = orderMapper.insertSelective(record);
        return integer==null?null:record;
    }

    @LcnTransaction
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int playerInvesting(Integer orderId) throws BusinessException {
        InvestOrder record = new InvestOrder();
        record.setOrderId(orderId);
        record.setOrderState(InvestStatus.MANAGEMENT.name());
        Integer integer = orderMapper.updateByPrimaryKeySelective(record);
        return (integer ==null || integer == 0)?0:integer;
    }

    @LcnTransaction
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int investOrderInvalid(InvestOrder record)  throws BusinessException{
        record.setOrderState(InvestStatus.INVALID.name());
        Integer integer = orderMapper.updateByPrimaryKeySelective(record);
        return integer ==null?0:integer;
    }

    @LcnTransaction
    @Transactional
    @Override
    public int investOrderCancel(InvestOrder record) throws BusinessException {
        record.setOrderState(InvestStatus.CANCEL.name());
        Integer integer = orderMapper.updateByPrimaryKeySelective(record);
        return integer ==null?0:integer;
    }

    @LcnTransaction
    @Transactional
    @Override
    public InvestOrderResp getInvestOrderById(Integer orderId)  throws BusinessException{
        if (orderId == null){
            return null;
        }
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public InvestOrder getOrderByPlayerIdInvestId(String playerId, Integer investId)  throws BusinessException{
        InvestOrder record = new InvestOrder();
        record.setOrderPayerId(playerId);
        record.setOrderInvestId(investId);
        return orderMapper.getOrderByPlayerIdInvestId(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<InvestOrderResp> getInvestOrderList(InvestOrderReq record) throws BusinessException {
        return orderMapper.getInvestOrders(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public int countOrdersByPayerIdInvestId(InvestOrder record) throws BusinessException {
        return orderMapper.countOrdersByPayerIdInvestId(record);
    }


}
