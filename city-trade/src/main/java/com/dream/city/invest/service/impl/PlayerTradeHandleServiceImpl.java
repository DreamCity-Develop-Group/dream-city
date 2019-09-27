package com.dream.city.invest.service.impl;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.PlayerEarning;
import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.entity.TradeVerify;
import com.dream.city.base.model.enu.*;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.req.TradeVerifReq;
import com.dream.city.invest.service.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlayerTradeHandleServiceImpl implements PlayerTradeHandleService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AccountService accountService;
    @Autowired
    PlayerTradeService tradeService;
    @Autowired
    TradeVerifyService verifyService;
    @Autowired
    EarningService earningService;



    @Override
    @Transactional
    public Result<Boolean> playerRecharge(PlayerAccountReq record) {
        boolean success = Boolean.FALSE;
        String msg = null;
        record.setTradeType(AmountDynType.in.name());
        record.setTradeType(TradeAmountType.RECHARGE.name());
        Result<BigDecimal> updateAccountResult = null;
        try {
            //充值
            updateAccountResult = updatePlayerAccount(record);
            success = updateAccountResult.getSuccess();
            msg = updateAccountResult.getMsg();

            //新增交易记录
            record.setAccId(record.getAccId());
            createPlayerTrade(record, updateAccountResult.getData(),updateAccountResult.getMsg());
        }catch (Exception e){
            logger.error("充值异常",e);
            throw new BusinessException("充值异常");
        }

        if (success){
            // todo
            msg = "您的充值已到账，金额：" + updateAccountResult.getData();

        }
        return new Result<Boolean>(success,msg,success);
    }

    @Override
    @Transactional
    public Result<Boolean> playerWithdraw(PlayerAccountReq record) {
        boolean success = Boolean.FALSE;
        String msg = "玩家提现";
        record.setTradeType(AmountDynType.out.name());
        record.setTradeType(TradeAmountType.WITHDRAW.name());
        Result<BigDecimal> updateAccountResult = null;
        try {
            //校验提现规则 todo

            //提现 冻结金额
            updateAccountResult = updatePlayerAccount(record);
            success = updateAccountResult.getSuccess();
            msg = updateAccountResult.getMsg();

            //新增交易记录
            record.setAccId(record.getAccId());
            Result<PlayerTrade> createPlayerTradeResult = createPlayerTrade(record,updateAccountResult.getData(),updateAccountResult.getMsg());

            //新增交易审核 待审核
            if (createPlayerTradeResult.getSuccess()){
                createTradeVerify(createPlayerTradeResult.getData().getTradeId(),updateAccountResult.getData(),
                        VerifyStatus.wait.name(),updateAccountResult.getMsg());
            }
        }catch (Exception e){
            logger.error("提现异常",e);
            throw new BusinessException("提现异常");
        }
        return new Result<Boolean>(success,msg,success);
    }


    @Override
    @Transactional
    public Result<Boolean> playerTransfer(PlayerAccountReq recordOut) {
        boolean success = Boolean.FALSE;
        String msg = "玩家转账";
        recordOut.setTradeType(TradeAmountType.TRANSFER.name());
        Result<BigDecimal> updateAccountResult = null;
        try {
            String verifyStatus = VerifyStatus.wait.name();
            //获取转账账户信息
            PlayerAccount accountOut = new PlayerAccount();
            accountOut.setAccPlayerId(recordOut.getAccPlayerId());
            PlayerAccount playerAccountOut = accountService.getPlayerAccount(accountOut);
            recordOut.setAccId(playerAccountOut.getAccId());

            //转账账户 出账
            recordOut.setTradeType(AmountDynType.out.name());
            updateAccountResult = updatePlayerAccount(recordOut);
            success = updateAccountResult.getSuccess();
            if (isInsideAccAddr(recordOut.getAccAddr())){
                //内部转账 立即到账
                msg = "玩家内部转账";
                verifyStatus = VerifyStatus.pass.name();
                //转账账户出账成功就立即到账
                if (success){
                    msg = "玩家内部转账成功";
                    //获取转入账户信息
                    PlayerAccount accountIn = new PlayerAccount();
                    accountIn.setAccPlayerId(recordOut.getFriendId());
                    PlayerAccount playerAccountIn = accountService.getPlayerAccount(accountIn);
                    //内部转账 立即到账 转入账户入账
                    PlayerAccountReq recordIn = new PlayerAccountReq();
                    recordIn.setAccId(playerAccountIn.getAccId());
                    recordIn.setAccPlayerId(recordOut.getFriendId());
                    recordIn.setTradeType(AmountDynType.in.name());
                    Result<BigDecimal> transferInResult = updatePlayerAccount(recordIn);

                    //新增交易记录 入账
                    recordIn.setAccId(playerAccountIn.getAccId());
                    createPlayerTrade(recordIn,transferInResult.getData(), transferInResult.getMsg());
                }
            }else {
                //外部转账 相当于提现 待审核
                msg = "玩家外部转账,待审核";
            }

            //新增交易记录 出账
            Result<PlayerTrade> createPlayerTradeResult = createPlayerTrade(recordOut,updateAccountResult.getData(), msg);

            //新增交易审核 内部转账：审核通过；外部转账：待审核
            if (createPlayerTradeResult.getSuccess() && !isInsideAccAddr(recordOut.getAccAddr())){
                createTradeVerify(createPlayerTradeResult.getData().getTradeId(),updateAccountResult.getData(),
                        verifyStatus, msg);
            }
        }catch (Exception e){
            logger.error("转账异常",e);
            throw new BusinessException("转账异常");
        }
        return new Result<Boolean>(success,msg,success);
    }


    @Override
    @Transactional
    public Result<Boolean> withdrawVerify(TradeVerifReq record) {
        boolean success = Boolean.FALSE;
        String msg = "";
        if (record.getEarnId() == null || (record.getEarnId() != null &&record.getEarnId() < 0)){
            msg = "收益id不能为空";
            return new Result<Boolean>(success,"收益id不能为空");
        }
        //提现收益
        PlayerEarning earning = earningService.getEarning(record.getEarnId());

        //玩家账户信息
        PlayerAccount playerAccount = null;
        PlayerAccount accountReq = new PlayerAccount();
        accountReq.setAccPlayerId(earning.getEarnPlayerId());
        playerAccount = accountService.getPlayerAccount(accountReq);
        //校验金额
        if (playerAccount.getAccUsdtFreeze().compareTo(earning.getEarnMax()) < 0){
            if (playerAccount.getAccUsdtAvailable().compareTo(earning.getEarnMax()) < 0){
                msg = "USDT不足";
                return new Result<Boolean>(success,"USDT不足");
            }
        }
        //校验税金
        if (playerAccount.getAccMtFreeze().compareTo(earning.getEarnTax()) < 0){
            if (playerAccount.getAccMtAvailable().compareTo(earning.getEarnTax()) < 0){
                msg = "MT不足";
                return new Result<Boolean>(success,"MT不足");
            }
        }

        //审核交易 修改审核状态
        int updateTradeVerify = 0;
        if(StringUtils.isBlank(msg)){
            TradeVerify verifyReq = new TradeVerify();
            verifyReq.setVerifyStatus(record.getVerifyStatus());
            verifyReq.setVerifyEmpId(record.getEmpId());
            verifyReq.setVerifyDesc(record.getVerifyDesc());
            verifyReq.setVerifyTradeId(record.getTradeId());
            updateTradeVerify = verifyService.updateTradeVerify(verifyReq);
        }

        if (updateTradeVerify > 0){
            if (record.getVerifyStatus().equalsIgnoreCase(VerifyStatus.pass.name())){
                //审核通过
                //玩家账户扣除金额 扣冻结金额
                PlayerAccount updateAccountReq = new PlayerAccount();
                updateAccountReq.setAccId(playerAccount.getAccId());
                updateAccountReq.setAccPlayerId(playerAccount.getAccPlayerId());
                updateAccountReq.setAccUsdtFreeze(playerAccount.getAccUsdtFreeze().subtract(earning.getEarnMax()));
                int updatePlayerAccount = 0;
                try {
                    //更新玩家账户
                    updatePlayerAccount = accountService.updatePlayerAccount(updateAccountReq);
                }catch (Exception e){
                    msg = "审核更新usdt账户异常";
                    logger.error(msg,e);
                    throw new BusinessException("审核更新usdt账户异常");
                }

                //玩家账户扣除金额流水
                Result<PlayerTrade> createPlayerTradeResult = null;
                PlayerTrade createPlayerTrade = null;
                PlayerAccountReq createPlayerTradeReq = new PlayerAccountReq();
                if (updatePlayerAccount > 0){
                    createPlayerTradeReq.setAccId(playerAccount.getAccId());
                    createPlayerTradeReq.setAccPlayerId(playerAccount.getAccPlayerId());
                    createPlayerTradeReq.setTradeType(TradeAmountType.WITHDRAW.name());
                    createPlayerTradeReq.setTradeType(AmountDynType.out.name());
                    try {
                        //新增流水
                        createPlayerTradeResult = this.createPlayerTrade(createPlayerTradeReq, earning.getEarnMax(), "审核扣除冻结usdt");
                        if (createPlayerTradeResult != null && createPlayerTradeResult.getSuccess()){
                            createPlayerTrade = createPlayerTradeResult.getData();
                        }
                    }catch (Exception e){
                        msg = "审核新增流水异常";
                        logger.error(msg,e);
                        throw new BusinessException("审核新增流水异常");
                    }
                }

                //更新玩家收益为已经提取
                int updateEarning = 0;
                if (createPlayerTradeResult != null && createPlayerTradeResult.getSuccess() && createPlayerTrade != null){
                    earning.setIsWithdrew("Y");
                    updateEarning = earningService.updateEarningById(earning);
                }else {
                    msg = "审核新增流水失败";
                }

                if(updateEarning > 0){
                    //玩家账户扣除税金 扣冻结mt税金
                    createPlayerTradeReq = new PlayerAccountReq();
                    updateAccountReq.setAccId(playerAccount.getAccId());
                    updateAccountReq.setAccPlayerId(playerAccount.getAccPlayerId());
                    updateAccountReq.setAccMtFreeze(playerAccount.getAccMtFreeze().subtract(earning.getEarnTax()));
                    updatePlayerAccount = 0;
                    try {
                        //更新玩家账户
                        updatePlayerAccount = accountService.updatePlayerAccount(updateAccountReq);
                    }catch (Exception e){
                        msg = "审核更新玩家mt账户异常";
                        logger.error(msg,e);
                        throw new BusinessException("审核更新玩家mt账户异常");
                    }

                    //玩家账户扣除税金流水
                    if (updatePlayerAccount > 0){
                        createPlayerTradeReq = new PlayerAccountReq();
                        createPlayerTradeReq.setAccId(playerAccount.getAccId());
                        createPlayerTradeReq.setAccPlayerId(playerAccount.getAccPlayerId());
                        createPlayerTradeReq.setTradeAmountType(TradeAmountType.USDT_DTINVEST_TAX.name());
                        createPlayerTradeReq.setTradeType(AmountDynType.out.name());
                        createPlayerTrade = null;
                        try {
                            //新增扣除税金流水
                            createPlayerTradeResult = this.createPlayerTrade(createPlayerTradeReq, earning.getEarnTax(), "审核扣除mt税金");
                            if (createPlayerTradeResult.getSuccess()){
                                createPlayerTrade = createPlayerTradeResult.getData();
                            }
                        }catch (Exception e){
                            msg = "审核新增扣除税金流水异常";
                            logger.error("msg",e);
                            throw new BusinessException("审核新增扣除税金流水异常");
                        }
                    }else {
                        msg = "审核更新玩家mt账户失败";
                    }

                    //获取平台账户
                    List<PlayerAccount> platformAccountList = null;
                    PlayerAccount platformAccount = null;
                    if (createPlayerTradeResult.getSuccess() && createPlayerTrade != null){
                        platformAccountList = accountService.getPlatformAccounts(null);
                        if (!CollectionUtils.isEmpty(platformAccountList)){
                            platformAccount = platformAccountList.get(0);
                        }
                        if (platformAccount != null){
                            //把玩家账户扣除税金 加到平台mt账户
                            platformAccount.setAccMt(platformAccount.getAccMtAvailable().add(earning.getEarnTax()));
                            platformAccount.setAccMtAvailable(platformAccount.getAccMtAvailable().add(earning.getEarnTax()));
                            updatePlayerAccount = 0;
                            try {
                                //更新平台mt账户
                                updatePlayerAccount = accountService.updatePlayerAccount(platformAccount);
                            }catch (Exception e){
                                msg = "审核平台mt账户异常";
                                logger.error(msg,e);
                                throw new BusinessException("审核更新平台mt账户异常");
                            }
                        }
                    }

                    //平台mt账户增加税金
                    if (platformAccount != null && updatePlayerAccount > 0){
                        createPlayerTradeReq = new PlayerAccountReq();
                        createPlayerTradeReq.setAccId(platformAccount.getAccId());
                        createPlayerTradeReq.setAccPlayerId(platformAccount.getAccPlayerId());
                        createPlayerTradeReq.setTradeAmountType(TradeAmountType.USDT_DTINVEST_TAX.name());
                        createPlayerTradeReq.setTradeType(AmountDynType.in.name());
                        Result<PlayerTrade> createPlatformATradeResult = null;
                        createPlayerTrade = null;
                        try {
                            //新增平台mt账户增加税金流水
                            createPlatformATradeResult = this.createPlayerTrade(createPlayerTradeReq, earning.getEarnTax(), "平台mt账户增加税金");
                            if (createPlatformATradeResult.getSuccess()){
                                createPlayerTrade = createPlatformATradeResult.getData();
                            }
                        }catch (Exception e){
                            msg = "审核新增扣除税金流水异常";
                            logger.error(msg,e);
                            throw new BusinessException("审核新增扣除税金流水异常");
                        }
                        if (!createPlatformATradeResult.getSuccess() && createPlayerTrade == null){
                            msg = "审核新增扣除税金流水失败";
                        }else {
                            success = Boolean.TRUE;
                            msg = "审核成功！";
                        }
                    }
                }else {
                    msg = "更新玩家收益为已经提取";
                }
            }else if (record.getVerifyStatus().equalsIgnoreCase(VerifyStatus.notpass.name())){
                //审核不通过
                success = Boolean.TRUE;
                msg = "审核成功！";
            }
        }else {
            msg = "修改审核状态失败！";
        }
        return new Result<Boolean>(success,msg,success);
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
        PlayerAccount getPlayerAccountReq = new PlayerAccount();
        getPlayerAccountReq.setAccPlayerId(record.getAccPlayerId());
        PlayerAccount getPlayerAccount = accountService.getPlayerAccount(getPlayerAccountReq);

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
        if (record.getTradeType().equalsIgnoreCase(TradeAmountType.RECHARGE.name())){
            tradeType = TradeAmountType.RECHARGE.getDesc();
            //            //账户金额加上充值金额
            if (record.getTradeType().equalsIgnoreCase(AmountType.mt.name())){
                tradeAmount = record.getAccMt();
                accMt = getPlayerAccount.getAccMt().add(tradeAmount);
                accMtAvailable = getPlayerAccount.getAccMtAvailable().add(tradeAmount);
            }else if (record.getTradeType().equalsIgnoreCase(AmountType.usdt.name())){
                tradeAmount = record.getAccUsdt();
                accUsdt = getPlayerAccount.getAccUsdt().add(tradeAmount);
                accUsdtAvailable = getPlayerAccount.getAccUsdtAvailable().add(tradeAmount);
            }
        }
        //提现
        if (record.getTradeType().equalsIgnoreCase(TradeAmountType.WITHDRAW.name())){
            tradeType = TradeAmountType.WITHDRAW.getDesc();
            //账户金额减去提现金额
            if (record.getTradeType().equalsIgnoreCase(AmountType.mt.name())){
                tradeAmount = record.getAccMt();
                accMt = getPlayerAccount.getAccMt();
                accMtAvailable = getPlayerAccount.getAccMtAvailable().subtract(tradeAmount);
                accMtFreeze = tradeAmount;
            }else if (record.getTradeType().equalsIgnoreCase(AmountType.usdt.name())){
                tradeAmount = record.getAccUsdt();
                accUsdt = getPlayerAccount.getAccUsdt();
                accUsdtAvailable = getPlayerAccount.getAccUsdtAvailable().subtract(tradeAmount);
                accUsdtFreeze = tradeAmount;
            }
        }
        //转账
        if (record.getTradeType().equalsIgnoreCase(TradeAmountType.TRANSFER.name())){
            tradeType = TradeAmountType.TRANSFER.getDesc();
            if (record.getTradeType().equalsIgnoreCase(AmountDynType.out.name())){
                //转账转出 账户金额减去转账金额
                if (isInsideAccAddr(record.getAccAddr())){
                    //内部转账 立即到账
                    if (record.getTradeType().equalsIgnoreCase(AmountType.mt.name())){
                        tradeAmount = record.getAccMt();
                        accMt = getPlayerAccount.getAccMt().subtract(tradeAmount);
                        accMtAvailable = getPlayerAccount.getAccMtAvailable().subtract(tradeAmount);
                    }else if (record.getTradeType().equalsIgnoreCase(AmountType.usdt.name())){
                        tradeAmount = record.getAccUsdt();
                        accUsdt = getPlayerAccount.getAccUsdt().subtract(tradeAmount);
                        accUsdtAvailable = getPlayerAccount.getAccUsdtAvailable().subtract(tradeAmount);
                    }
                } else {
                    if (record.getTradeType().equalsIgnoreCase(AmountType.mt.name())){
                        tradeAmount = record.getAccMt();
                        accMt = getPlayerAccount.getAccMt();
                        accMtAvailable = getPlayerAccount.getAccMtAvailable().subtract(tradeAmount);
                        accMtFreeze = tradeAmount;
                    }else if (record.getTradeType().equalsIgnoreCase(AmountType.usdt.name())){
                        tradeAmount = record.getAccUsdt();
                        accUsdt = getPlayerAccount.getAccUsdt();
                        accUsdtAvailable = getPlayerAccount.getAccUsdtAvailable().subtract(tradeAmount);
                        accUsdtFreeze = tradeAmount;
                    }
                }
            } else {
                //转账转入 账户金额加上转账金额
                if (record.getTradeType().equalsIgnoreCase(AmountType.mt.name())){
                    tradeAmount = record.getAccMt();
                    accMt = getPlayerAccount.getAccMt().add(tradeAmount);
                    accMtAvailable = accMt.add(tradeAmount);
                }else if (record.getTradeType().equalsIgnoreCase(AmountType.usdt.name())){
                    tradeAmount = record.getAccUsdt();
                    accUsdt = getPlayerAccount.getAccUsdt().add(tradeAmount);
                    accUsdtAvailable = getPlayerAccount.getAccUsdtAvailable().add(tradeAmount);
                }
            }
        }
        accountUpdate.setAccMt(accMt);
        accountUpdate.setAccUsdtAvailable(accMtAvailable);
        accountUpdate.setAccMtFreeze(accMtFreeze);
        accountUpdate.setAccUsdt(accUsdt);
        accountUpdate.setAccUsdtAvailable(accUsdtAvailable);
        accountUpdate.setAccUsdtFreeze(accUsdtFreeze);
        desc = tradeType + "失败！";

        //更新PlayerAccount
        int updateResult = accountService.updatePlayerAccount(accountUpdate);
        if (updateResult > 0){
            success = Boolean.TRUE;
            desc = tradeType+"成功！";
        }

        return new Result(success,desc,tradeAmount);
    }

    /**
     * 新增交易记录
     * @param record
     */
    private Result<PlayerTrade> createPlayerTrade(PlayerAccountReq record,BigDecimal tradeAmount,String desc) throws Exception{
        PlayerTrade tradeReq = new PlayerTrade();
        tradeReq.setTradeType(record.getTradeType());
        tradeReq.setTradeAmountType(record.getTradeType());
        tradeReq.setTradeDesc(desc);
        tradeReq.setTradeAccId(record.getAccId());
        tradeReq.setTradePlayerId(record.getAccPlayerId());
        tradeReq.setTradeAmount(tradeAmount);
        //生成交易记录
        PlayerTrade insertPlayerTrade = tradeService.insertPlayerTrade(tradeReq);
        boolean success = Boolean.FALSE;
        if (insertPlayerTrade != null){
            success = Boolean.TRUE;
        }
        return new Result(success,desc,insertPlayerTrade);
    }


    /**
     * 新增交易审核
     * 待审核、审核通过
     * @param tradeId
     * @param verifyAmount
     * @param verifyStatus
     * @param verifyDesc
     */
    private void createTradeVerify(Integer tradeId,BigDecimal verifyAmount,String verifyStatus,String verifyDesc){
        TradeVerify verifyReq = new TradeVerify();
        verifyReq.setVerifyAmount(verifyAmount);
        verifyReq.setVerifyDesc(verifyDesc);
        verifyReq.setVerifyStatus(verifyStatus);
        verifyReq.setVerifyTradeId(tradeId);

        verifyService.insertTradeVerify(verifyReq);
    }

}
