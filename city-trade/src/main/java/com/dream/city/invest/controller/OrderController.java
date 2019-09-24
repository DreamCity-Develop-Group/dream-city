package com.dream.city.invest.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.invest.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 新建投资
     * @param order
     * @return
     */
    @RequestMapping("/insertOrder")
    public Result<InvestOrder> insertInvestOrder(@RequestBody InvestOrder order) {
        logger.info("新建投资，{}", order);

        InvestOrder investOrder = orderService.insertInvestOrder(order);
        String desc = "新建投资失败";
        boolean success = Boolean.FALSE;
        if (investOrder != null){
            desc = "新建投资成功";
            success = Boolean.TRUE;
        }
        Result<InvestOrder> result = new Result<>(success,desc,investOrder);
        return result;
    }

    /**
     * 查询投资项
     * @param orderId
     * @return
     */
    @RequestMapping("/getOrderById/{orderId}")
    public Result getInvestOrderById(@PathVariable Integer orderId) {
        logger.info("查询投资，orderId:{}", orderId);
        InvestOrder order = new InvestOrder();

        InvestOrder investOrder = orderService.getInvestOrderById(order);
        String desc = "新建投资失败";
        boolean success = Boolean.FALSE;
        if (investOrder != null){
            desc = "新建投资成功";
            success = Boolean.TRUE;
        }
        Result<InvestOrder> result = new Result<>(success,desc,investOrder);
        return result;
    }

    /**
     * 投资订单作废
     * @param order
     * @return
     */
    @RequestMapping("/orderInvalid")
    public Result investOrderInvalid(@RequestBody InvestOrder order){
        logger.info("投资订单作废，{}", order);

        int i = orderService.investOrderInvalid(order);
        String desc = "投资订单作废失败";
        boolean success = Boolean.FALSE;
        if (i > 0){
            desc = "投资订单作废成功";
            success = Boolean.TRUE;
        }
        Result<Integer> result = new Result<>(success,desc,i);
        return result;
    }


    /**
     * 投资订单取消
     * @param order
     * @return
     */
    @RequestMapping("/orderCancel")
    public Result investOrderCancel(@RequestBody InvestOrder order){
        logger.info("投资订单取消，{}", order);

        int i = orderService.investOrderCancel(order);
        String desc = "投资订单取消失败";
        boolean success = Boolean.FALSE;
        if (i > 0){
            desc = "投资订单取消成功";
            success = Boolean.TRUE;
        }
        Result<Integer> result = new Result<>(success,desc,i);
        return result;
    }


    /**
     * 投资订单列表
     * @param order
     * @return
     */
    @RequestMapping("/getOrders")
    public Result getInvestOrders(@RequestBody InvestOrder order) {
        logger.info("投资订单列表，{}", order);

        String desc = "投资订单列表失败";
        boolean success = Boolean.FALSE;
        List<InvestOrder> data = null;
        try {
            data = orderService.getInvestOrderList(order);
            desc = "投资订单列表成功";
            success = Boolean.TRUE;
        } catch (Exception e) {
            logger.error("投资订单列表异常", e);
        }
        Result<List<InvestOrder>> result = new Result<>(success, desc, data);
        return result;
    }










}
