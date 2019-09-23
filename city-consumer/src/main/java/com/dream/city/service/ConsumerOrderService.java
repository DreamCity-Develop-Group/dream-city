package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.InvestOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 投资订单
 */
@FeignClient(value = "city-trade")
@RequestMapping("/order")
public interface ConsumerOrderService {


    /**
     * 创建订单
     * @param record
     * @return
     */
    @RequestMapping("/insertOrder")
    Result insertOrder(@RequestBody InvestOrder record);

    /**
     * 订单作废
     * @param record
     * @return
     */
    @RequestMapping("/orderInvalid")
    Result orderInvalid(@RequestBody InvestOrder record);

    /**
     * 订单取消
     * @param record
     * @return
     */
    @RequestMapping("/orderCancel")
    Result investOrderCancel(@RequestBody InvestOrder record);

    /**
     * 查询订单
     * @param record
     * @return
     */
    @RequestMapping("/getOrder")
    Result getOrder(@RequestBody InvestOrder record);

    /**
     * 订单列表
     * @param record
     * @return
     */
    @RequestMapping("/getOrders")
    Result getOrders(@RequestBody InvestOrder record);



}
