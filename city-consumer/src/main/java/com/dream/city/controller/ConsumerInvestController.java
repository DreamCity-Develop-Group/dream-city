package com.dream.city.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *  玩家交易
 *  投资
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerInvestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Autowired
    //private ConsumerTradeService tradeService;

    /**
     * 用户下单
     * @param msg
     * @return
     */
    @RequestMapping("/createOrder")
    public Message createOrder(@RequestBody Message msg){
        logger.info("用户下单", JSONObject.toJSONString(msg));

        //物业扣减金额


        //生成订单


        //下单用户账户扣减金额

        //用户账户流水


        msg.getData().setT(JSON.toJSONString(""));
        msg.setDesc("");
        return msg;
    }

    /**
     * 用户取消下单
     * @param msg
     * @return
     */
    @RequestMapping("/orderCancel")
    public Message orderCancel(@RequestBody Message msg){
        logger.info("用户取消下单", JSONObject.toJSONString(msg));

        //订单状态修改

        //用户账户金额返回 解冻 退款

        //用户账户流水

        msg.getData().setT(JSON.toJSONString(""));
        msg.setDesc("");
        return msg;
    }

    /**
     * 查询订单
     * @param msg
     * @return
     */
    @RequestMapping("/getOrder")
    public Message getOrder(@RequestBody Message msg){
        logger.info("查询订单", JSONObject.toJSONString(msg));



        msg.getData().setT(JSON.toJSONString(""));
        msg.setDesc("");
        return msg;
    }

    /**
     * 订单列表
     * @param msg
     * @return
     */
    @RequestMapping("/getOrders")
    public Message getOrders(@RequestBody Message msg){
        logger.info("查询订单列表", JSONObject.toJSONString(msg));



        msg.getData().setT(JSON.toJSONString(""));
        msg.setDesc("");
        return msg;
    }


}
