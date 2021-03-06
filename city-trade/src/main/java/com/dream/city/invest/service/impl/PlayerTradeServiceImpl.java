package com.dream.city.invest.service.impl;


import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.*;
import com.dream.city.base.model.mapper.PlayerTradeMapper;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerAccountResp;
import com.dream.city.base.model.resp.PlayerTradeResp;
import com.dream.city.invest.service.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PlayerTradeServiceImpl implements PlayerTradeService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PlayerTradeMapper playerTradeMapper;


    @Autowired
    private AccountService accountService;
    @Autowired
    private TradeVerifyService verifyService;
    @Autowired
    private TradeDetailService tradeDetailService;
    @Autowired
    DictionaryService dictionaryService;


    @LcnTransaction
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PlayerTrade insertPlayerTrade(PlayerTrade record) throws BusinessException {
        Integer integer = playerTradeMapper.insertSelective(record);
        if (integer == null || integer < 1) {
            return null;
        }
        return record;
    }

    @LcnTransaction
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public int updatePlayerTrade(PlayerTrade record) throws BusinessException {
        Integer integer = playerTradeMapper.updateByPrimaryKeySelective(record);
        return integer == null ? 0 : integer;
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerTradeResp getPlayerTradeById(Integer dynId) throws BusinessException {
        if (dynId == null) {
            return null;
        }
        return playerTradeMapper.getPlayerTradeById(dynId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerTrade getPlayerTrade(PlayerTrade record) throws BusinessException {
        return playerTradeMapper.getPlayerTrade(record);
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<PlayerTradeResp> getPlayerTradeList(PlayerTradeReq record) throws BusinessException {
        return playerTradeMapper.getPlayerTradeList(record);
    }


    @LcnTransaction
    @Transactional
    @Override
    public Result<PlayerTrade> playerRecharge(PlayerAccountReq record) throws BusinessException {
        boolean success = Boolean.FALSE;
        String msg = null;
        record.setTradeType(TradeType.RECHARGE.name());
        record.setInOut(AmountDynType.IN.name());

        Result<BigDecimal> updateAccountResult = null;
        PlayerTrade trade = null;
        try {
            Result<PlayerTrade> playerTradeResult = null;
            //充值
            updateAccountResult = this.updatePlayerAccount(record);
            if (updateAccountResult != null) {
                success = updateAccountResult.getSuccess();
                msg = updateAccountResult.getMsg();

                //新增交易记录
                record.setAccId(record.getAccId());
                playerTradeResult = this.createPlayerTrade(record, updateAccountResult.getData(), updateAccountResult.getMsg());
            }

            if (playerTradeResult != null && playerTradeResult.getSuccess()) {
                PlayerAccount getPlayerAccount = accountService.getPlayerAccount(record.getAccPlayerId());
                BigDecimal usdtSurplus = BigDecimal.ZERO;
                BigDecimal mtSurplus = BigDecimal.ZERO;
                if (record.getAmountType().equalsIgnoreCase(AmountType.USDT.name())) {
                    usdtSurplus = getPlayerAccount.getAccUsdt().add(playerTradeResult.getData().getTradeAmount());
                } else {
                    mtSurplus = getPlayerAccount.getAccMt().add(playerTradeResult.getData().getTradeAmount());
                }
                trade = playerTradeResult.getData();
                this.createTradeDetail(playerTradeResult.getData().getTradePlayerId(), playerTradeResult.getData().getTradeId(),
                        playerTradeResult.getData().getTradeAmount(), usdtSurplus, mtSurplus, null, null, TradeDetailType.RECHARGE.getCode(), "充值");
            }
        } catch (Exception e) {
            logger.error("充值异常", e);
            throw new BusinessException("充值异常");
        }
        return new Result<>(success, msg, trade);
    }


    @LcnTransaction
    @Transactional
    @Override
    public Result<PlayerTrade> playerWithdraw(PlayerAccountReq record) throws BusinessException {
        boolean success = Boolean.FALSE;
        String msg = "玩家提现";
        record.setInOut(AmountDynType.OUT.name());
        record.setTradeType(TradeType.WITHDRAW.name());
        //record.setAmountType(AmountType.USDT.name());
        Result<BigDecimal> updateAccountResult = null;
        PlayerTrade trade = null;
        try {
            String valByKey = dictionaryService.getValByKey("player.withdraw.mt.tax");
            BigDecimal withdrawTax = BigDecimal.ZERO;
            if (StringUtils.isNotBlank(valByKey)) {
                withdrawTax = BigDecimal.valueOf(Double.parseDouble(valByKey));
            }

            //校验提现规则
            PlayerAccount getPlayerAccount = accountService.getPlayerAccount(record.getAccPlayerId());
            Result checkAmount = this.checkAmount(record, getPlayerAccount);
            if (!checkAmount.getSuccess()) {
                return new Result<>(success, checkAmount.getMsg(), checkAmount.getCode());
            }

            //提现 冻结金额
            BigDecimal tradeAmount = BigDecimal.ZERO;
            updateAccountResult = this.updatePlayerAccount(record);
            if (updateAccountResult != null && updateAccountResult.getSuccess()) {
                tradeAmount = updateAccountResult.getData();
                success = updateAccountResult.getSuccess();
                msg = updateAccountResult.getMsg();
            }

            //新增交易记录
            record.setAccId(record.getAccId());
            Result<PlayerTrade> createPlayerTradeResult = this.createPlayerTrade(record, tradeAmount, updateAccountResult.getMsg());

            //新增交易审核 待审核
            TradeVerify tradeVerify = null;
            if (createPlayerTradeResult != null && createPlayerTradeResult.getSuccess()) {
                trade = createPlayerTradeResult.getData();
                tradeVerify = this.createTradeVerify(
                        record.getAccAddr(),createPlayerTradeResult.getData().getTradeId(),
                        VerifyStatus.WAIT.name(), updateAccountResult.getMsg());
            }

            if (createPlayerTradeResult != null && createPlayerTradeResult.getSuccess() && trade != null && tradeVerify != null) {
                BigDecimal usdtSurplus = getPlayerAccount.getAccUsdt().subtract(trade.getTradeAmount());
                this.createTradeDetail(trade.getTradePlayerId(), trade.getTradeId(),
                        trade.getTradeAmount(), usdtSurplus, getPlayerAccount.getAccMt(), null, tradeVerify.getVerifyId(), TradeDetailType.WITHDRAW_FREEZE.getCode(), "提现冻结金额");
                BigDecimal mtSurplus = getPlayerAccount.getAccMt().subtract(withdrawTax);
                this.createTradeDetail(trade.getTradePlayerId(), trade.getTradeId(),
                        withdrawTax, usdtSurplus, mtSurplus, null, tradeVerify.getVerifyId(), TradeDetailType.WITHDRAW_FREEZE.getCode(), "提现冻结税金");
            }
        } catch (Exception e) {
            logger.error("提现异常", e);
            throw new BusinessException("提现异常");
        }
        return new Result<>(success, msg, trade);
    }


    @LcnTransaction
    @Transactional
    @Override
    public Result checkAmount(PlayerAccountReq record, PlayerAccount getPlayerAccount) throws BusinessException {
        String valByKey = dictionaryService.getValByKey("player.withdraw.mt.tax");
        String valByKey2 = dictionaryService.getValByKey("player.transfer.mt.tax");
        BigDecimal withdrawTax = BigDecimal.ZERO;
        BigDecimal transferTax = BigDecimal.ZERO;
        if (StringUtils.isNotBlank(valByKey)) {
            withdrawTax = BigDecimal.valueOf(Double.parseDouble(valByKey));
        }
        if (StringUtils.isNotBlank(valByKey2)) {
            transferTax = BigDecimal.valueOf(Double.parseDouble(valByKey2));
        }
        //内部转账税
        String valByKey4 = dictionaryService.getValByKey("player.inside.transfer.mt.tax");
        BigDecimal insideTransferTax = BigDecimal.ZERO;
        if (StringUtils.isNotBlank(valByKey4)) {
            insideTransferTax = BigDecimal.valueOf(Double.parseDouble(valByKey4));
        }
        PlayerAccountResp accountByAccAddr = this.getrAccountByAccAddr(record.getAccAddr());

        String msg = "";
        boolean success = Boolean.TRUE;
        int code = ReturnStatus.SUCCESS.getStatus();
        if (record.getAmount().compareTo(getPlayerAccount.getAccUsdtAvailable()) > 0) {
            msg = "USDT余额不足";
            success = Boolean.FALSE;
            code = ReturnStatus.NOT_ENOUGH.getStatus();
        }
        if (TradeType.WITHDRAW.getCode().equalsIgnoreCase(record.getTradeType()) && withdrawTax.compareTo(getPlayerAccount.getAccMtAvailable()) > 0) {
            msg = "MT余额不足";
            success = Boolean.FALSE;
            code = ReturnStatus.NOT_ENOUGH_MT.getStatus();
        }
        if (TradeType.TRANSFER.getCode().equalsIgnoreCase(record.getTradeType())) {
            if (accountByAccAddr == null) {
                //外部转账
                if (transferTax.compareTo(getPlayerAccount.getAccMtAvailable()) > 0) {
                    msg = "MT余额不足";
                    success = Boolean.FALSE;
                    code = ReturnStatus.NOT_ENOUGH_MT.getStatus();
                }
            } else {
                //内部转账
                if (insideTransferTax.compareTo(getPlayerAccount.getAccMtAvailable()) > 0) {
                    msg = "MT余额不足";
                    success = Boolean.FALSE;
                    code = ReturnStatus.NOT_ENOUGH_MT.getStatus();
                }
            }
        }

        return Result.result(success, msg, code);
    }


    @LcnTransaction
    @Transactional
    @Override
    public Result<PlayerTrade> playerTransfer(PlayerAccountReq recordOut) throws BusinessException {
        boolean success = Boolean.FALSE;
        String msg = "玩家转账";
        Result<BigDecimal> updateAccountResult = null;
        PlayerTrade trade = null;
        try {
            String valByKey = dictionaryService.getValByKey("player.inside.transfer.verify");
            String valByKey2 = dictionaryService.getValByKey("player.transfer.mt.tax");
            //转账税率
            BigDecimal transferTax = BigDecimal.ZERO;
            //是否确认
            Boolean transferVerify = Boolean.FALSE;
            //是否外部转账
            Boolean isDeposit = Boolean.FALSE;

            PlayerAccount account = accountService.getPlayerAccountByAddr(recordOut.getAccAddr());
            if (Objects.isNull(account)){
                isDeposit = Boolean.TRUE;
            }

            if (StringUtils.isNotBlank(valByKey)) {
                transferVerify = Boolean.parseBoolean(valByKey);
            }
            if (StringUtils.isNotBlank(valByKey2)) {
                transferTax = BigDecimal.valueOf(Double.parseDouble(valByKey2));
            }
            //内部转账税
            String valByKey4 = dictionaryService.getValByKey("player.inside.transfer.mt.tax");
            BigDecimal insideTransferTax = BigDecimal.ZERO;
            if (StringUtils.isNotBlank(valByKey4)) {
                insideTransferTax = BigDecimal.valueOf(Double.parseDouble(valByKey4));
            }

            recordOut.setInOut(AmountDynType.OUT.name());
            recordOut.setTradeType(TradeType.TRANSFER.getCode());
            recordOut.setTradeDetailType(TradeType.TRANSFER_FROM.getCode());
            String verifyStatus = VerifyStatus.WAIT.name();

            //获取转账账户信息
            PlayerAccount getPlayerAccount = accountService.getPlayerAccount(recordOut.getAccPlayerId());
            recordOut.setAccId(getPlayerAccount.getAccId());
            recordOut.setAccUsdt(recordOut.getAmount());

            //校验提现规则
            if (getPlayerAccount == null) {
                return new Result(success, "没找到账户信息", ReturnStatus.ACCOUNT_EXISTS.getStatus(), null);
            }
            if (recordOut.getAmount() == null) {
                return new Result(success, "转账金额不能为空", ReturnStatus.PARAM_ERROR.getStatus(), null);
            }
            if (StringUtils.isBlank(recordOut.getAccAddr())) {
                return new Result(success, "转账地址不能为空", ReturnStatus.PARAM_ERROR.getStatus(), null);
            }
            Result checkAmount = this.checkAmount(recordOut, getPlayerAccount);
            if (!checkAmount.getSuccess()) {
                return new Result<>(success, checkAmount.getMsg(), checkAmount.getCode(), null);
            }

            //转账账户 出账
            updateAccountResult = this.updatePlayerAccount(recordOut);
            if (updateAccountResult != null) {
                success = updateAccountResult.getSuccess();
            }

            //新增交易记录 出账
            Result<PlayerTrade> createPlayerTradeResult = this.createPlayerTrade(recordOut, updateAccountResult.getData(), msg);

            //新增交易审核 内部转账：审核通过；外部转账：待审核
            TradeVerify tradeVerify = null;
            if (createPlayerTradeResult.getSuccess()) {
               // 是否内部转账
                trade = createPlayerTradeResult.getData();
                if (isDeposit) {
                    //外部转账
                    verifyStatus = VerifyStatus.WAIT.name();
                } else { //内部转账
                    if (transferVerify){
                        verifyStatus = VerifyStatus.WAIT.name();
                    }else{

                        verifyStatus = VerifyStatus.PASS.name();
                    }
                }
                tradeVerify = this.createTradeVerify(recordOut.getAccAddr(),createPlayerTradeResult.getData().getTradeId(),verifyStatus, msg);
            }

            //收款用户
            PlayerAccountResp accountTo = getrAccountByAccAddr(recordOut.getAccAddr());

            //新增交易流水
            if (createPlayerTradeResult != null && createPlayerTradeResult.getSuccess() && tradeVerify != null) {
                BigDecimal usdtSurplus = getPlayerAccount.getAccUsdt().subtract(createPlayerTradeResult.getData().getTradeAmount());
                BigDecimal mtSurplus = getPlayerAccount.getAccMt();

                if (accountTo != null) {
                    mtSurplus = getPlayerAccount.getAccMt().subtract(insideTransferTax);
                    //内部转账
                    if (transferVerify) {
                        this.createTradeDetail(createPlayerTradeResult.getData().getTradePlayerId(), createPlayerTradeResult.getData().getTradeId(),
                                createPlayerTradeResult.getData().getTradeAmount(), usdtSurplus, getPlayerAccount.getAccMt(), null, tradeVerify.getVerifyId(), TradeDetailType.TRANSFER_FREEZE.getCode(), "转账冻结金额");
                        this.createTradeDetail(createPlayerTradeResult.getData().getTradePlayerId(), createPlayerTradeResult.getData().getTradeId(),
                                insideTransferTax, usdtSurplus, mtSurplus, null, tradeVerify.getVerifyId(), TradeDetailType.TRANSFER_FREEZE.getCode(), "转账冻结税金");
                    } else {
                        this.createTradeDetail(createPlayerTradeResult.getData().getTradePlayerId(), createPlayerTradeResult.getData().getTradeId(),
                                createPlayerTradeResult.getData().getTradeAmount(), usdtSurplus, getPlayerAccount.getAccMt(), null, tradeVerify.getVerifyId(), TradeDetailType.TRANSFER_VERIFY.getCode(), "转账扣除金额");
                        this.createTradeDetail(createPlayerTradeResult.getData().getTradePlayerId(), createPlayerTradeResult.getData().getTradeId(),
                                insideTransferTax, usdtSurplus, mtSurplus, null, tradeVerify.getVerifyId(), TradeDetailType.TRANSFER_VERIFY.getCode(), "转账扣除税金");
                    }
                } else {
                    mtSurplus = getPlayerAccount.getAccMt().subtract(transferTax);
                    //外部转账
                    this.createTradeDetail(createPlayerTradeResult.getData().getTradePlayerId(), createPlayerTradeResult.getData().getTradeId(),
                            createPlayerTradeResult.getData().getTradeAmount(), usdtSurplus, getPlayerAccount.getAccMt(), null, tradeVerify.getVerifyId(), TradeDetailType.TRANSFER_FREEZE.getCode(), "转账冻结金额");
                    this.createTradeDetail(createPlayerTradeResult.getData().getTradePlayerId(), createPlayerTradeResult.getData().getTradeId(),
                            transferTax, usdtSurplus, mtSurplus, null, tradeVerify.getVerifyId(), TradeDetailType.TRANSFER_FREEZE.getCode(), "转账冻结税金");
                }

            }

            //转入
            if (accountTo != null) {
                //内部转账不需审核 立即到账
                msg = "玩家内部转账";
                if (success && !transferVerify) {
                    msg = "玩家内部转账成功";
                    trade.setPersonalTax(insideTransferTax);

                    //内部转账 立即到账 转入账户入账
                    PlayerAccountReq recordIn = new PlayerAccountReq();
                    recordIn.setAccId(accountTo.getAccId());
                    recordIn.setAccPlayerId(accountTo.getPlayerId());
                    recordIn.setTradeType(TradeType.TRANSFER.getCode());
                    recordIn.setTradeDetailType(TradeType.TRANSFER_TO.getCode());
                    recordIn.setAccUsdt(recordOut.getAmount());
                    recordIn.setAccUsdtAvailable(recordOut.getAmount());
                    Result<BigDecimal> transferInResult = this.updatePlayerAccount(recordIn);

                    BigDecimal usdtSurplus = accountTo.getAccUsdt().add(createPlayerTradeResult.getData().getTradeAmount());
                    //新增交易记录 入账
                    recordIn.setAccId(accountTo.getAccId());
                    this.createPlayerTrade(recordIn, transferInResult.getData(), transferInResult.getMsg());

                    this.createTradeDetail(recordIn.getAccPlayerId(), createPlayerTradeResult.getData().getTradeId(),
                            createPlayerTradeResult.getData().getTradeAmount(), usdtSurplus, accountTo.getAccMt(), null, null, TradeType.TRANSFER_TO.getCode(), "转账进账");
                }
            } else {
                //外部转账 相当于提现 待审核  TODO
                msg = "玩家外部转账,待审核";
            }
        } catch (Exception e) {
            logger.error("转账异常", e);
            throw new BusinessException("转账异常");
        }
        return new Result<>(success, msg, trade);
    }


    /**
     * 判断是内部还是外部地址
     *
     * @param accAddr 玩家账户地址
     * @return TRUE：内部地址，FALSE外部地址
     */
    @LcnTransaction
    @Transactional
    @Override
    public PlayerAccountResp getrAccountByAccAddr(String accAddr) throws BusinessException {
        PlayerAccountReq record = new PlayerAccountReq();
        record.setAccAddr(accAddr);
        PlayerAccountResp playerAccount = accountService.getPlayerAccount(record);
        return playerAccount;
    }


    @LcnTransaction
    @Transactional
    @Override
    public void createTradeDetail(String playerId, Integer tradeId, BigDecimal tradeAmount, BigDecimal usdtSurplus,
                                  BigDecimal mtSurplus, Integer orderId, Integer verifyId,
                                  String tradeDetailType, String descr) throws BusinessException {
        TradeDetail tradeDetail = new TradeDetail();
        tradeDetail.setTradeId(tradeId);
        tradeDetail.setTradeAmount(tradeAmount);
        tradeDetail.setMtSurplus(mtSurplus);
        tradeDetail.setUsdtSurplus(usdtSurplus);
        tradeDetail.setDescr(descr);
        tradeDetail.setPlayerId(playerId);
        tradeDetail.setTradeDetailType(tradeDetailType);
        tradeDetail.setOrderId(orderId);
        if (verifyId != null) {
            tradeDetail.setVerifyId(verifyId);
            tradeDetail.setVerifyTime(new Date());
        }
        tradeDetailService.insert(tradeDetail);
    }


    /**
     * 更新账户数据
     * 充值、提现、转账
     *
     * @param record
     * @return 交易金额
     */
    @LcnTransaction
    @Transactional
    @Override
    public Result<BigDecimal> updatePlayerAccount(PlayerAccountReq record) throws BusinessException {
        boolean success = Boolean.FALSE;
        String tradeType = null;
        String desc = "失败！";
        //转账是否需要审核
        String valByKey = dictionaryService.getValByKey("player.inside.transfer.verify");
        BigDecimal transferTax = BigDecimal.ZERO;
        Boolean transferVerify = Boolean.FALSE;
        if (StringUtils.isNotBlank(valByKey)) {
            transferVerify = Boolean.parseBoolean(valByKey);
        }
        //转账税
        String valByKey2 = dictionaryService.getValByKey("player.transfer.mt.tax");
        if (StringUtils.isNotBlank(valByKey2)) {
            transferTax = BigDecimal.valueOf(Double.parseDouble(valByKey2));
        }
        //提现税
        String valByKey3 = dictionaryService.getValByKey("player.withdraw.mt.tax");
        BigDecimal withdrawTax = BigDecimal.ZERO;
        if (StringUtils.isNotBlank(valByKey3)) {
            withdrawTax = BigDecimal.valueOf(Double.parseDouble(valByKey3));
        }
        //内部转账税
        String valByKey4 = dictionaryService.getValByKey("player.inside.transfer.mt.tax");
        BigDecimal insideTransferTax = BigDecimal.ZERO;
        if (StringUtils.isNotBlank(valByKey4)) {
            insideTransferTax = BigDecimal.valueOf(Double.parseDouble(valByKey4));
        }

        PlayerAccount getPlayerAccount = accountService.getPlayerAccount(record.getAccPlayerId());

        //更新PlayerAccount参数
        PlayerAccount accountUpdate = new PlayerAccount();
        accountUpdate.setAccId(getPlayerAccount.getAccId());
        accountUpdate.setAccPlayerId(record.getAccPlayerId());
        //accountUpdate.setVersion(getPlayerAccount.getVersion()==null?0:getPlayerAccount.getVersion());

        BigDecimal accMt = BigDecimal.ZERO;
        BigDecimal accMtAvailable = BigDecimal.ZERO;
        BigDecimal accMtFreeze = BigDecimal.ZERO;

        BigDecimal accUsdt = BigDecimal.ZERO;
        BigDecimal accUsdtAvailable = BigDecimal.ZERO;
        BigDecimal accUsdtFreeze = BigDecimal.ZERO;

        BigDecimal tradeAmount = BigDecimal.ZERO;
        //充值
        if (record.getTradeType().equalsIgnoreCase(TradeType.RECHARGE.name())) {
            tradeType = TradeType.RECHARGE.getDesc();
            //            //账户金额加上充值金额
            if (record.getAmountType().equalsIgnoreCase(AmountType.MT.name())) {
                tradeAmount = record.getAccMt();
                accMt = getPlayerAccount.getAccMt().add(tradeAmount);
                accMtAvailable = getPlayerAccount.getAccMtAvailable().add(tradeAmount);
                accMtFreeze = getPlayerAccount.getAccMtFreeze();
            } else if (record.getAmountType().equalsIgnoreCase(AmountType.USDT.name())) {
                tradeAmount = record.getAccUsdt();
                accUsdt = getPlayerAccount.getAccUsdt().add(tradeAmount);
                accUsdtAvailable = getPlayerAccount.getAccUsdtAvailable().add(tradeAmount);
                accUsdtFreeze = getPlayerAccount.getAccUsdtFreeze();
            }
        }
        //提现
        if (record.getTradeType().equalsIgnoreCase(TradeType.WITHDRAW.name())) {
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
        if (TradeType.TRANSFER.name().equalsIgnoreCase(record.getTradeType())) {
            tradeAmount = record.getAccUsdt();
            tradeType = TradeType.TRANSFER.getDesc();
            if (TradeType.TRANSFER_FROM.name().equalsIgnoreCase(record.getTradeDetailType())) {
                //转账转出 账户金额减去转账金额
                PlayerAccountResp accountByAccAddr = this.getrAccountByAccAddr(record.getAccAddr());
                if (StringUtils.isNotBlank(record.getAccAddr()) && accountByAccAddr != null) {
                    //内部转账
                    if (transferVerify) {
                        //要审核 冻结
                        accMt = getPlayerAccount.getAccMt().subtract(insideTransferTax);
                        accMtAvailable = getPlayerAccount.getAccMtAvailable().subtract(insideTransferTax);
                        accMtFreeze = getPlayerAccount.getAccMtFreeze().add(insideTransferTax);
                        accUsdt = getPlayerAccount.getAccUsdt().subtract(tradeAmount);
                        accUsdtAvailable = getPlayerAccount.getAccUsdtAvailable().subtract(tradeAmount);
                        accUsdtFreeze = getPlayerAccount.getAccUsdtFreeze().add(tradeAmount);
                    } else {
                        //不用审核 立即到账
                        accMt = getPlayerAccount.getAccMt().subtract(insideTransferTax);
                        accMtAvailable = getPlayerAccount.getAccMtAvailable().subtract(insideTransferTax);
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
        if (updateResult > 0) {
            success = Boolean.TRUE;
            desc = tradeType + "成功！";
        }

        return new Result<>(success, desc, tradeAmount);
    }

    /**
     * 新增交易记录
     *
     * @param record
     */
    @LcnTransaction
    @Transactional
    @Override
    public Result<PlayerTrade> createPlayerTrade(PlayerAccountReq record, BigDecimal tradeAmount, String desc) throws BusinessException {
        String valByKey = dictionaryService.getValByKey("player.inside.transfer.verify");
        String valByKey2 = dictionaryService.getValByKey("player.transfer.mt.tax");
        BigDecimal transferTax = BigDecimal.ZERO;
        Boolean transferVerify = Boolean.FALSE;
        if (StringUtils.isNotBlank(valByKey)) {
            transferVerify = Boolean.parseBoolean(valByKey);
        }
        if (StringUtils.isNotBlank(valByKey2)) {
            transferTax = BigDecimal.valueOf(Double.parseDouble(valByKey2));
        }
        String valByKey3 = dictionaryService.getValByKey("player.withdraw.mt.tax");
        BigDecimal withdrawTax = BigDecimal.ZERO;
        if (StringUtils.isNotBlank(valByKey3)) {
            withdrawTax = BigDecimal.valueOf(Double.parseDouble(valByKey3));
        }
        //内部转账税
        String valByKey4 = dictionaryService.getValByKey("player.inside.transfer.mt.tax");
        BigDecimal insideTransferTax = BigDecimal.ZERO;
        if (StringUtils.isNotBlank(valByKey4)) {
            insideTransferTax = BigDecimal.valueOf(Double.parseDouble(valByKey4));
        }
        //收款用户
        PlayerAccountResp accountTo = getrAccountByAccAddr(record.getAccAddr());

        PlayerTrade tradeReq = new PlayerTrade();
        tradeReq.setTradeType(record.getTradeType());
        tradeReq.setTradeDesc(desc);
        tradeReq.setTradeAccId(Integer.parseInt(String.valueOf(record.getAccId())));
        tradeReq.setTradePlayerId(record.getAccPlayerId());
        tradeReq.setTradeAmount(tradeAmount);
        if (TradeType.RECHARGE.getCode().equalsIgnoreCase(record.getTradeType())) {
            tradeReq.setTradeStatus(TradeStatus.IN.getCode());
            tradeReq.setInOutStatus(AmountDynType.IN.getCode());
        } else if (TradeType.WITHDRAW.getCode().equalsIgnoreCase(record.getTradeType())) {
            tradeReq.setPersonalTax(withdrawTax);
            tradeReq.setTradeStatus(TradeStatus.FREEZE.getCode());
            tradeReq.setInOutStatus(AmountDynType.OUT.getCode());
        } else if (TradeType.TRANSFER.getCode().equalsIgnoreCase(record.getTradeType())) {
            if (TradeType.TRANSFER_FROM.getCode().equalsIgnoreCase(record.getTradeDetailType())) {
                if (accountTo == null) {
                    tradeReq.setPersonalTax(transferTax);
                } else {
                    tradeReq.setPersonalTax(insideTransferTax);
                }
                tradeReq.setInOutStatus(AmountDynType.OUT.getCode());
                if (transferVerify) {
                    tradeReq.setTradeStatus(TradeStatus.FREEZE.getCode());
                } else {
                    tradeReq.setTradeStatus(TradeStatus.OUT.getCode());
                }
            } else {
                tradeReq.setTradeStatus(TradeStatus.IN.getCode());
                tradeReq.setInOutStatus(AmountDynType.IN.getCode());
            }
        }

        //生成交易记录
        PlayerTrade insertPlayerTrade = insertPlayerTrade(tradeReq);
        boolean success = Boolean.FALSE;
        if (insertPlayerTrade != null) {
            success = Boolean.TRUE;
        }
        return new Result<>(success, desc, insertPlayerTrade);
    }


    /**
     * 新增交易审核
     * 待审核、审核通过
     *
     * @param tradeId
     * @param verifyStatus
     * @param verifyDesc
     */
    @LcnTransaction
    @Transactional
    @Override
    public TradeVerify createTradeVerify(String toAddr,Integer tradeId, String verifyStatus, String verifyDesc) throws BusinessException {
        TradeVerify verifyReq = new TradeVerify();
        verifyReq.setVerifyToAddress(toAddr);
        verifyReq.setVerifyDesc(verifyDesc);
        verifyReq.setVerifyStatus(verifyStatus);
        verifyReq.setVerifyTradeId(tradeId);
        return verifyService.insertTradeVerify(verifyReq);
    }

}
