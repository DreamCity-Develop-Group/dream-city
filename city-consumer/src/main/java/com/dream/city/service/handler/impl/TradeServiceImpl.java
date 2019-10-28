package com.dream.city.service.handler.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerAccountResp;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.model.resp.PlayerTradeResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.consumer.ConsumerAccountService;
import com.dream.city.service.consumer.ConsumerPlayerService;
import com.dream.city.service.consumer.ConsumerTradeService;
import com.dream.city.service.handler.CommonService;
import com.dream.city.service.handler.TradeService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

/**
 * @author Wvv
 * @program: dream-city
 * @File: TradeServiceImpl
 * @description: 交易服务类
 * @create: 2019/10/2019/10/27 23:25:12 [星期日]
 **/
@Service
public class TradeServiceImpl implements TradeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerTradeService tradeService;

    @Autowired
    private ConsumerPlayerService playerService;
    @Autowired
    private ConsumerAccountService accountService;
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    CommonService commonService;

    private final String PlAYER_UPGRADE = "PUSHER_CHANNEL";


    /**
     * 根据用户id获取交易明细
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message getTradeDetailList(@RequestBody Message msg)throws BusinessException {
        logger.info("根据tradeId获取投资记录", JSONObject.toJSONString(msg));
        msg.setCode(ReturnStatus.ERROR.getStatus());
        msg.getData().setCode(ReturnStatus.ERROR.getStatus());
        msg.setDesc("根据用户id获取交易明细");

        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);
        PlayerTradeReq tradeReq = new PlayerTradeReq();
        tradeReq.setPlayerId(accountReq.getAccPlayerId());
        Result<List<PlayerTradeResp>> tradeResult = tradeService.getTradeDetailList(tradeReq);
        Map<String,Object> data = new HashMap<>();
        List<Map<String,Object>> tradeDetailListResult = new ArrayList<>();
        if (tradeResult != null){
            msg.setCode(tradeResult.getCode());
            msg.getData().setCode(tradeResult.getCode());
            msg.setDesc(tradeResult.getMsg());
            if (!CollectionUtils.isEmpty(tradeResult.getData())){
                List<PlayerTradeResp> tradeDetailList = tradeResult.getData();
                Map<String,Object> dataMap = null;
                for(PlayerTradeResp tradeResp: tradeDetailList){
                    dataMap = new HashMap<>();
                    dataMap.put("createTime",tradeResp.getCreateTime());
                    dataMap.put("tradeDetailType",DataUtils.getTradeDetailType(tradeResp.getTradeDetailType()));
                    dataMap.put("tradeAmount",tradeResp.getTradeAmount());
                    dataMap.put("tradeStatus",DataUtils.getTradeStatus(tradeResp.getTradeStatus()));
                    dataMap.put("usdtSurplus",tradeResp.getUsdtSurplus());
                    dataMap.put("mtSurplus",tradeResp.getMtSurplus());
                    tradeDetailListResult.add(dataMap);
                }
            }
        }
        data.put("tradeRecordList",tradeDetailListResult);
        msg.getData().setData(data);
        return msg;
    }


    /**
     * 根据tradeId获取投资记录
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message getPlayerTradeById(@RequestBody Message msg)throws BusinessException {
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
    @LcnTransaction
    @Transactional
    @Override
    public Message getPlayerTrade(@RequestBody Message msg)throws BusinessException {
        logger.info("获取投资记录", JSONObject.toJSONString(msg));

        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);
        String playerId = accountReq.getAccPlayerId();
        PlayerResp player = null;
        if (StringUtils.isBlank(playerId)) {
            player = commonService.getPlayerByUserName(msg);
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
    @LcnTransaction
    @Transactional
    @Override
    public Message getPlayerTradeList(@RequestBody Message msg)throws BusinessException {
        logger.info("获取投资记录列表", JSONObject.toJSONString(msg));

        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);
        String playerId = accountReq.getAccPlayerId();
        PlayerResp player = null;
        if (StringUtils.isBlank(playerId)) {
            player = commonService.getPlayerByUserName(msg);
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
    @LcnTransaction
    @Transactional
    @Override
    public Message playerRecharge(@RequestBody Message msg)throws BusinessException {
        logger.info("玩家充值", JSONObject.toJSONString(msg));

        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);
        String playerId = accountReq.getAccPlayerId();
        PlayerResp player = null;
        if (StringUtils.isBlank(playerId)) {
            player = commonService.getPlayerByUserName(msg);
            playerId = player.getPlayerId();
        }
        accountReq.setAccPlayerId(playerId);

        Result<PlayerTrade>  tradeResult = tradeService.playerRecharge(accountReq);
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
    @LcnTransaction
    @Transactional
    @Override
    public Message playerWithdraw(@RequestBody Message msg)throws BusinessException {
        logger.info("玩家提现", JSONObject.toJSONString(msg));

        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);
        String playerId = accountReq.getAccPlayerId();
        PlayerResp player = null;
        if (StringUtils.isBlank(playerId)) {
            player = commonService.getPlayerByUserName(msg);
            playerId = player.getPlayerId();
        }
        accountReq.setAccPlayerId(playerId);

        Result<PlayerTrade>  tradeResult = tradeService.playerWithdraw(accountReq);
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
    @LcnTransaction
    @Transactional
    @Override
    public Message playerTransfer(@RequestBody Message msg)throws BusinessException {
        logger.info("玩家转账", JSONObject.toJSONString(msg));

        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);
        String playerId = accountReq.getAccPlayerId();
        PlayerResp playerReq = null;
        if (StringUtils.isBlank(playerId)) {
            playerReq = commonService.getPlayerByUserName(msg);
            playerId = playerReq.getPlayerId();
        }
        Player player = playerService.getPlayerByPlayerId(playerId);
        accountReq.setAccPlayerId(playerId);

        Map resultMap = new HashMap();
        resultMap.put("playerId",accountReq.getAccPlayerId());
        resultMap.put("money",accountReq.getMoney());
        resultMap.put("mt",0);
        resultMap.put("code",ReturnStatus.SUCCESS.getStatus());
        resultMap.put("desc","转账失败");

        if (StringUtils.isBlank(accountReq.getOldpwshop()) || !accountReq.getOldpwshop().equals(player.getPlayerTradePass())){
            msg.setCode(ReturnStatus.ERROR_PASS.getStatus());
            msg.getData().setCode(ReturnStatus.ERROR_PASS.getStatus());
            msg.setDesc("交易密码错误");
            return msg;
        }

        PlayerAccountReq playerAccountReq = new PlayerAccountReq();
        playerAccountReq.setAccAddr(accountReq.getAccAddr());
        Result<PlayerAccountResp> playerAccountResult = accountService.getPlayerAccount(playerAccountReq);
        if (playerAccountResult != null && playerAccountResult.getData() != null && StringUtils.isNotBlank(playerId)
                && StringUtils.isNotBlank(playerAccountResult.getData().getPlayerId()) && playerId.equalsIgnoreCase(playerAccountResult.getData().getPlayerId())){
            msg.setCode(ReturnStatus.ERROR_PASS.getStatus());
            msg.getData().setCode(ReturnStatus.ERROR_PASS.getStatus());
            msg.setDesc("不能给自己转账");
            return msg;
        }

        Result<PlayerTrade>  tradeResult = tradeService.playerTransfer(accountReq);
        msg.getData().setCode(tradeResult.getCode());
        resultMap.put("money",accountReq.getMoney());
        resultMap.put("mt",0);
        if (tradeResult != null){
            if (tradeResult.getSuccess()){
                resultMap.put("money",tradeResult.getData().getTradeAmount());
                msg.getData().setCode(tradeResult.getCode());

                PlayerAccountResp playerAccount = null;
                if (playerAccountResult!= null){
                    //内部转账发消息
                    playerAccount = playerAccountResult.getData();
                    if (playerAccount != null && StringUtils.isNotBlank(playerAccount.getPlayerId())){
                        Player player2 = playerService.getPlayerByPlayerId(playerAccount.getPlayerId());

                        msg.setCode(tradeResult.getCode());
                        resultMap.put("desc","转账["+tradeResult.getData().getTradeAmount()+"]USDT给["+player2.getPlayerName()+"]成功");
                        //commonService.sendMessage(playerId,null,JSON.toJSONString(resultMap));

                        Message message = new Message(
                                "server",
                                "client",
                                new MessageData("trade/transfer", "consumer", new JSONObject(), ReturnStatus.TRANSFER_TO.getStatus()),
                                ""
                        );
                        message.setCode(tradeResult.getCode());
                        if (redisUtils.hasKey(RedisKeys.PLAYER_ONLINE_STATE_KEY + player2.getPlayerName())){
                            Optional<String> optional = redisUtils.get(RedisKeys.PLAYER_ONLINE_STATE_KEY + player2.getPlayerName());
                            String clientId = null;
                            if (optional != null && optional.isPresent()){
                                resultMap.put("code",ReturnStatus.TRANSFER_TO.getStatus());
                                clientId = optional.get();
                                message.setTarget(clientId);
                                message.getData().setData(resultMap);
                                redisUtils.publishMsg(PlAYER_UPGRADE, JSON.toJSONString(message));
                            }
                        }

                        /*resultMap.put("code",ReturnStatus.TRANSFER_TO.getStatus());
                        resultMap.put("desc","["+player2.getPlayerName()+"]给您转账["+tradeResult.getData().getTradeAmount()+"]USDT成功");
                        commonService.sendMessage(player2.getPlayerId(),null,JSON.toJSONString(resultMap));*/
                    }
                }
                resultMap.put("mt",tradeResult.getData().getPersonalTax());
                resultMap.put("code",tradeResult.getCode());
            }else {
                msg.setCode(tradeResult.getCode());
                msg.getData().setCode(tradeResult.getCode());
                resultMap.put("code",tradeResult.getCode());
                commonService.sendMessage(playerId,null,"转账失败");
            }
        }else {
            msg.setCode(ReturnStatus.FAILED.getStatus());
            resultMap.put("code",ReturnStatus.FAILED.getStatus());
            commonService.sendMessage(playerId,null,"转账失败");
        }

        msg.getData().setData(resultMap);
        msg.setDesc(tradeResult.getMsg());
        return msg;
    }
}
