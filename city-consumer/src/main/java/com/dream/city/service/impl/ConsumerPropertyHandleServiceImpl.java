package com.dream.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.enu.InvestStatus;
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
                ordersCountResult = orderService.countOrdersByPayerIdInvestId(order);
                //物业投资按钮 TODO
                String status = InvestStatus.SUBSCRIBE.getDesc();
                if(ordersCountResult.getData() > 0){
                    //经营中
                    status = InvestStatus.MANAGEMENT.getDesc();
                }

                resultMap.put("username",record.getUsername());
                resultMap.put("inImg",invest.getInImg());
                resultMap.put("inName",invest.getInName());
                resultMap.put("inId",invest.getInId());
                resultMap.put("profit",invest.getInTax());
                resultMap.put("orderAmount",invest.getInLimit());
                resultMap.put("personalInTax",0); //TODO
                resultMap.put("enterpriseIntax",0); //TODO
                resultMap.put("status",status); //TODO
                resultList.add(resultMap);
            }
        }

        return null;
    }


}
