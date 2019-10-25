package com.dream.city.controller;

import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.req.CityInvestReq;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.service.ConsumerOrderHandleService;
import com.dream.city.service.ConsumerPropertyHandleService;
import com.dream.city.service.ConsumerTradeVerifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @Autowired
    ConsumerTradeVerifyService verifyService;
    @Autowired
    private ConsumerPropertyHandleService investService;


    /**
     * 预约投资
     * @param msg
     * @return
     */
    @ApiOperation(value = "预约投资",httpMethod = "POST", notes = "t参数:investId,playerId", response = Message.class)
    @RequestMapping("/playerInvest")
    public Message playerInvest(@RequestBody Message msg){
        logger.info("预约投资", JSONObject.toJSONString(msg));
        return orderHandleService.playerInvest(msg);
    }


    /**
     * 投资
     * @param msg
     * @return
     */
    /*@ApiOperation(value = "投资",httpMethod = "POST", notes = "t可选参数:investId,orderAmount,username,amountType", response = Message.class)
    @RequestMapping("/playerInvesting")
    public Message playerInvesting(@RequestBody Message msg){
        logger.info("投资", JSONObject.toJSONString(msg));
        return orderHandleService.playerInvest(msg);
    }*/


    /**
     * 根据投资id查询订单
     * @param msg
     * @return
     */
    @ApiOperation(value = "根据投资id查询订单",httpMethod = "POST", notes = "t可选参数:investId,username", response = Message.class)
    @RequestMapping("/getInvest")
    public Message getInvest(@RequestBody Message msg){
        logger.info("查询订单", JSONObject.toJSONString(msg));
        return orderHandleService.getPlayerInvestOrderById(msg);
    }


    /**
     * 投资列表
     * @param msg
     * @return
     */
    @ApiOperation(value = "投资列表", httpMethod = "POST", notes = "投资列表", response = Message.class)
    @RequestMapping("/getInvestList")
    public Message getInvestList(@RequestBody Message msg){
        logger.info("投资列表", JSONObject.toJSONString(msg));
        CityInvestReq invest = DataUtils.getInvestFromMessage(msg);
        Result<List<Map<String,Object>>> result = investService.getPropertyLsit(invest);
        Map<String,Object>  dataResult = new HashMap<>();
        dataResult.put("investList",result.getData());
        if (StringUtils.isNotBlank(invest.getFriendId())){
            dataResult.put("playerId",invest.getFriendId());
        }else {
            dataResult.put("playerId",invest.getPlayerId());
        }
        msg.getData().setData(dataResult);
        msg.setDesc(result.getMsg());
        msg.setCode(result.getSuccess()? ReturnStatus.SUCCESS.getStatus():ReturnStatus.ERROR.getStatus());
        return msg;
    }


    /**
     * 投资列表
     * @param msg
     * @return
     */
    /*@RequestMapping("/getInvestList")
    public Message getInvestList(@RequestBody Message msg){
        logger.info("查询投资列表", JSONObject.toJSONString(msg));
        return orderHandleService.getPlayerInvestOrders(msg);
    }*/





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
