package com.dream.city.property.service;

import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.req.CityInvestReq;
import com.dream.city.base.model.resp.InvestResp;

import java.util.List;

/**
 * 物业/投资项
 */
public interface InvestService {

    /**
     * 新建物业
     * @param record
     * @return
     */
    int insertInvest(CityInvest record);


    /**
     * 查询物业
     * @param record: inId 、inName
     * @return
     */
    InvestResp getInvest(CityInvestReq record);

    /**
     * 更新物业
     * @param record
     * @return
     */
    int updateInvest(CityInvest record);

    /**
     * 物业列表
     * @param record
     * @return
     */
    List<InvestResp> getInvestLsit(CityInvestReq record);





}
