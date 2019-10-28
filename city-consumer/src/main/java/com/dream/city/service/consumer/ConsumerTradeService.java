package com.dream.city.service.consumer;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.entity.TradeDetail;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerTradeResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *  玩家交易
 *  充值、提现、转账
 */
@FeignClient(value = "city-trade")
@RequestMapping("/trade")
public interface ConsumerTradeService {


    /**
     * 新增投资记录
     * @param record
     * @return
     */
    @RequestMapping("/insertPlayerTrade")
    Result<PlayerTrade> insertPlayerTrade(@RequestBody PlayerTrade record);

    /**
     * 根据tradeId获取投资记录
     * @param tradeId
     * @return
     */
    @RequestMapping("/getPlayerTradeById")
    Result<PlayerTradeResp> getPlayerTradeById(@RequestParam Integer tradeId);


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
    Result<List<PlayerTradeResp>> getPlayerTradeList(@RequestBody PlayerTradeReq record);


    /**
     * 玩家充值
     * @return
     */
    @RequestMapping("/playerRecharge")
    Result<PlayerTrade>  playerRecharge(@RequestBody PlayerAccountReq record);


    /**
     * 玩家提现
     * @return
     */
    @RequestMapping("/playerWithdraw")
    Result<PlayerTrade>  playerWithdraw(@RequestBody PlayerAccountReq record);


    /**
     * 玩家转账
     * @return
     */
    @RequestMapping("/playerTransfer")
    Result<PlayerTrade>  playerTransfer(@RequestBody PlayerAccountReq record);

    @RequestMapping("/investCollectEarning")
    Result investCollectEarning(@RequestParam("playerId") String playerId, @RequestParam("investId") int investId);




    /**
     * 新增交易明细
     * @param record
     * @return
     */
    @RequestMapping("/insertTradeDetail")
    Result<Integer> insertTradeDetail(@RequestBody TradeDetail record);



    /**
     * 根据id获取交易明细
     * @param id
     * @return
     */
    @RequestMapping("/getTradeDetailById/{id}")
    Result<TradeDetail> getTradeDetailById(@PathVariable("id") Integer id);




    /**
     * 获取交易明细列表
     * @param record
     * @return
     */
    @RequestMapping("/getTradeDetailList")
    Result<List<PlayerTradeResp>> getTradeDetailList(@RequestBody PlayerTradeReq record);




}
