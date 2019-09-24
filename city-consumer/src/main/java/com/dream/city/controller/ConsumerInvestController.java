package com.dream.city.controller;

import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.service.ConsumerOrderHandleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "玩家投资",description = "玩家投资，购买mt")
@RestController
@RequestMapping("/consumer")
public class ConsumerInvestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConsumerOrderHandleService orderHandleService;


    /**
     * 玩家投资
     * @param msg
     * @return
     */
    @ApiOperation(value = "玩家投资",notes = "t可选参数:investId,orderAmount,username,amountType", response = Message.class)
    @RequestMapping("/playerInvest")
    public Message playerInvest(@RequestBody Message msg){
        logger.info("玩家投资", JSONObject.toJSONString(msg));
        return orderHandleService.playerInvest(msg);
    }

    /**
     * 根据投资id查询订单
     * @param msg
     * @return
     */
    @ApiOperation(value = "根据投资id查询订单",notes = "t可选参数:investId,username", response = Message.class)
    @RequestMapping("/getInvest")
    public Message getInvest(@RequestBody Message msg){
        logger.info("查询订单", JSONObject.toJSONString(msg));
        return orderHandleService.getplayerInvestOrderById(msg);
    }

    /**
     * 订单列表
     * @param msg
     * @return
     */
    @RequestMapping("/getInvestList")
    public Message getInvestList(@RequestBody Message msg){
        logger.info("查询订单列表", JSONObject.toJSONString(msg));
        return orderHandleService.getplayerInvestOrders(msg);
    }



    /**
     * 用户取消下单
     * @param msg
     * @return
     */
    /*@RequestMapping("/orderCancel")
    public Message orderCancel(@RequestBody Message msg){
        logger.info("用户取消下单", JSONObject.toJSONString(msg));

        //订单状态修改

        //用户账户金额返回 解冻 退款

        //用户账户流水

        msg.getData().setData(JSON.toJSONString(""));
        msg.setDesc("");
        return msg;
    }*/



}
