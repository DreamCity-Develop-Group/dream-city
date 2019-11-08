package com.dream.city.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.SalesOrder;
import com.dream.city.base.model.mapper.SalesOrderMapper;
import com.dream.city.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalesOrdeServiceImpl implements SalesOrderService {
    @Autowired
    private SalesOrderMapper salesOrderMapper;

    @LcnTransaction
    @Transactional
    @Override
    public List<SalesOrder> getSalesOrdersByState(int state) throws BusinessException {
        List<SalesOrder> salesOrders = salesOrderMapper.getSalesOrdersByState(state);

        return salesOrders;
    }

    @LcnTransaction
    @Transactional
    @Override
    public SalesOrder getSalesOrder() throws BusinessException {
        return null;
    }

    @LcnTransaction
    @Transactional
    @Override
    public int selectSalesSellerRejectTimes(String buyer_id, String sellerId, int status) {

        return salesOrderMapper.selectSalesSellerRejectTimes(buyer_id, sellerId, status);

    }
}
