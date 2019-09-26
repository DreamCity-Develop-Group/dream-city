package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.req.CityInvestReq;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * 物业/投资项
 */
public interface ConsumerPropertyHandleService {

    /**
     * 新建物业
     * @param record
     * @return
     */
    Result<Integer> insertProperty(@RequestBody CityInvest record);


    /**
     * 查询物业
     * @param record
     * @return
     */
    Result<CityInvest> getPropertyByIdOrName(@RequestBody CityInvest record);

    /**
     * 更新物业
     * @param record
     * @return
     */
    Result<Integer> updateProperty(@RequestBody CityInvest record);

    /**
     * 物业列表
     * @param record
     * @return
     */
    Result<List<Map<String,Object>>> getPropertyLsit(@RequestBody CityInvestReq record);





}
