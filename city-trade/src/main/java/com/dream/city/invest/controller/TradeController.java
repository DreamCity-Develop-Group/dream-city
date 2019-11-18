package com.dream.city.invest.controller;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerTradeResp;
import com.dream.city.invest.service.PlayerTradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 玩家交易
 */
@RestController
@RequestMapping("/trade")
public class TradeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PlayerTradeService tradeService;


    /**
     * 新增投资记录
     *
     * @param record
     * @return
     */
    @RequestMapping("/insertPlayerTrade")
    public Result<PlayerTrade> insertPlayerTrade(@RequestBody PlayerTrade record) {
        logger.info("新增投资记录，{}", record);
        boolean success = Boolean.FALSE;
        String desc = "新增投资记录失败";
        PlayerTrade resultDate = null;
        try {
            resultDate = tradeService.insertPlayerTrade(record);
            desc = "新增投资记录成功";
            success = Boolean.TRUE;
        } catch (Exception e) {
            desc = "新增投资记录异常";
            logger.error(desc, e);
        }
        return Result.result(success, desc,
                success ? ReturnStatus.SUCCESS.getStatus() : ReturnStatus.FAILED.getStatus(),
                resultDate);
    }

    /**
     * 根据tradeId获取投资记录
     *
     * @param tradeId
     * @return
     */
    @RequestMapping("/getPlayerTradeById")
    public Result<PlayerTradeResp> getPlayerTradeById(@RequestParam Integer tradeId) {
        logger.info("根据tradeId获取投资记录，tradeId：{}", tradeId);
        boolean success = Boolean.FALSE;
        String desc = "根据tradeId获取投资记录成功";
        PlayerTradeResp resultDate = null;
        try {
            resultDate = tradeService.getPlayerTradeById(tradeId);
            success = Boolean.TRUE;
        } catch (Exception e) {
            desc = "根据tradeId获取投资记录异常";
            logger.error(desc, e);
        }
        return Result.result(success, desc,
                success ? ReturnStatus.SUCCESS.getStatus() : ReturnStatus.FAILED.getStatus(),
                resultDate);
    }


    /**
     * 获取投资记录
     *
     * @param record
     * @return
     */
    @RequestMapping("/getPlayerTrade")
    public Result<PlayerTrade> getPlayerTrade(@RequestBody PlayerTrade record) {
        logger.info("获取投资记录，{}", record);
        boolean success = Boolean.FALSE;
        String desc = "获取投资记录成功";
        PlayerTrade resultDate = null;
        try {
            resultDate = tradeService.getPlayerTrade(record);
            success = Boolean.TRUE;
        } catch (Exception e) {
            desc = "获取投资记录异常";
            logger.error(desc, e);
        }
        return Result.result(success, desc,
                success ? ReturnStatus.SUCCESS.getStatus() : ReturnStatus.FAILED.getStatus(),
                resultDate);
    }

    /**
     * 获取投资记录列表
     *
     * @param record
     * @return
     */
    @RequestMapping("/getPlayerTradeList")
    public Result<List<PlayerTradeResp>> getPlayerTradeList(@RequestBody PlayerTradeReq record) {
        logger.info("获取投资记录，{}", record);
        boolean success = Boolean.FALSE;
        String desc = "获取投资记录失败";
        List<PlayerTradeResp> resultDate = null;
        try {
            resultDate = tradeService.getPlayerTradeList(record);
            success = Boolean.TRUE;
            desc = "获取投资记录成功";
        } catch (Exception e) {
            desc = "获取投资记录异常";
            logger.error(desc, e);
        }
        return Result.result(success, desc,
                success ? ReturnStatus.SUCCESS.getStatus() : ReturnStatus.FAILED.getStatus(),
                resultDate);
    }


    /**
     * 玩家充值
     *
     * @return
     */
    @RequestMapping("/playerRecharge")
    public Result<PlayerTrade> playerRecharge(@RequestBody PlayerAccountReq record) {
        logger.info("玩家充值，{}", record);
        return tradeService.playerRecharge(record);
    }

    /**
     * 玩家提现
     *
     * @return
     */
    @RequestMapping("/playerWithdraw")
    public Result<PlayerTrade> playerWithdraw(@RequestBody PlayerAccountReq record) {
        logger.info("玩家提现，{}", record);
        return tradeService.playerWithdraw(record);
    }

    /**
     * 玩家转账
     *
     * @return
     */
    @RequestMapping("/playerTransfer")
    public Result<PlayerTrade> playerTransfer(@RequestBody PlayerAccountReq record) throws BusinessException {
        logger.info("玩家转账，{}", record);
        try {
            return tradeService.playerTransfer(record);
        }catch (Exception e){
            e.printStackTrace();
            return Result.result(false);
        }
    }


}
