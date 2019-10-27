package com.dream.city.service.handler;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.req.InvestOrderReq;
import com.dream.city.base.model.resp.InvestResp;
import com.dream.city.base.model.resp.PlayerResp;

import java.math.BigDecimal;

public interface OrderService {


    Message playerInvest(Message msg);

    Message playerInvesting(Message msg);


    /**
     * 获取当前投资项目数据
     *
     * @param inId
     * @param inName
     * @return
     */
    InvestResp getInvestByIdOrinName(Integer inId, String inName, Integer inType);

    /**
     * 生成订单
     *
     * @param invest
     * @param orderPayerId
     * @param desc
     * @return
     */
    Result<InvestOrder> orderCreate(InvestResp invest, String orderPayerId, int orderRepeat, String desc);

    /**
     * 下单用户账户扣减金额
     * 冻结金额
     * 投资只能用usdt
     *
     * @param orderAmount
     * @return
     */
    Result deductPlayerAccountAmount(BigDecimal orderAmount, BigDecimal inTax, PlayerAccount playerAccount);

    /**
     * 用户交易流水
     *
     * @param playerId
     * @param orderId
     * @param tradeAmount
     * @param amountType
     * @return
     */
    Result<PlayerTrade> createPlayerTrade(String playerId, Integer orderId, BigDecimal tradeAmount, String amountType, String tradeType, String tradeStatus);

    /**
     * 生成订单待审核数据
     *
     * @param trade
     * @return
     */
    Result<TradeVerify> createTradeVerify(PlayerTrade trade);

    Message getPlayerInvestOrderById(Message msg);

    Message getPlayerInvestOrders(Message msg);

    Message getFriendInvestOrders(Message msg);

    Message getPlayerOrFriendOrders(Message msg, InvestOrderReq orderReq, PlayerResp player);

    Message playerInvestInvalid(Message msg);

    Message playerInvestCancel(Message msg);



}
