package com.dream.city.controller;


import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.service.handler.TradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 玩家交易
 * 充值、提现、转账
 */
@Api(value = "玩家充值、提现、转账", description = "玩家充值、提现、转账")
@RestController
@RequestMapping("/consumer")
public class ConsumerTradeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TradeService tradeService;

    private final String PlAYER_UPGRADE = "PUSHER_CHANNEL";


    /**
     * 根据用户id获取交易明细
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "根据用户id获取交易明细", httpMethod = "POST", notes = "t参数:playerId", response = Message.class)
    @RequestMapping("/trade/getTradeDetailList")
    public Message getTradeDetailList(@RequestBody Message msg) {
        try {
            return tradeService.getTradeDetailList(msg);
        } catch (Exception e) {
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }


    /**
     * 根据tradeId获取投资记录
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "根据tradeId获取投资记录", httpMethod = "POST", notes = "t参数:tradeId", response = Message.class)
    @RequestMapping("/trade/getPlayerTradeById")
    public Message getPlayerTradeById(@RequestBody Message msg) {
        try {
            return tradeService.getPlayerTradeById(msg);
        } catch (Exception e) {
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }


    /**
     * 获取投资记录
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "获取投资记录", httpMethod = "POST", notes = "t可选参数:tradeId,playerId,username,nick,tradeType", response = Message.class)
    @RequestMapping("/trade/getPlayerTrade")
    public Message getPlayerTrade(@RequestBody Message msg) {
        try {
            return tradeService.getPlayerTrade(msg);
        } catch (Exception e) {
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }

    /**
     * 获取投资记录列表
     *
     * @param msg
     * @return
     */
    @ApiOperation(value = "获取投资记录列表", httpMethod = "POST", notes = "t可选参数:tradeId,playerId,username,nick,tradeType", response = Message.class)
    @RequestMapping("/trade/getPlayerTradeList")
    public Message getPlayerTradeList(@RequestBody Message msg) {
        try {
            return tradeService.getPlayerTradeList(msg);
        } catch (Exception e) {
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }


    /**
     * 玩家充值
     *
     * @return
     */
    @RequestMapping("/trade/recharge")
    @ApiOperation(value = "玩家充值", httpMethod = "POST", notes = "t参数:playerId,username,nick,accUsdt,accMt", response = Message.class)
    public Message playerRecharge(@RequestBody Message msg) {
        try {
            return tradeService.playerRecharge(msg);
        } catch (Exception e) {
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }


    /**
     * 玩家提现
     *
     * @return
     */
    @RequestMapping("/trade/playerWithdraw")
    @ApiOperation(value = "玩家提现", httpMethod = "POST", notes = "t参数:playerId,username,nick,accUsdt,accMt", response = Message.class)
    public Message playerWithdraw(@RequestBody Message msg) {
        try {
            return tradeService.playerWithdraw(msg);
        } catch (Exception e) {
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }


    /**
     * 玩家转账
     *
     * @return
     */
    @ApiOperation(value = "玩家转账", httpMethod = "POST", notes = "t参数:playerId,username,nick,accUsdt,accMt", response = Message.class)
    @RequestMapping("/trade/transfer")
    public Message playerTransfer(@RequestBody Message msg) throws BusinessException {
        try {
            return tradeService.playerTransfer(msg);
        } catch (Exception e) {
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }


}
