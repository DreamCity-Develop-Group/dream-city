package com.dream.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.req.CityInvestReq;
import com.dream.city.service.ConsumerOrderService;
import com.dream.city.service.ConsumerPropertyHandleService;
import com.dream.city.service.ConsumerPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
            Date currentTime = new Date();
            for (CityInvest invest:list){
                resultMap = JSON.parseObject(JSON.toJSONString(invest),Map.class);
                order = new InvestOrder();
                order.setOrderInvestId(invest.getInId());
                order.setOrderPayerId(record.getPayerId());
                String couldInvest = "可投资";
                ordersCountResult = orderService.countOrdersByPayerIdInvestId(order);
                if(ordersCountResult.getData() > 0){
                    couldInvest = "已投资";
                }
                if (invest.getInStart().after(currentTime) || invest.getInEnd().after(currentTime)){
                    couldInvest = "不可投";
                }

                resultMap.put("username",record.getUsername());
                resultMap.put("couldInvest",couldInvest);
                resultMap.put("personalInTax",0); // TODO
                resultMap.put("enterpriseIntax",0); // TODO
                resultList.add(resultMap);
            }
        }

        return null;
    }


}
