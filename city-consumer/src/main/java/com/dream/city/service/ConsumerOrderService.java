package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.InvestOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


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
    Result<InvestOrder> insertOrder(@RequestBody InvestOrder record);

    /**
     * 订单作废
     * @param record
     * @return
     */
    @RequestMapping("/orderInvalid")
    Result<Integer> orderInvalid(@RequestBody InvestOrder record);

    /**
     * 订单取消
     * @param record
     * @return
     */
    @RequestMapping("/orderCancel")
    Result<Integer> investOrderCancel(@RequestBody InvestOrder record);

    /**
     * 查询订单
     * @param orderId
     * @return
     */
    @RequestMapping("/getOrderById/{orderId}")
    Result getOrderById(@PathVariable Integer orderId);

    /**
     * 订单列表
     * @param record
     * @return
     */
    @RequestMapping("/getOrders")
    Result<List<InvestOrder>> getOrders(@RequestBody InvestOrder record);


}
