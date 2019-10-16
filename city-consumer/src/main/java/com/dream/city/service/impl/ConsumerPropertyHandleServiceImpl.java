package com.dream.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.enu.InvestStatus;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.req.CityInvestReq;
import com.dream.city.base.model.resp.InvestResp;
import com.dream.city.service.ConsumerPropertyHandleService;
import com.dream.city.service.ConsumerPropertyService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ConsumerPropertyHandleServiceImpl implements ConsumerPropertyHandleService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerPropertyService propertyService;

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
        String desc = "获取物业列表失败";
        boolean success = Boolean.FALSE;
        List<Map<String,Object>> resultList = new ArrayList<>();
        try {
            Result<List<InvestResp>> investLsitResult = propertyService.getInvestLsit(record);
            if (investLsitResult.getSuccess()){
                List<InvestResp> list = investLsitResult.getData();
                Map<String,Object> resultMap = null;
                for (InvestResp invest:list){
                    //resultMap = JSON.parseObject(JSON.toJSONString(invest),Map.class);
                    resultMap = new HashMap<>();
                    //物业投资按钮
                    int status = ReturnStatus.ERROR.getStatus();
                    if (StringUtils.isBlank(invest.getOrderState())){
                        //预约
                        status = ReturnStatus.INVEST_SUBSCRIBE.getStatus();
                    }else {
                        if(InvestStatus.SUBSCRIBE.name().equalsIgnoreCase(invest.getOrderState())){
                            //预约
                            status = ReturnStatus.INVEST_SUBSCRIBE.getStatus();
                        }else if(InvestStatus.SUBSCRIBED.name().equalsIgnoreCase(invest.getOrderState())){
                            //已预约
                            status = ReturnStatus.INVEST_SUBSCRIBED.getStatus();
                        }else if(InvestStatus.MANAGEMENT.name().equalsIgnoreCase(invest.getOrderState())){
                            //经营中
                            status = ReturnStatus.INVEST_MANAGEMENT.getStatus();
                        }
                        if(InvestStatus.EXTRACT.name().equalsIgnoreCase(invest.getOrderState())){
                            //可提取
                            status = ReturnStatus.INVEST_EXTRACT.getStatus();
                        }
                    }
                    if(invest.getIsWithdrew() != null && invest.getIsWithdrew() == 2){
                        //可提取
                        status = ReturnStatus.INVEST_EXTRACT.getStatus();
                    }
                    if (invest.getEarnCurrent() == null){
                        invest.setEarnCurrent(BigDecimal.ZERO);
                    }
                    BigDecimal extract = invest.getEarnCurrent().setScale( 0, BigDecimal.ROUND_DOWN );
                    BigDecimal incomeLeft = invest.getEarnCurrent().subtract(extract);
                    resultMap.put("investId",invest.getInId());
                    resultMap.put("investMoney",invest.getInLimit());
                    resultMap.put("extractable",invest.getEarnCurrent());
                    resultMap.put("extract",extract);
                    resultMap.put("incomeLeft",incomeLeft);
                    resultMap.put("personTax",invest.getPersonalInTax());
                    resultMap.put("enterpriseTax",invest.getEnterpriseIntax());
                    resultMap.put("quotaTax",invest.getInQuotaTax());
                    resultMap.put("state", status);
                    resultMap.put("inType", invest.getInType());
                    resultMap.put("openState", invest.getIsValid());


                    resultList.add(resultMap);
                }
                success = Boolean.TRUE;
                desc = "获取物业列表成功";
            }
        }catch (Exception e){
            logger.error("获取物业列表异常",e);
        }
        return new Result<>(success,desc,resultList);
    }


}
