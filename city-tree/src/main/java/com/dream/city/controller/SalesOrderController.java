package com.dream.city.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.SalesOrder;
import com.dream.city.service.SalesOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/tree")
public class SalesOrderController {

    @Autowired
    private SalesOrderService salesOrderService;


    @RequestMapping("/get/salesOrder")
    public Result getSalesOrder(@RequestParam("playerId")String playerId){
        SalesOrder order = salesOrderService.getSalesOrder(1L);
        List<SalesOrder> orders = salesOrderService.selectSalesOrder(playerId);
        return new Result("success",200,orders);
    }

    @RequestMapping("/player/buy/mt")
    public Result playerBuyMt(@RequestParam("amount")BigDecimal buyAmount,@RequestParam("playerId")String playerId){
        if (StringUtils.isBlank(playerId)){
            return new Result("参数错误",501);
        }
        if (buyAmount.compareTo(new BigDecimal(0.00))<=1){
            return new Result("购买额度不能小于0",500);
        }

        return salesOrderService.buyMt(buyAmount,playerId);

    }

    /**
     * 取得当前玩家的所有MT购买请求:玩家是卖家
     * @param playerId
     * @return
     */
    @RequestMapping("/get/buyer/order")
    public Result getBuyerOrder(@RequestParam("playerId")String playerId){
        if (StringUtils.isBlank(playerId)){
            return new Result("参数错误",501);
        }
        List<SalesOrder> orders = salesOrderService.selectSalesSellerOrder(playerId);

        return new Result("sucess",200,orders);
    }


    /**
     * 取得当前玩家的所有MT购买请求:玩家是买家
     * @param playerId
     * @return
     */
    @RequestMapping("/get/seller/order")
    public Result getSellerOrder(@RequestParam("playerId")String playerId){
        if (StringUtils.isBlank(playerId)){
            return new Result("参数错误",501);
        }
        List<SalesOrder> orders = salesOrderService.selectSalesBuyerOrder(playerId);

        return new Result("sucess",200,orders);
    }


}
