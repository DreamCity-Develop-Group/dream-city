package com.dream.city.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.SalesOrder;
import com.dream.city.base.model.mapper.SalesOrderMapper;
import com.dream.city.base.model.mapper.SalesRefuseOrderMapper;
import com.dream.city.service.SalesOrderService;
import com.dream.city.service.SalesRefuseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalesRefuseOrderServiceImpl implements SalesRefuseOrderService {
    @Autowired
    private SalesRefuseOrderMapper salesRefuseOrderMapper;

    @Override
    public Integer selectSalesBuyerRefuseOrders(String buyId, String sellId) {
        return salesRefuseOrderMapper.selectSalesBuyerRefuseOrders(buyId,sellId);
    }
}
