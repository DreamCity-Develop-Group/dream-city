package com.dream.city.service.handler.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.enu.InvestStatus;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.req.CityInvestReq;
import com.dream.city.base.model.resp.InvestResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.utils.DateUtils;
import com.dream.city.service.consumer.ConsumerInvestService;
import com.dream.city.service.consumer.ConsumerPropertyService;
import com.dream.city.service.handler.PropertyService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 * @program: dream-city
 * @File: PropertyServiceImpl
 * @description: 资产服务类
 * @create: 2019/10/2019/10/27 23:15:33 [星期日]
 **/

@Service
public class PropertyServiceImpl implements PropertyService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerPropertyService consumerPropertyService;
    @Autowired
    ConsumerInvestService investService;
    @Autowired
    PropertyService propertyService;


    @LcnTransaction
    @Transactional
    @Override
    public Result<Integer> insertProperty(CityInvest record) {
        return investService.insertInvest(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result<InvestResp> getProperty(CityInvestReq record) {
        return investService.getInvest(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result<Integer> updateProperty(CityInvest record) {
        return investService.updateInvest(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result<List<Map<String,Object>>> getPropertyLsit(CityInvestReq record) {
        String desc = "获取物业列表失败";
        boolean success = Boolean.FALSE;
        List<Map<String,Object>> resultList = new ArrayList<>();
        try {
            Result<List<InvestResp>> investLsitResult = investService.getInvestLsit(record);
            if (investLsitResult.getSuccess()){
                List<InvestResp> list = investLsitResult.getData();
                Map<String,Object> resultMap = null;
                for (InvestResp invest:list){
                    //resultMap = JSON.parseObject(JSON.toJSONString(invest),Map.class);
                    resultMap = new HashMap<>();
                    //物业投资按钮
                    int status = ReturnStatus.INVEST_SUBSCRIBE.getStatus();
                    logger.info("物业状态",invest.getOrderState());
                    if (StringUtils.isBlank(invest.getOrderState())){
                        //预约
                        status = ReturnStatus.INVEST_SUBSCRIBE.getStatus();
                    }else {
                        String string= InvestStatus.SUBSCRIBE.name();
                        int code = InvestStatus.SUBSCRIBE.getStatus();
                        int state = Integer.parseInt(invest.getOrderState());

                        if (state == InvestStatus.SUBSCRIBE.getStatus()){
                            //预约
                            status = ReturnStatus.INVEST_SUBSCRIBE.getStatus();
                        }else if(state == InvestStatus.SUBSCRIBED.getStatus()){
                            //已预约
                            status = ReturnStatus.INVEST_SUBSCRIBED.getStatus();
                        }else if (state == InvestStatus.MANAGEMENT.getStatus()){
                            //经营中
                            status = ReturnStatus.INVEST_MANAGEMENT.getStatus();
                        }else if(state == InvestStatus.EXTRACT.getStatus()){
                            //可提取
                            status = ReturnStatus.INVEST_EXTRACT.getStatus();
                        }else {
                            //预约
                            status = ReturnStatus.INVEST_SUBSCRIBE.getStatus();
                        }

                        /*if(InvestStatus.SUBSCRIBE.name().equalsIgnoreCase(invest.getOrderState())){
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
                        }*/
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
                    resultMap.put("openState", invest.getIsValid());
                    resultMap.put("inType", invest.getInType());
                    resultMap.put("expectIncome", invest.getInLimit()
                            .multiply(BigDecimal.valueOf(Long.parseLong(String.valueOf(invest.getInEarning())))));
                    resultMap.put("likeCount",0);
                    String resultTime = "9:30";
                    if (invest.getVerifyTime() != null){
                        resultTime = DateUtils.date2Str(invest.getVerifyTime(),"HH:mm");
                    }
                    resultMap.put("resultTime",resultTime);

                    resultList.add(resultMap);
                }
                success = Boolean.TRUE;
                desc = "获取物业列表成功";
            }else {
                desc = "获取物业列表为空";
            }
        }catch (Exception e){
            logger.error("获取物业列表异常",e);
        }
        return new Result<>(success,desc,resultList);
    }
    /**
     * 查询物业
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message getProperty(Message msg)throws BusinessException{
        logger.info("查询物业", JSONObject.toJSONString(msg));

        CityInvestReq investReq = DataUtils.getInvestFromMessage(msg);
        Result result = consumerPropertyService.getProperty(investReq);
        msg.getData().setData(JSON.toJSONString(result.getData()));
        msg.setDesc(result.getMsg());
        return msg;
    }

    /**
     * 物业列表
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message getPropertyLsit(Message msg)throws BusinessException {
        logger.info("物业列表", JSONObject.toJSONString(msg));

        CityInvestReq invest = DataUtils.getInvestFromMessage(msg);
        Result result = getPropertyLsit(invest);
        msg.getData().setData(JSON.toJSONString(result.getData()));
        msg.setDesc(result.getMsg());
        return msg;
    }

    /**
     * 新建物业
     * @param msg
     * @return
     */
    /*
    @LcnTransaction
    @Transactional
    @Override
    public Message insertInvest(Message msg)throws BusinessException{
        logger.info("用户取消下单", JSONObject.toJSONString(msg));



        msg.getData().setData(JSON.toJSONString(""));
        msg.setDesc("");
        return msg;
    }*/



    /**
     * 更新物业
     * @param msg
     * @return
     */
    /*
    @LcnTransaction
    @Transactional
    @Override
    public Message updateInvest(Message msg)throws BusinessException{
        logger.info("用户取消下单", JSONObject.toJSONString(msg));



        msg.getData().setData(JSON.toJSONString(""));
        msg.setDesc("");
        return msg;
    }*/
}
