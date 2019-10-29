package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.service.handler.InvestService;
import com.dream.city.service.handler.PropertyService;
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
    InvestService investService;
    @Autowired
    PropertyService propertyService;


    /**
     * 预约投资
     * @param msg
     * @return
     */
    @ApiOperation(value = "预约投资",httpMethod = "POST", notes = "t参数:inType,playerId", response = Message.class)
    @RequestMapping("/playerInvest")
    public Message playerInvest(@RequestBody Message msg){
        try {
            return investService.playerInvest(msg);
        }catch (Exception e){
            return msg;
        }
    }


    /**
     * 投资
     * @param msg
     * @return
     */
    /*@ApiOperation(value = "投资",httpMethod = "POST", notes = "t可选参数:investId,orderAmount,username,amountType", response = Message.class)
    @RequestMapping("/playerInvesting")
    public Message playerInvesting(@RequestBody Message msg){
        try {
            return investService.playerInvest(msg);
        }catch (Exception e){
            return msg;
        }
    }*/


    /**
     * 根据投资id查询订单
     * @param msg
     * @return
     */
    @ApiOperation(value = "根据投资id查询订单",httpMethod = "POST", notes = "t可选参数:investId,username", response = Message.class)
    @RequestMapping("/getInvest")
    public Message getInvest(@RequestBody Message msg){
        try {
            return investService.getInvest(msg);
        }catch (Exception e){
            return msg;
        }
    }


    /**
     * 投资列表
     * @param msg
     * @return
     */
    @ApiOperation(value = "投资列表", httpMethod = "POST", notes = "投资列表", response = Message.class)
    @RequestMapping("/getInvestList")
    public Message getInvestList(@RequestBody Message msg){
        try {
            return investService.getInvestList(msg);
        }catch (Exception e){
            return msg;
        }
    }



}
