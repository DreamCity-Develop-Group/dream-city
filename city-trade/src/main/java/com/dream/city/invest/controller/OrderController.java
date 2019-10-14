package com.dream.city.invest.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.req.InvestOrderReq;
import com.dream.city.base.model.resp.InvestOrderResp;
import com.dream.city.invest.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 投资订单
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private OrderService orderService;


    /**
     * 预约投资
     * @param order
     * @return
     */
    @RequestMapping("/insertOrder")
    public Result<InvestOrder> insertInvestOrder(@RequestBody InvestOrder order) {
        logger.info("预约投资，{}", order);

        InvestOrder investOrder = orderService.insertInvestOrder(order);
        String desc = "预约投资失败";
        boolean success = Boolean.FALSE;
        if (investOrder != null){
            desc = "预约投资成功";
            success = Boolean.TRUE;
        }
        return new Result<InvestOrder>(success,desc, ReturnStatus.INVEST_SUBSCRIBED.getStatus(),investOrder);
    }

    /**
     * 投资
     * 投资状态改成经营中
     * @param orderId
     * @return
     */
    @RequestMapping("/playerInvesting")
    public Result<Integer> playerInvesting(@RequestParam("orderId") Integer orderId) {
        logger.info("投资，{}", orderId);
        String desc = "投资";
        boolean success = Boolean.FALSE;
        int i = orderService.playerInvesting(orderId);
        if (i > 0){
            desc = "投资成功";
            success = Boolean.TRUE;
        }
        return new Result<>(success,desc,i);
    }

    /**
     * 查询投资项
     * @param orderId
     * @return
     */
    @RequestMapping("/getOrderById/{orderId}")
    public Result<InvestOrderResp> getInvestOrderById(@PathVariable Integer orderId) {
        logger.info("查询投资，orderId:{}", orderId);
        InvestOrder order = new InvestOrder();

        InvestOrderResp investOrder = orderService.getInvestOrderById(order);
        String desc = "查询投资失败";
        boolean success = Boolean.FALSE;
        if (investOrder != null){
            desc = "查询投资成功";
            success = Boolean.TRUE;
        }
        return new Result<>(success,desc,investOrder);
    }

    /**
     * 投资订单作废
     * @param order
     * @return
     */
    @RequestMapping("/orderInvalid")
    public Result<Integer> investOrderInvalid(@RequestBody InvestOrder order){
        logger.info("投资订单作废，{}", order);

        int i = orderService.investOrderInvalid(order);
        String desc = "投资订单作废失败";
        boolean success = Boolean.FALSE;
        if (i > 0){
            desc = "投资订单作废成功";
            success = Boolean.TRUE;
        }
        return  new Result<>(success,desc,i);
    }


    /**
     * 投资订单取消
     * @param order
     * @return
     */
    @RequestMapping("/orderCancel")
    public Result<Integer> investOrderCancel(@RequestBody InvestOrder order){
        logger.info("投资订单取消，{}", order);

        int i = orderService.investOrderCancel(order);
        String desc = "投资订单取消失败";
        boolean success = Boolean.FALSE;
        if (i > 0){
            desc = "投资订单取消成功";
            success = Boolean.TRUE;
        }
        return new Result<>(success,desc,i);
    }


    /**
     * 投资订单列表
     * @param order
     * @return
     */
    @RequestMapping("/getOrders")
    public Result<List<InvestOrderResp>> getInvestOrders(@RequestBody InvestOrderReq order) {
        logger.info("投资订单列表，{}", order);

        String desc = "投资订单列表失败";
        boolean success = Boolean.FALSE;
        List<InvestOrderResp> data = null;
        try {
            data = orderService.getInvestOrderList(order);
            desc = "投资订单列表成功";
            success = Boolean.TRUE;
        } catch (Exception e) {
            logger.error("投资订单列表异常", e);
        }
        return new Result<>(success, desc, data);
    }



    /**
     * 投资数量
     * @param record orderInvestId、orderPayerId、orderState
     * @return
     */
    @RequestMapping("/countOrdersByPayerIdInvestId")
    public Result<Integer> countOrdersByPayerIdInvestId(@RequestBody InvestOrder record){
        logger.info("获取投资数量，{}", record);

        String desc = "获取投资数量失败";
        boolean success = Boolean.FALSE;
        Integer data = null;
        try {
            data = orderService.countOrdersByPayerIdInvestId(record);
            desc = "获取投资数量成功";
            success = Boolean.TRUE;
        } catch (Exception e) {
            logger.error("获取投资数量异常", e);
        }
        return new Result<>(success, desc, data);
    }






}
