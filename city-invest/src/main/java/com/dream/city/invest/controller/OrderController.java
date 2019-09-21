package com.dream.city.invest.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.invest.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 投资订单
 */
@RestController
@RequestMapping("/invest")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private OrderService orderService;


    /**
     * 新建投资
     * @param order
     * @return
     */
    @RequestMapping("/insertInvest")
    public Result insertInvestOrder(@RequestBody InvestOrder order) {
        logger.info("新建投资，{}", order);

        int i = orderService.insertInvestOrder(order);
        String desc = "新建投资失败";
        if (i > 0){
            desc = "新建投资成功";
        }
        Result<Integer> result = new Result<>(Boolean.TRUE,desc,i);
        return result;
    }

    /**
     * 查询投资项
     * @param order
     * @return
     */
    @RequestMapping("/getInvestOrder")
    public Result getInvestOrder(@RequestBody InvestOrder order) {
        logger.info("查询投资，{}", order);

        InvestOrder investOrder = orderService.getInvestOrder(order);
        String desc = "新建投资失败";
        if (investOrder != null){
            desc = "新建投资成功";
        }
        Result<InvestOrder> result = new Result<>(Boolean.TRUE,desc,investOrder);
        return result;
    }

    /**
     * 投资订单作废
     * @param order
     * @return
     */
    @RequestMapping("/investOrderInvalid")
    public Result investOrderInvalid(@RequestBody InvestOrder order){
        logger.info("投资订单作废，{}", order);

        int i = orderService.investOrderInvalid(order);
        String desc = "投资订单作废失败";
        if (i > 0){
            desc = "投资订单作废成功";
        }
        Result<Integer> result = new Result<>(Boolean.TRUE,desc,i);
        return result;
    }


    /**
     * 投资订单取消
     * @param order
     * @return
     */
    @RequestMapping("/investOrderCancel")
    public Result investOrderCancel(@RequestBody InvestOrder order){
        logger.info("投资订单取消，{}", order);

        int i = orderService.investOrderCancel(order);
        String desc = "投资订单取消失败";
        if (i > 0){
            desc = "投资订单取消成功";
        }
        Result<Integer> result = new Result<>(Boolean.TRUE,desc,i);
        return result;
    }


    /**
     * 投资订单列表
     * @param order
     * @return
     */
    @RequestMapping("/getInvestOrders")
    public Result getInvestOrders(@RequestBody InvestOrder order) {
        logger.info("投资订单列表，{}", order);

        String desc = "投资订单列表失败";
        List<InvestOrder> data = null;
        try {
            data = orderService.getInvestOrders(order);
            desc = "投资订单列表成功";
        } catch (Exception e) {
            logger.error("投资订单列表异常", e);
        }
        Result<List<InvestOrder>> result = new Result<>(Boolean.TRUE, desc, data);
        return result;
    }










}
