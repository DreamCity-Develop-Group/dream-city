package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityInvest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 物业/投资项
 */
@FeignClient(value = "city-property")
@RequestMapping("/property")
public interface ConsumerPropertyService {

    /**
     * 新建物业
     * @param record
     * @return
     */
    @RequestMapping("/insertInvest")
    Result<Integer> insertInvest(@RequestBody CityInvest record);


    /**
     * 查询物业
     * @param record
     * @return
     */
    @RequestMapping("/getInvestByIdOrName")
    Result<CityInvest> getInvestByIdOrName(@RequestBody CityInvest record);

    /**
     * 更新物业
     * @param record
     * @return
     */
    @RequestMapping("/updateInvest")
    Result<Integer> updateInvest(@RequestBody CityInvest record);

    /**
     * 物业列表
     * @param record
     * @return
     */
    @RequestMapping("/getInvestLsit")
    Result<List<CityInvest>> getInvestLsit(@RequestBody CityInvest record);





}
