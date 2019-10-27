package com.dream.city.service.handler;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.req.CityInvestReq;
import com.dream.city.base.model.resp.InvestResp;

import java.util.List;
import java.util.Map;

public interface PropertyService {

    Result<Integer> insertProperty(CityInvest record) throws BusinessException;

    Result<InvestResp> getProperty(CityInvestReq record) throws BusinessException;


    Result<Integer> updateProperty(CityInvest record) throws BusinessException;

    /**
     * 查询物业
     *
     * @param msg
     * @return
     */
    Message getProperty(Message msg) throws BusinessException;

    /**
     * 物业列表
     *
     * @param msg
     * @return
     */
    Message getPropertyLsit(Message msg) throws BusinessException;

    Result<List<Map<String, Object>>> getPropertyLsit(CityInvestReq record) throws BusinessException;

    /**
     * 新建物业
     * @param msg
     * @return
     */
    /*
    public Message insertInvest(Message msg)throws BusinessException;*/


    /**
     * 更新物业
     * @param msg
     * @return
     */
    /*
    public Message updateInvest(Message msg)throws BusinessException;*/
}
