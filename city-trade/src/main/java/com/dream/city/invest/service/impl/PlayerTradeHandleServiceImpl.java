package com.dream.city.invest.service.impl;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.entity.TradeDetail;
import com.dream.city.base.model.entity.TradeVerify;
import com.dream.city.base.model.enu.*;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.invest.service.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class PlayerTradeHandleServiceImpl implements PlayerTradeHandleService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;
    @Autowired
    private PlayerTradeService tradeService;
    @Autowired
    private TradeVerifyService verifyService;
    @Autowired
    private TradeDetailService tradeDetailService;

    @Value("${player.withdraw.mt.tax:5}")
    private BigDecimal withdrawTax;
    @Value("${player.transfer.mt.tax:5}")
    private BigDecimal transferTax;
    @Value("${player.inside.transfer.verify:true}")
    private Boolean transferVerify;


    @Override
    @Transactional
    public Result<Boolean> playerRecharge(PlayerAccountReq record) {
        boolean success = Boolean.FALSE;
        String msg = null;
        record.setTradeType(TradeType.RECHARGE.name());
        record.setInOut(AmountDynType.IN.name());

        Result<BigDecimal> updateAccountResult = null;
        try {
            Result<PlayerTrade> playerTradeResult = null;
            //充值
            updateAccountResult = this.updatePlayerAccount(record);
            if (updateAccountResult != null){
                success = updateAccountResult.getSuccess();
                msg = updateAccountResult.getMsg();

                //新增交易记录
                record.setAccId(record.getAccId());
                playerTradeResult = this.createPlayerTrade(record, updateAccountResult.getData(), updateAccountResult.getMsg());
            }
            if (playerTradeResult != null && playerTradeResult.getSuccess()){
                this.createTradeDetail(playerTradeResult.getData().getTradePlayerId(), playerTradeResult.getData().getTradeId(),
                        playerTradeResult.getData().getTradeAmount(), null,null,TradeDetailType.RECHARGE.getCode(), "充值");
            }
        }catch (Exception e){
            logger.error("充值异常",e);
            throw new BusinessException("充值异常");
        }

        if (success){
            // todo
            msg = "您的充值已到账，金额：" + updateAccountResult.getData();

        }
        return new Result<>(success,msg,success);
    }



    @Override
    @Transactional
    public Result<Boolean> playerWithdraw(PlayerAccountReq record) {
        boolean success = Boolean.FALSE;
        String msg = "玩家提现";
        record.setInOut(AmountDynType.OUT.name());
        record.setTradeType(TradeType.WITHDRAW.name());
        //record.setAmountType(AmountType.USDT.name());
        Result<BigDecimal> updateAccountResult = null;
        try {
            //校验提现规则 todo


            //提现 冻结金额
            BigDecimal tradeAmount = BigDecimal.ZERO;
            updateAccountResult = this.updatePlayerAccount(record);
            if (updateAccountResult != null && updateAccountResult.getSuccess()){
                tradeAmount = updateAccountResult.getData();
                success = updateAccountResult.getSuccess();
                msg = updateAccountResult.getMsg();
            }

            //新增交易记录
            record.setAccId(record.getAccId());
            Result<PlayerTrade> createPlayerTradeResult = this.createPlayerTrade(record,tradeAmount,updateAccountResult.getMsg());

            //新增交易审核 待审核
            TradeVerify tradeVerify = null;
            if (createPlayerTradeResult != null && createPlayerTradeResult.getSuccess()){
                tradeVerify = this.createTradeVerify(createPlayerTradeResult.getData().getTradeId(),
                        VerifyStatus.WAIT.name(), updateAccountResult.getMsg());
            }

            if (createPlayerTradeResult != null && createPlayerTradeResult.getSuccess() && tradeVerify != null){
                this.createTradeDetail(createPlayerTradeResult.getData().getTradePlayerId(), createPlayerTradeResult.getData().getTradeId(),
                        createPlayerTradeResult.getData().getTradeAmount(), null,tradeVerify.getVerifyId(),TradeDetailType.WITHDRAW_FREEZE.getCode(), "提现冻结金额");
                this.createTradeDetail(createPlayerTradeResult.getData().getTradePlayerId(), createPlayerTradeResult.getData().getTradeId(),
                        withdrawTax, null,tradeVerify.getVerifyId(),TradeDetailType.WITHDRAW_FREEZE.getCode(), "提现冻结税金");
            }
        }catch (Exception e){
            logger.error("提现异常",e);
            throw new BusinessException("提现异常");
        }
        return new Result<>(success,msg,success);
    }


    @Override
    @Transactional
    public Result<Boolean> playerTransfer(PlayerAccountReq recordOut) {
        boolean success = Boolean.FALSE;
        String msg = "玩家转账";
        Result<BigDecimal> updateAccountResult = null;
        try {
            String verifyStatus = VerifyStatus.WAIT.name();
            //获取转账账户信息
            PlayerAccount playerAccountOut = accountService.getPlayerAccount(recordOut.getAccPlayerId());
            recordOut.setAccId(playerAccountOut.getAccId());

            //转账账户 出账
            recordOut.setInOut(AmountDynType.OUT.name());
            recordOut.setTradeType(TradeType.TRANSFER_FROM.getCode());
            updateAccountResult = this.updatePlayerAccount(recordOut);
            if (updateAccountResult != null) {
                success = updateAccountResult.getSuccess();
            }

            //新增交易记录 出账
            Result<PlayerTrade> createPlayerTradeResult = this.createPlayerTrade(recordOut,updateAccountResult.getData(), msg);

            //新增交易审核 内部转账：审核通过；外部转账：待审核
            TradeVerify tradeVerify = null;
            if (createPlayerTradeResult.getSuccess()){
                if (transferVerify){
                    tradeVerify = this.createTradeVerify(createPlayerTradeResult.getData().getTradeId(),
                            verifyStatus, msg);
                }else {
                    verifyStatus = VerifyStatus.PASS.name();
                    tradeVerify = this.createTradeVerify(createPlayerTradeResult.getData().getTradeId(),
                            verifyStatus, msg);
                }
            }
            //新增交易流水
            if (createPlayerTradeResult != null && createPlayerTradeResult.getSuccess() && tradeVerify != null){
                if (isInsideAccAddr(playerAccountOut.getAccAddr())){
                    //内部转账
                    if (transferVerify){
                        this.createTradeDetail(createPlayerTradeResult.getData().getTradePlayerId(), createPlayerTradeResult.getData().getTradeId(),
                                createPlayerTradeResult.getData().getTradeAmount(), null,tradeVerify.getVerifyId(),TradeDetailType.TRANSFER_FREEZE.getCode(), "转账冻结金额");
                        this.createTradeDetail(createPlayerTradeResult.getData().getTradePlayerId(), createPlayerTradeResult.getData().getTradeId(),
                                transferTax, null,tradeVerify.getVerifyId(),TradeDetailType.TRANSFER_FREEZE.getCode(), "转账冻结税金");
                    }else {
                        this.createTradeDetail(createPlayerTradeResult.getData().getTradePlayerId(), createPlayerTradeResult.getData().getTradeId(),
                                createPlayerTradeResult.getData().getTradeAmount(), null,tradeVerify.getVerifyId(),TradeDetailType.TRANSFER_VERIFY.getCode(), "转账扣除金额");
                        this.createTradeDetail(createPlayerTradeResult.getData().getTradePlayerId(), createPlayerTradeResult.getData().getTradeId(),
                                transferTax, null,tradeVerify.getVerifyId(),TradeDetailType.TRANSFER_VERIFY.getCode(), "转账扣除税金");
                    }
                }else {
                    //外部转账
                    this.createTradeDetail(createPlayerTradeResult.getData().getTradePlayerId(), createPlayerTradeResult.getData().getTradeId(),
                            createPlayerTradeResult.getData().getTradeAmount(), null,tradeVerify.getVerifyId(),TradeDetailType.TRANSFER_FREEZE.getCode(), "转账冻结金额");
                    this.createTradeDetail(createPlayerTradeResult.getData().getTradePlayerId(), createPlayerTradeResult.getData().getTradeId(),
                            transferTax, null,tradeVerify.getVerifyId(),TradeDetailType.TRANSFER_FREEZE.getCode(), "转账冻结税金");
                }

            }

            //转入
            if (isInsideAccAddr(recordOut.getAccAddr()) && !transferVerify){
                //内部转账不需审核 立即到账
                msg = "玩家内部转账";
                if (success){
                    msg = "玩家内部转账成功";
                    //获取转入账户信息
                    PlayerAccount playerAccountIn = accountService.getPlayerAccount(recordOut.getFriendId());

                    //内部转账 立即到账 转入账户入账
                    PlayerAccountReq recordIn = new PlayerAccountReq();
                    recordIn.setAccId(playerAccountIn.getAccId());
                    recordIn.setAccPlayerId(recordOut.getFriendId());
                    recordIn.setTradeType(TradeType.TRANSFER_TO.getCode());
                    recordIn.setAccUsdt(recordOut.getAccUsdt());
                    recordIn.setAccUsdtAvailable(recordOut.getAccUsdt());
                    Result<BigDecimal> transferInResult = this.updatePlayerAccount(recordIn);

                    //新增交易记录 入账
                    recordIn.setAccId(playerAccountIn.getAccId());
                    this.createPlayerTrade(recordIn,transferInResult.getData(), transferInResult.getMsg());

                    this.createTradeDetail(createPlayerTradeResult.getData().getTradePlayerId(), createPlayerTradeResult.getData().getTradeId(),
                            createPlayerTradeResult.getData().getTradeAmount(), null,null,TradeType.TRANSFER_TO.getCode(), "转账进账");
                }
            }else {
                //外部转账 相当于提现 待审核
                msg = "玩家外部转账,待审核";
            }
        }catch (Exception e){
            logger.error("转账异常",e);
            throw new BusinessException("转账异常");
        }
        return new Result<>(success,msg,success);
    }


    /**
     * 判断是内部还是外部地址
     * @param accAddr 玩家账户地址
     * @return TRUE：内部地址，FALSE外部地址
     */
    private boolean isInsideAccAddr(String accAddr){
        //判断accAddr是内部还是外部地址 todo

        return Boolean.TRUE;
    }


    private void createTradeDetail(String playerId,Integer tradeId,BigDecimal tradeAmount,Integer orderId,Integer verifyId,String tradeDetailType,String descr){
        TradeDetail tradeDetail = new TradeDetail();
        tradeDetail.setTradeId(tradeId);
        tradeDetail.setTradeAmount(tradeAmount);
        tradeDetail.setDescr(descr);
        tradeDetail.setPlayerId(playerId);
        tradeDetail.setTradeDetailType(tradeDetailType);
        tradeDetail.setOrderId(orderId);
        if (verifyId != null){
            tradeDetail.setVerifyId(verifyId);
            tradeDetail.setVerifyTime(new Date());
        }
        tradeDetailService.insert(tradeDetail);
    }


    /**
     * 更新账户数据
     * 充值、提现、转账
     * @param record
     * @return 交易金额
     */
    private Result<BigDecimal> updatePlayerAccount(PlayerAccountReq record) throws Exception{
        boolean success = Boolean.FALSE;
        String tradeType = null;
        String desc = "失败！";
        PlayerAccount getPlayerAccount = accountService.getPlayerAccount(record.getAccPlayerId());

        //更新PlayerAccount参数
        PlayerAccount accountUpdate = new PlayerAccount();
        accountUpdate.setAccId(getPlayerAccount.getAccId());
        accountUpdate.setAccPlayerId(record.getAccPlayerId());

        BigDecimal accMt = BigDecimal.ZERO;
        BigDecimal accMtAvailable = BigDecimal.ZERO;
        BigDecimal accMtFreeze = BigDecimal.ZERO;

        BigDecimal accUsdt = BigDecimal.ZERO;
        BigDecimal accUsdtAvailable = BigDecimal.ZERO;
        BigDecimal accUsdtFreeze = BigDecimal.ZERO;

        BigDecimal tradeAmount = BigDecimal.ZERO;
        //充值
        if (record.getTradeType().equalsIgnoreCase(TradeType.RECHARGE.name())){
            tradeType = TradeType.RECHARGE.getDesc();
            //            //账户金额加上充值金额
            if (record.getAmountType().equalsIgnoreCase(AmountType.MT.name())){
                tradeAmount = record.getAccMt();
                accMt = getPlayerAccount.getAccMt().add(tradeAmount);
                accMtAvailable = getPlayerAccount.getAccMtAvailable().add(tradeAmount);
                accMtFreeze = getPlayerAccount.getAccMtFreeze();
            }else if (record.getAmountType().equalsIgnoreCase(AmountType.USDT.name())){
                tradeAmount = record.getAccUsdt();
                accUsdt = getPlayerAccount.getAccUsdt().add(tradeAmount);
                accUsdtAvailable = getPlayerAccount.getAccUsdtAvailable().add(tradeAmount);
                accUsdtFreeze = getPlayerAccount.getAccUsdtFreeze();
            }
        }
        //提现
        if (record.getTradeType().equalsIgnoreCase(TradeType.WITHDRAW.name())){
            tradeType = TradeType.WITHDRAW.getDesc();
            tradeAmount = record.getAccUsdt();
            //账户金额减去提现金额
            accMt = getPlayerAccount.getAccMt().subtract(withdrawTax);
            accMtAvailable = getPlayerAccount.getAccMtAvailable().subtract(withdrawTax);
            accMtFreeze = getPlayerAccount.getAccMtFreeze().add(withdrawTax);
            accUsdt = getPlayerAccount.getAccUsdt();
            accUsdtAvailable = getPlayerAccount.getAccUsdtAvailable().subtract(tradeAmount);
            accUsdtFreeze = getPlayerAccount.getAccUsdtFreeze().add(tradeAmount);
        }
        //转账
        if (TradeType.TRANSFER.name().equalsIgnoreCase(record.getTradeType())){
            tradeAmount = record.getAccUsdt();
            tradeType = TradeType.TRANSFER.getDesc();
            if (TradeType.TRANSFER_FROM.name().equalsIgnoreCase(record.getTradeType())){
                //转账转出 账户金额减去转账金额
                if (isInsideAccAddr(record.getAccAddr())){
                    //内部转账
                    if (transferVerify){
                        //要审核 冻结
                        accMt = getPlayerAccount.getAccMt().subtract(transferTax);
                        accMtAvailable = getPlayerAccount.getAccMtAvailable().subtract(transferTax);
                        accMtFreeze = getPlayerAccount.getAccMtFreeze().add(transferTax);
                        accUsdt = getPlayerAccount.getAccUsdt().subtract(tradeAmount);
                        accUsdtAvailable = getPlayerAccount.getAccUsdtAvailable().subtract(tradeAmount);
                        accUsdtFreeze = getPlayerAccount.getAccUsdtFreeze().add(tradeAmount);
                    }else {
                        //不用审核 立即到账
                        accMt = getPlayerAccount.getAccMt().subtract(transferTax);
                        accMtAvailable = getPlayerAccount.getAccMtAvailable().subtract(transferTax);
                        accMtFreeze = getPlayerAccount.getAccMtFreeze();
                        accUsdt = getPlayerAccount.getAccUsdt().subtract(tradeAmount);
                        accUsdtAvailable = getPlayerAccount.getAccUsdtAvailable().subtract(tradeAmount);
                        accUsdtFreeze = getPlayerAccount.getAccUsdtFreeze();
                    }

                } else {
                    accMt = getPlayerAccount.getAccMt().subtract(transferTax);
                    accMtAvailable = getPlayerAccount.getAccMtAvailable().subtract(transferTax);
                    accMtFreeze = getPlayerAccount.getAccMtFreeze().add(transferTax);
                    accUsdt = getPlayerAccount.getAccUsdt();
                    accUsdtAvailable = getPlayerAccount.getAccUsdtAvailable().subtract(tradeAmount);
                    accUsdtFreeze = getPlayerAccount.getAccUsdtFreeze().add(tradeAmount);
                }
            } else {
                //转账转入 账户金额加上转账金额
                accMt = getPlayerAccount.getAccMt();
                accMtAvailable = getPlayerAccount.getAccMtAvailable();
                accMtFreeze = getPlayerAccount.getAccMtFreeze();
                accUsdt = getPlayerAccount.getAccUsdt().add(tradeAmount);
                accUsdtAvailable = getPlayerAccount.getAccUsdtAvailable().add(tradeAmount);
                accUsdtFreeze = getPlayerAccount.getAccUsdtFreeze();
            }
        }

        accountUpdate.setAccMt(accMt);
        accountUpdate.setAccMtAvailable(accMtAvailable);
        accountUpdate.setAccMtFreeze(accMtFreeze);
        accountUpdate.setAccUsdt(accUsdt);
        accountUpdate.setAccUsdtAvailable(accUsdtAvailable);
        accountUpdate.setAccUsdtFreeze(accUsdtFreeze);

        desc = tradeType + "失败！";

        //更新PlayerAccount
        int updateResult = accountService.updatePlayerAccountById(accountUpdate);
        if (updateResult > 0){
            success = Boolean.TRUE;
            desc = tradeType+"成功！";
        }

        return new Result<>(success,desc,tradeAmount);
    }

    /**
     * 新增交易记录
     * @param record
     */
    private Result<PlayerTrade> createPlayerTrade(PlayerAccountReq record,BigDecimal tradeAmount,String desc) throws Exception{
        PlayerTrade tradeReq = new PlayerTrade();
        tradeReq.setTradeType(record.getTradeType());
        tradeReq.setTradeDesc(desc);
        tradeReq.setTradeAccId(record.getAccId());
        tradeReq.setTradePlayerId(record.getAccPlayerId());
        tradeReq.setTradeAmount(tradeAmount);
        if (TradeType.RECHARGE.getCode().equalsIgnoreCase(record.getTradeType())){
            tradeReq.setTradeStatus(TradeStatus.IN.getCode());
            tradeReq.setInOutStatus(AmountDynType.IN.getCode());
        }else if (TradeType.WITHDRAW.getCode().equalsIgnoreCase(record.getTradeType())){
            tradeReq.setInQuotaTax(withdrawTax);
            tradeReq.setTradeStatus(TradeStatus.FREEZE.getCode());
            tradeReq.setInOutStatus(AmountDynType.OUT.getCode());
        }else if (TradeType.TRANSFER_FROM.getCode().equalsIgnoreCase(record.getTradeType())){
            tradeReq.setInQuotaTax(transferTax);
            tradeReq.setInOutStatus(AmountDynType.OUT.getCode());
            if (transferVerify) {
                tradeReq.setTradeStatus(TradeStatus.FREEZE.getCode());
            }else {
                tradeReq.setTradeStatus(TradeStatus.OUT.getCode());
            }
        }else if (TradeType.TRANSFER_TO.getCode().equalsIgnoreCase(record.getTradeType())){
            tradeReq.setTradeStatus(TradeStatus.IN.getCode());
            tradeReq.setInOutStatus(AmountDynType.IN.getCode());
        }

        //生成交易记录
        PlayerTrade insertPlayerTrade = tradeService.insertPlayerTrade(tradeReq);
        boolean success = Boolean.FALSE;
        if (insertPlayerTrade != null){
            success = Boolean.TRUE;
        }
        return new Result<>(success,desc,insertPlayerTrade);
    }


    /**
     * 新增交易审核
     * 待审核、审核通过
     * @param tradeId
     * @param verifyStatus
     * @param verifyDesc
     */
    private TradeVerify createTradeVerify(Integer tradeId,String verifyStatus,String verifyDesc){
        TradeVerify verifyReq = new TradeVerify();
        verifyReq.setVerifyDesc(verifyDesc);
        verifyReq.setVerifyStatus(verifyStatus);
        verifyReq.setVerifyTradeId(tradeId);
        return verifyService.insertTradeVerify(verifyReq);
    }

}
