package com.dream.city.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.SalesOrder;
import com.dream.city.service.PlayerAccountService;
import com.dream.city.service.SalesOrderService;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.ORB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tree")
public class SalesOrderController {

    @Autowired
    private SalesOrderService salesOrderService;
    @Autowired
    private PlayerAccountService accountService;


    @RequestMapping("/get/salesOrder")
    public Result getSalesOrder(@RequestParam("playerId")String playerId){
        SalesOrder order = salesOrderService.getSalesOrder(1L);
        List<SalesOrder> orders = salesOrderService.selectSalesOrder(playerId);
        return new Result("success",200,orders);
    }

    /**
     * 购买MT==>创建订单
     * @param buyAmount
     * @param playerId
     * @return
     */
    @RequestMapping("/player/buy/mt")
    public Result playerBuyMtCreate(@RequestParam("amount")BigDecimal buyAmount,@RequestParam("playerId")String playerId){
        if (StringUtils.isBlank(playerId)){
            return new Result("参数错误",501);
        }
        if (buyAmount.compareTo(new BigDecimal(0.00))<=1){
            return new Result("购买额度不能小于0",500);
        }
        //支付比率 USDT 和 MT
        BigDecimal rate = salesOrderService.getUsdtToMtRate();
        return salesOrderService.buyMtCreate(buyAmount,rate,playerId);

    }

    /**
     * 验证支付密码 ==》完成订单
     * @param payPass
     * @param playerId
     * @return
     */
    public Result playerBuyMtFinish(@RequestParam("payPass")String payPass,@RequestParam("playerId")String playerId,@RequestParam("orderId")String orderId){
        //找出待支付订单
        SalesOrder  salesOrder = salesOrderService.getSalesOrder(orderId);
        //验证支付密码
        Result result = accountService.checkPayPass(playerId,payPass);
        //完成支付
        if (result.getCode() == 200 && result.getSuccess()){
            return salesOrderService.buyMtFinish(playerId,orderId);
        }else {
            return result;
        }
    }



    /**
     * 专家发货,单条或者多条，遍历货单ID
     */
    @RequestMapping("/player/seller/send")
    public Result playerSellerSend(@RequestParam("playerId")String playerId,@RequestParam("orders")List<String> orders){
        if (StringUtils.isBlank(playerId) || null == orders || orders.size()==0){
            return new Result("参数错误",500);
        }
        List<SalesOrder> salesOrders = new ArrayList<>();
        BigDecimal amountMt = new BigDecimal(0.00);
        for (String order : orders){
            SalesOrder salesOrder = salesOrderService.getSalesOrder(order);
            salesOrders.add(salesOrder);
            amountMt.add(salesOrder.getOrderAmount());
        }
        BigDecimal availbleMt = accountService.getPlayerAccountMTAvailble(playerId);

        if (availbleMt.compareTo(amountMt) < 1){
            return new Result("可用MT额度不足，请及时备货",500);
        }

        salesOrderService.
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
