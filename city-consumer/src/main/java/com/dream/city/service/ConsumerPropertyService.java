package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.req.CityInvestReq;
import com.dream.city.base.model.resp.InvestResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 物业/投资项
 */
@FeignClient(value = "city-property")
public interface ConsumerPropertyService {

    /**
     * 新建物业
     * @param invest
     * @return
     */
    @RequestMapping("/property/insertInvest")
    Result<Integer> insertInvest(@RequestBody CityInvest invest);


    /**
     * 查询物业
     * @param invest
     * @return
     */
    @RequestMapping("/property/getInvest")
    Result<InvestResp> getInvest(@RequestBody CityInvestReq invest);

    /**
     * 更新物业
     * @param invest
     * @return
     */
    @RequestMapping("/property/updateInvest")
    Result<Integer> updateInvest(@RequestBody CityInvest invest);

    /**
     * 物业列表
     * @param invest
     * @return
     */
    @RequestMapping("/property/getInvestLsit")
    Result<List<InvestResp>> getInvestLsit(@RequestBody CityInvestReq invest);





}
