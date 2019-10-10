package com.dream.city.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.model.resp.PlayerTradeResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.service.ConsumerCommonsService;
import com.dream.city.service.ConsumerTradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *  玩家交易
 *  充值、提现、转账
 */
@Api(value = "玩家充值、提现、转账",description = "玩家充值、提现、转账")
@RestController
@RequestMapping("/consumer")
public class ConsumerTradeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerTradeService tradeService;
    @Autowired
    private ConsumerCommonsService commonsService;




    /**
     * 根据tradeId获取投资记录
     * @param msg
     * @return
     */
    @ApiOperation(value = "根据tradeId获取投资记录",notes = "t参数:tradeId", response = Message.class)
    @RequestMapping("/trade/getPlayerTradeById")
    public Message getPlayerTradeById(@RequestBody Message msg){
        logger.info("根据tradeId获取投资记录", JSONObject.toJSONString(msg));

        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);

        Result<PlayerTradeResp> tradeResult = tradeService.getPlayerTradeById(accountReq.getAccId());
        Map result = JSON.parseObject(JSON.toJSONString(tradeResult.getData()));
        result.put("username",accountReq.getUsername());
        msg.getData().setData(result);
        msg.setDesc("");
        return msg;
    }


    /**
     * 获取投资记录
     * @param msg
     * @return
     */
    @ApiOperation(value = "获取投资记录",notes = "t可选参数:tradeId,playerId,username,nick,tradeType", response = Message.class)
    @RequestMapping("/trade/getPlayerTrade")
    public Message getPlayerTrade(@RequestBody Message msg){
        logger.info("获取投资记录", JSONObject.toJSONString(msg));

        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);
        String playerId = accountReq.getAccPlayerId();
        PlayerResp player = null;
        if (StringUtils.isBlank(playerId)) {
            player = commonsService.getPlayerByUserName(msg);
            playerId = player.getPlayerId();
        }
        PlayerTrade record = new PlayerTrade();
        record.setTradeAccId(accountReq.getAccId());
        record.setTradeOrderId(accountReq.getTradeOrderId());
        record.setTradePlayerId(playerId);
        record.setTradeType(accountReq.getTradeType());
        record.setTradePlayerId(playerId);

        Result<PlayerTrade> tradeResult = tradeService.getPlayerTrade(record);
        Map resultMap = JSON.parseObject(JSON.toJSONString(tradeResult.getData()));
        resultMap.put("username",accountReq.getUsername());
        msg.getData().setData(resultMap);
        msg.setDesc(tradeResult.getMsg());
        return msg;
    }

    /**
     * 获取投资记录列表
     * @param msg
     * @return
     */
    @ApiOperation(value = "获取投资记录列表",notes = "t可选参数:tradeId,playerId,username,nick,tradeType", response = Message.class)
    @RequestMapping("/trade/getPlayerTradeList")
    public Message getPlayerTradeList(@RequestBody Message msg){
        logger.info("用户下单", JSONObject.toJSONString(msg));

        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);
        String playerId = accountReq.getAccPlayerId();
        PlayerResp player = null;
        if (StringUtils.isBlank(playerId)) {
            player = commonsService.getPlayerByUserName(msg);
            playerId = player.getPlayerId();
        }
        PlayerTradeReq record = new PlayerTradeReq();
        record.setAccId(accountReq.getAccId());
        record.setOrderId(accountReq.getTradeOrderId());
        record.setPlayerId(playerId);
        record.setTradeType(accountReq.getTradeType());

        Result<List<PlayerTradeResp>> tradeResult = tradeService.getPlayerTradeList(record);
        List<PlayerTradeResp> tradeList = tradeResult.getData();
        List<Map> resultList = new ArrayList<>();
        Map<String,Object> map = null;
        for (PlayerTradeResp trade : tradeList){
            map = JSON.parseObject(JSON.toJSONString(trade),Map.class);
            map.put("username",accountReq.getUsername());
            resultList.add(map);
        }

        msg.getData().setData(resultList);
        msg.setDesc(tradeResult.getMsg());
        return msg;
    }


    /**
     * 玩家充值
     * @return
     */
    @RequestMapping("/trade/recharge")
    @ApiOperation(value = "玩家充值",notes = "t参数:playerId,username,nick,accUsdt,accMt", response = Message.class)
    public Message playerRecharge(@RequestBody Message msg){
        logger.info("玩家充值", JSONObject.toJSONString(msg));

        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);
        String playerId = accountReq.getAccPlayerId();
        PlayerResp player = null;
        if (StringUtils.isBlank(playerId)) {
            player = commonsService.getPlayerByUserName(msg);
            playerId = player.getPlayerId();
        }
        accountReq.setAccPlayerId(playerId);

        Result<PlayerTrade> tradeResult = tradeService.playerRecharge(accountReq);
        /*String sendMessage = "玩家充值";
        if (tradeResult.getSuccess()){
            if (accountReq.getAccMt() != null && accountReq.getAccMt().compareTo(BigDecimal.ZERO) > 0){
                sendMessage = sendMessage + accountReq.getAccMt() + "MT成功";
            }
            if (accountReq.getAccUsdt() != null && accountReq.getAccUsdt().compareTo(BigDecimal.ZERO) > 0){
                sendMessage = sendMessage + accountReq.getAccUsdt() + "USDT成功";
            }
        }else {
            if (accountReq.getAccMt() != null && accountReq.getAccMt().compareTo(BigDecimal.ZERO) > 0){
                sendMessage = sendMessage + accountReq.getAccMt() + "MT失败";
            }
            if (accountReq.getAccUsdt() != null && accountReq.getAccUsdt().compareTo(BigDecimal.ZERO) > 0){
                sendMessage = sendMessage + accountReq.getAccUsdt() + "USDT失败";
            }
        }
        commonsService.sendMessage(playerId,null,sendMessage);*/
        Map resultMap = JSON.parseObject(JSON.toJSONString(tradeResult.getData()));
        resultMap.put("username",accountReq.getUsername());
        msg.getData().setData(resultMap);
        msg.setDesc(tradeResult.getMsg());
        return msg;
    }


    /**
     * 玩家提现
     * @return
     */
    @RequestMapping("/trade/playerWithdraw")
    @ApiOperation(value = "玩家提现",notes = "t参数:playerId,username,nick,accUsdt,accMt", response = Message.class)
    public Message playerWithdraw(@RequestBody Message msg){
        logger.info("玩家提现", JSONObject.toJSONString(msg));

        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);
        String playerId = accountReq.getAccPlayerId();
        PlayerResp player = null;
        if (StringUtils.isBlank(playerId)) {
            player = commonsService.getPlayerByUserName(msg);
            playerId = player.getPlayerId();
        }
        accountReq.setAccPlayerId(playerId);

        Result<PlayerTrade> tradeResult = tradeService.playerWithdraw(accountReq);
        /*String sendMessage = "玩家提现";
        if (tradeResult.getSuccess()){
            if (accountReq.getAccMt() != null && accountReq.getAccMt().compareTo(BigDecimal.ZERO) > 0){
                sendMessage = sendMessage + accountReq.getAccMt() + "MT扣款成功，请耐心等待审核";
            }
            if (accountReq.getAccUsdt() != null && accountReq.getAccUsdt().compareTo(BigDecimal.ZERO) > 0){
                sendMessage = sendMessage + accountReq.getAccUsdt() + "USDT扣款成功，请耐心等待审核";
            }
        }else {
            if (accountReq.getAccMt() != null && accountReq.getAccMt().compareTo(BigDecimal.ZERO) > 0){
                sendMessage = sendMessage + accountReq.getAccMt() + "MT失败";
            }
            if (accountReq.getAccUsdt() != null && accountReq.getAccUsdt().compareTo(BigDecimal.ZERO) > 0){
                sendMessage = sendMessage + accountReq.getAccUsdt() + "USDT失败";
            }
        }
        commonsService.sendMessage(playerId,null,sendMessage);*/
        Map resultMap = JSON.parseObject(JSON.toJSONString(tradeResult.getData()));
        resultMap.put("username",accountReq.getUsername());
        msg.getData().setData(resultMap);
        msg.setDesc(tradeResult.getMsg());
        return msg;
    }


    /**
     * 玩家转账
     * @return
     */
    @ApiOperation(value = "玩家转账",notes = "t参数:playerId,username,nick,accUsdt,accMt", response = Message.class)
    @RequestMapping("/trade/transfer")
    public Message playerTransfer(@RequestBody Message msg){
        logger.info("玩家转账", JSONObject.toJSONString(msg));

        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);
        String playerId = accountReq.getAccPlayerId();
        PlayerResp player = null;
        if (StringUtils.isBlank(playerId)) {
            player = commonsService.getPlayerByUserName(msg);
            playerId = player.getPlayerId();
        }
        accountReq.setAccPlayerId(playerId);

        Result<PlayerTrade> tradeResult = tradeService.playerTransfer(accountReq);
        /*String sendMessage = "玩家转账";
        if (tradeResult.getSuccess()){
            if (accountReq.getAccMt() != null && accountReq.getAccMt().compareTo(BigDecimal.ZERO) > 0){
                sendMessage = sendMessage + accountReq.getAccMt() + "MT扣款成功，请耐心等待审核";
            }
            if (accountReq.getAccUsdt() != null && accountReq.getAccUsdt().compareTo(BigDecimal.ZERO) > 0){
                sendMessage = sendMessage + accountReq.getAccUsdt() + "USDT扣款成功，请耐心等待审核";
            }
        }else {
            if (accountReq.getAccMt() != null && accountReq.getAccMt().compareTo(BigDecimal.ZERO) > 0){
                sendMessage = sendMessage + accountReq.getAccMt() + "MT失败";
            }
            if (accountReq.getAccUsdt() != null && accountReq.getAccUsdt().compareTo(BigDecimal.ZERO) > 0){
                sendMessage = sendMessage + accountReq.getAccUsdt() + "USDT失败";
            }
        }
        commonsService.sendMessage(playerId,null,sendMessage);*/
        Map resultMap = JSON.parseObject(JSON.toJSONString(tradeResult.getData()));
        resultMap.put("username",accountReq.getUsername());
        msg.getData().setData(resultMap);
        msg.setDesc(tradeResult.getMsg());
        return msg;
    }


}
