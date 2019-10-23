package com.dream.city.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.model.resp.PlayerTradeResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.ConsumerAccountService;
import com.dream.city.service.ConsumerCommonsService;
import com.dream.city.service.ConsumerPlayerService;
import com.dream.city.service.ConsumerTradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


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
    @Autowired
    private ConsumerPlayerService playerService;
    @Autowired
    private ConsumerAccountService accountService;
    @Autowired
    private RedisUtils redisUtils;

    private final String PlAYER_UPGRADE = "PUSHER_CHANNEL";


    /**
     * 根据用户id获取交易明细
     * @param msg
     * @return
     */
    @ApiOperation(value = "根据用户id获取交易明细",httpMethod = "POST", notes = "t参数:playerId", response = Message.class)
    @RequestMapping("/trade/getTradeDetailList")
    public Message getTradeDetailList(@RequestBody Message msg){
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
                Map<String,Object> dataMap = new HashMap<>();
                for(PlayerTradeResp tradeResp: tradeDetailList){
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
    @ApiOperation(value = "根据tradeId获取投资记录",httpMethod = "POST", notes = "t参数:tradeId", response = Message.class)
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
    @ApiOperation(value = "获取投资记录",httpMethod = "POST", notes = "t可选参数:tradeId,playerId,username,nick,tradeType", response = Message.class)
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
    @ApiOperation(value = "获取投资记录列表",httpMethod = "POST", notes = "t可选参数:tradeId,playerId,username,nick,tradeType", response = Message.class)
    @RequestMapping("/trade/getPlayerTradeList")
    public Message getPlayerTradeList(@RequestBody Message msg){
        logger.info("获取投资记录列表", JSONObject.toJSONString(msg));

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
    @ApiOperation(value = "玩家充值",httpMethod = "POST", notes = "t参数:playerId,username,nick,accUsdt,accMt", response = Message.class)
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

        Result<PlayerTrade>  tradeResult = tradeService.playerRecharge(accountReq);
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
    @ApiOperation(value = "玩家提现",httpMethod = "POST", notes = "t参数:playerId,username,nick,accUsdt,accMt", response = Message.class)
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

        Result<PlayerTrade>  tradeResult = tradeService.playerWithdraw(accountReq);
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
    @ApiOperation(value = "玩家转账",httpMethod = "POST", notes = "t参数:playerId,username,nick,accUsdt,accMt", response = Message.class)
    @RequestMapping("/trade/transfer")
    public Message playerTransfer(@RequestBody Message msg){
        logger.info("玩家转账", JSONObject.toJSONString(msg));

        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);
        String playerId = accountReq.getAccPlayerId();
        PlayerResp playerReq = null;
        if (StringUtils.isBlank(playerId)) {
            playerReq = commonsService.getPlayerByUserName(msg);
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

        PlayerAccount playerAccountReq = new PlayerAccount();
        playerAccountReq.setAccAddr(accountReq.getAccAddr());
        Result<PlayerAccount> playerAccountResult = accountService.getPlayerAccount(playerAccountReq);
        if (playerId.equalsIgnoreCase(playerAccountResult.getData().getAccPlayerId())){
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

                PlayerAccount playerAccount = null;
                if (playerAccountResult!= null){
                    playerAccount = playerAccountResult.getData();
                    if (playerAccount != null && StringUtils.isNotBlank(playerAccount.getAccPlayerId())){
                        Player player2 = playerService.getPlayerByPlayerId(playerAccount.getAccPlayerId());

                        msg.setCode(tradeResult.getCode());
                        resultMap.put("desc","转账["+tradeResult.getData().getTradeAmount()+"]USDT给["+player2.getPlayerName()+"]成功");
                        //commonsService.sendMessage(playerId,null,JSON.toJSONString(resultMap));

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
                        commonsService.sendMessage(player2.getPlayerId(),null,JSON.toJSONString(resultMap));*/
                    }
                }
                resultMap.put("mt",tradeResult.getData().getQuotaTax());
                resultMap.put("code",tradeResult.getCode());
            }else {
                msg.setCode(tradeResult.getCode());
                msg.getData().setCode(tradeResult.getCode());
                resultMap.put("code",tradeResult.getCode());
                commonsService.sendMessage(playerId,null,"转账失败");
            }
        }else {
            msg.setCode(ReturnStatus.FAILED.getStatus());
            resultMap.put("code",ReturnStatus.FAILED.getStatus());
            commonsService.sendMessage(playerId,null,"转账失败");
        }

        msg.getData().setData(resultMap);
        msg.setDesc(tradeResult.getMsg());
        return msg;
    }

    @RequestMapping("/trade/invest/collect/earning")
    public Message investCollectEarning(@RequestBody Message message){
        Object dataMsg = message.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);
        String playerId = jsonObject.getString("playerId");
        int investId = jsonObject.getInteger("investId");

        Result result = tradeService.investCollectEarning(playerId,investId);

        message.getData().setCode(result.getCode());
        return message;
    }


}
