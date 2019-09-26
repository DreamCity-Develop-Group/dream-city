package com.dream.city.service.impl;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.req.CityInvestReq;
import com.dream.city.service.ConsumerOrderService;
import com.dream.city.service.ConsumerPropertyHandleService;
import com.dream.city.service.ConsumerPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConsumerPropertyHandleServiceImpl implements ConsumerPropertyHandleService {

    @Autowired
    private ConsumerPropertyService propertyService;
    @Autowired
    private ConsumerOrderService orderService;

    @Override
    public Result<Integer> insertProperty(CityInvest record) {
        return propertyService.insertInvest(record);
    }

    @Override
    public Result<CityInvest> getPropertyByIdOrName(CityInvest record) {
        return propertyService.getInvestByIdOrName(record);
    }

    @Override
    public Result<Integer> updateProperty(CityInvest record) {
        return propertyService.updateInvest(record);
    }

    @Override
    public Result<List<Map<String,Object>>> getPropertyLsit(CityInvestReq record) {
        CityInvest investReq = new CityInvest();
        investReq.setIsValid("Y");
        Result<List<CityInvest>> investLsitResult = propertyService.getInvestLsit(investReq);
        List<Map<String,Object>> resultList = new ArrayList<>();
        if (investLsitResult.getSuccess()){
            List<CityInvest> list = investLsitResult.getData();
            Map<String,Object> resultMap = null;
            Result<Integer> ordersCountResult = null;
            InvestOrder order = null;
            for (CityInvest invest:list){
                resultMap = new HashMap<>();
                order = new InvestOrder();
                order.setOrderInvestId(invest.getInId());
                order.setOrderPayerId(record.getPayerId());
                ordersCountResult = orderService.countOrdersByPayerIdInvestId(order);

                // TODO
                /*resultMap.put("username",);
                resultMap.put("username",);
                resultMap.put("username",);
                resultMap.put("username",);*/
                resultList.add(resultMap);
            }
        }

        return null;
    }


}
