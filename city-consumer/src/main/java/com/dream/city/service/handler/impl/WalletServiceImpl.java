package com.dream.city.service.handler.impl;

import com.alibaba.druid.sql.visitor.functions.If;
import com.alibaba.fastjson.JSON;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.*;
import com.dream.city.base.model.mapper.*;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerTradeResp;
import com.dream.city.base.model.vo.*;
import com.dream.city.base.utils.RedisKeys;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.handler.CommonService;
import com.dream.city.service.handler.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    TradeDetailMapper tradeDetailMapper;
    @Autowired
    PlayerTradeMapper playerTradeMapper;
    @Autowired
    TradeVerifyMapper verifyMapper;
    @Autowired
    RedisUtils redisUtils;

    @Autowired
    PlayerAccountMapper playerAccountMapper;
    @Autowired
    PlayerMapper playerMapper;
    @Autowired
    DepositCallbackLogMapper depositCallbackLogMapper;
    @Autowired
    CommonService commonService;

    private final String assetId = "0xdac17f958d2ee523a2206206994597c13d831ec7";
    private final String CHAIN_ID = "60";
    private final String PlAYER_UPGRADE = "PUSHER_CHANNEL";

    @LcnTransaction
    @Transactional
    @Override
    public List<WithdrawTaskRecord> getWithdrawList(WithdrawParamVo dataVo) {
        List<WithdrawTaskRecord> withdrawTaskRecordList = new ArrayList<>();
        PlayerTradeReq playerTradeReq = new PlayerTradeReq();
        playerTradeReq.setTradeType(TradeType.TRANSFER_FROM.getCode());
        playerTradeReq.setTradeDetailType(TradeDetailType.TRANSFER_VERIFY.getCode());
        playerTradeReq.setVerifyStatus(VerifyStatus.PASS.getCode());
        List<PlayerTradeResp> tradeResps = tradeDetailMapper.getTradeVerifyDetailList();

        tradeResps.forEach(playerTradeResp -> {
            if (StringUtils.isNotBlank(playerTradeResp.getVerifyToAddress())) {
                WithdrawTaskRecord record = new WithdrawTaskRecord(
                        playerTradeResp.getVerifyId().longValue(),//id
                        playerTradeResp.getVerifyId().toString(),//taskId
                        "60",//chainId
                        assetId,//assetId
                        "USDT",//coinType
                        playerTradeResp.getPlayerId(),//userId
                        playerTradeResp.getPlayerId(), //userName
                        playerTradeResp.getVerifyToAddress(),//toWalletAddress
                        playerTradeResp.getTradeAmount(),//amount
                        BigDecimal.ZERO,//transferCharge
                        playerTradeResp.getVerifyDesc(),//memo
                        "",//checksum
                        playerTradeResp.getVerifyTime() //createTime
                );

                withdrawTaskRecordList.add(record);
            }
        });
        return withdrawTaskRecordList;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result withDrawResultCallback(WithdrawFeedbackParamVo dataVo) {
        String tradeVerifylId = dataVo.getTask_id();
        if (Objects.isNull(tradeVerifylId)) {
            log.error("错误的task_id，无法根据task_id获取到相应的交易数据");
            return Result.result(false, "错误的task_id，无法根据task_id获取到相应的交易数据");
        }
        Integer verifyId = Integer.valueOf(tradeVerifylId);
        if (Objects.isNull(verifyId) || verifyId.equals(0)) {
            log.error("交易检验ID无效");
            return Result.result(false, "交易检验ID无效");
        }
        TradeVerify verify = verifyMapper.getTradeVerifyBiId(verifyId);
        if (Objects.isNull(verify)) {
            log.error("错误的交易检验ID");
            return Result.result(false, "错误的交易检验ID");
        }
        Integer tradeId = verify.getVerifyTradeId();
        PlayerTradeResp trade = playerTradeMapper.getPlayerTradeById(tradeId);
        if (Objects.isNull(trade)) {
            log.error("错误的交易检验,要处理的交易详情不存在");
            return Result.result(false, "错误的交易检验,要处理的交易详情不存在");
        }
        //如果提现失败,退款，重新发起提现申请
        if (dataVo.getStatus().equalsIgnoreCase("2")) {
            verify.setVerifyStatus(VerifyStatus.FAILED.getCode());
            //修改校验状态为通过
            verifyMapper.updateByPrimaryKeySelective(verify);
            //退回持有额度和冻结额度

            String playerId = trade.getPlayerId();

            PlayerAccount playerAccount = playerAccountMapper.getPlayerAccount(playerId);
            playerAccount.setAccUsdtFreeze(playerAccount.getAccUsdtFreeze().subtract(trade.getTradeAmount()));
            playerAccount.setAccUsdtAvailable(playerAccount.getAccUsdtAvailable().add(trade.getTradeAmount()));

            int re = playerAccountMapper.updatePlayerAccount(playerAccount);
            if (re > 0) {
                log.error("接收回调并回退账户成功");
                return Result.result(true, "接收回调并回退账户成功");
            }
            log.error("接收回调并回退账户失败");
            return Result.result(false, "接收回调并回退账户失败");

            //提现成功
        } else if (dataVo.getStatus().equalsIgnoreCase("1")) {
            if (verify.getVerifyStatus().equals(VerifyStatus.PASS.getCode())) {

                verify.setVerifyStatus(VerifyStatus.SUCCESS.getCode());
                //修改校验状态为通过
                verifyMapper.updateByPrimaryKeySelective(verify);
                //删除持有额度和冻结额度
                String playerId = trade.getPlayerId();
                PlayerAccount playerAccount = playerAccountMapper.getPlayerAccount(playerId);
                playerAccount.setAccUsdtFreeze(playerAccount.getAccUsdtFreeze().subtract(trade.getTradeAmount()));
                playerAccount.setAccUsdt(playerAccount.getAccUsdt().subtract(trade.getTradeAmount()));
                int re = playerAccountMapper.updatePlayerAccount(playerAccount);
                if (re > 0) {
                    log.error("接收回调并修改账户成功");
                    return Result.result(true, "接收回调并修改账户成功");
                }
            }
            log.error("接收回调并修改账户失败");
            return Result.result(false, "接收回调并修改账户失败");

        } else {
            log.error("未知状态的请求");
            return Result.result(false, "未知状态的请求");
        }
    }

    /**
     * 充值
     *
     * @param dataVo
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Result depositCallback(DepositParamVo dataVo,DepositCallbackLog dlog) {
        if (dataVo.getChainId().equals(CHAIN_ID) && dataVo.getAssetId().equals(assetId)) {
            String playerAddress = dataVo.getToWalletAddress();
            BigDecimal amount = dataVo.getAmount();

            if (Objects.isNull(playerAddress)) {
                log.error("充值回调参数错误,无法获取address");
                return Result.result(false, "充值回调参数错误,无法获取address");
            }
            BigDecimal value = BigDecimal.ZERO;
            if (Objects.isNull(amount) || amount.compareTo(value) == 0) {
                log.error("充值回调参数错误,无法获取额度");
                return Result.result(false, "充值回调参数错误,无法获取额度");
            }
            PlayerAccount account = playerAccountMapper.getPlayerAccountByAddr(playerAddress);
            account.setAccUsdtAvailable(account.getAccUsdtAvailable().add(amount));
            account.setAccUsdt(account.getAccUsdt().add(amount));

            int result = playerAccountMapper.updatePlayerAccount(account);
            if (result > 0) {
                Player player = playerMapper.getPlayer(account.getAccPlayerId());
                if (redisUtils.hasKey(RedisKeys.PLAYER_ONLINE_STATE_KEY + player.getPlayerName())) {
                    Optional<String> optional = redisUtils.get(RedisKeys.PLAYER_ONLINE_STATE_KEY + player.getPlayerName());
                    String clientId = null;
                    Message message = new Message();
                    MessageData data = new MessageData("deposit","server",200);
                    message.setData(data);

                    message.getData().setCode(ReturnStatus.DEPOSIT_FROM.getStatus());

                    Map resultMap = new HashMap();
                    resultMap.put("playerId", account.getAccPlayerId());
                    resultMap.put("amount", amount);
                    resultMap.put("mt", 0);
                    resultMap.put("code", ReturnStatus.SUCCESS.getStatus());
                    resultMap.put("desc", "充值到帐成功");

                    if (optional != null && optional.isPresent()) {
                        clientId = optional.get();
                        message.setTarget(clientId);
                        message.getData().setData(resultMap);
                        redisUtils.publishMsg(PlAYER_UPGRADE, JSON.toJSONString(message));
                    }
                } else {
                    // TODO 生成相应的记录，留给对方查看
                    CityMessage cmessage = new CityMessage();
                    cmessage.setHaveRead(0);
                    cmessage.setFriendId(player.getPlayerId());
                    cmessage.setId(0L);
                    cmessage.setTitle("入账消息");
                    cmessage.setContent("你的充值已经到账,额度：" + amount + "，请查收！");
                    cmessage.setPlayerId(account.getAccPlayerId());
                    cmessage.setCreateTime(new Date());
                    boolean finished = commonService.sendMessage(cmessage);
                    if (!finished) {
                        commonService.sendMessage(cmessage);
                    }
                }

                    log.info("withDrawResultCallback:正确充值：充值成功记录");
                    dlog.setDepDesc(dataVo.getMemo()+"正确充值：充值成功记录");
                    dlog.setCreateTime(new Date());
                    addDepositCallbackLog(dlog);


                return Result.result(true, "更新账户成功");
            } else {
                log.error("更新账户失败");
                return Result.result(false, "更新账户失败");
            }
        }
        log.error("无可处理的数据");
        return Result.result(true, "无可处理的数据");
    }

    @LcnTransaction
    @Transactional
    @Override
    public void addDepositCallbackLog(DepositCallbackLog dlog) {
        depositCallbackLogMapper.insertSelective(dlog);
    }

    @LcnTransaction
    @Transactional
    @Override
    public DepositCallbackLog getDepositCallbackLog(String txId) {
       return depositCallbackLogMapper.getDepositCallbackLog(txId);
    }


}