package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.req.PlayerAccountReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *  玩家交易
 *  充值、提现、转账
 */
@FeignClient(value = "city-trade")
public interface ConsumerTradeService {

    /**
     * 根据tradeId获取投资记录
     * @param tradeId
     * @return
     */
    @RequestMapping("/getPlayerTradeById")
    Result<PlayerTrade> getPlayerTradeById(@RequestParam Integer tradeId);


    /**
     * 获取投资记录
     * @param record
     * @return
     */
    @RequestMapping("/getPlayerTrade")
    Result<PlayerTrade> getPlayerTrade(@RequestBody PlayerTrade record);

    /**
     * 获取投资记录列表
     * @param record
     * @return
     */
    @RequestMapping("/getPlayerTradeList")
    Result<List<PlayerTrade>> getPlayerTradeList(@RequestBody PlayerTrade record);


    /**
     * 玩家充值
     * @return
     */
    @RequestMapping("/playerRecharge")
    Result playerRecharge(@RequestBody PlayerAccountReq record);


    /**
     * 玩家提现
     * @return
     */
    @RequestMapping("/playerWithdraw")
    Result playerWithdraw(@RequestBody PlayerAccountReq record);


    /**
     * 玩家转账
     * @return
     */
    @RequestMapping("/playerTransfer")
    Result playerTransfer(@RequestBody PlayerAccountReq record);



}
