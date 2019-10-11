package com.dream.city.service.impl;

import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RuleItem;
import com.dream.city.base.model.entity.SalesOrder;
import com.dream.city.base.model.mapper.SalesOrderMapper;
import com.dream.city.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesOrdeServiceImpl implements SalesOrderService {
    @Autowired
    SalesOrderMapper salesOrderMapper;

    @Override
    public List<SalesOrder> getSalesOrdersByState(int state) {
        List<SalesOrder> salesOrders = salesOrderMapper.getSalesOrdersByState(state);

        return salesOrders;
    }

    @Override
    public SalesOrder getSalesOrder() {
        return null;
    }
}
