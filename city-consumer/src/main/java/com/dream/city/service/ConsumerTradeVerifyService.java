package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.TradeVerify;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 交易审核
 */
@FeignClient(value = "city-trade")
@RequestMapping("/trade")
public interface ConsumerTradeVerifyService {

    /**
     * 新增审核
     * @param record
     * @return
     */
    @RequestMapping("/insertTradeVerify")
    Result<TradeVerify> insertTradeVerify(@RequestBody TradeVerify record);

    /**
     * 审核
     * @param record
     * @return
     */
    @RequestMapping("/updateTradeVerify")
    Result updateTradeVerify(@RequestBody TradeVerify record);

    /**
     * 根据id获取审核
     * @param verifyId
     * @return
     */
    @RequestMapping("/getTradeVerifyBiId/{verifyId}")
    Result getTradeVerifyBiId(@PathVariable Integer verifyId);

    /**
     * 审核列表
     * @param record
     * @return
     */
    @RequestMapping("/getTradeVerifyList")
    Result getTradeVerifyList(@RequestBody TradeVerify record);

}
