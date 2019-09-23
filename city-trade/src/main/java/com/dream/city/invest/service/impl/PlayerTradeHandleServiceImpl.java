package com.dream.city.invest.service.impl;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.entity.TradeVerify;
import com.dream.city.base.model.enu.AmountDynType;
import com.dream.city.base.model.enu.AmountType;
import com.dream.city.base.model.enu.TradeType;
import com.dream.city.base.model.enu.VerifyStatus;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.invest.service.PlayerTradeService;
import com.dream.city.invest.service.AccountService;
import com.dream.city.invest.service.PlayerTradeHandleService;
import com.dream.city.invest.service.TradeVerifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PlayerTradeHandleServiceImpl implements PlayerTradeHandleService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AccountService accountService;
    @Autowired
    PlayerTradeService tradeService;
    @Autowired
    TradeVerifyService verifyService;



    @Override
    @Transactional
    public Result playerRecharge(PlayerAccountReq record) {
        boolean success = Boolean.FALSE;
        String msg = null;
        record.setAmountDynType(AmountDynType.in.name());
        record.setTradeType(TradeType.recharge.name());
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
        }

        if (success){
            // todo
            msg = "您的充值已到账，金额：" + updateAccountResult.getData();

        }
        return new Result(success,msg,success);
    }

    @Override
    public Result playerWithdraw(PlayerAccountReq record) {
        boolean success = Boolean.FALSE;
        String msg = null;
        record.setAmountDynType(AmountDynType.out.name());
        record.setTradeType(TradeType.withdraw.name());
        Result<BigDecimal> updateAccountResult = null;
        try {
            //提现 冻结金额
            updateAccountResult = updatePlayerAccount(record);
            success = updateAccountResult.getSuccess();
            msg = updateAccountResult.getMsg();

            //新增交易记录
            record.setAccId(record.getAccId());
            Result<PlayerTrade> createPlayerTradeResult = createPlayerTrade(record,
                    updateAccountResult.getData(),updateAccountResult.getMsg());

            //新增交易审核
            if (createPlayerTradeResult.getSuccess()){
                createTradeVerify(createPlayerTradeResult.getData().getTradeId(),updateAccountResult.getData(),
                        VerifyStatus.wait.name(),updateAccountResult.getMsg());
            }

            String accAddr = record.getAccAddr();
            if (isInsideAccAddr(accAddr)){
                //内部提现 todo

            } else {
                //外部提现 todo

            }
        }catch (Exception e){
            logger.error("提现异常",e);
        }
        return new Result(success,msg,success);
    }


    @Override
    public Result playerTransfer(PlayerAccountReq record) {
        boolean success = Boolean.FALSE;
        record.setAmountDynType(AmountDynType.out.name());
        record.setTradeType(TradeType.transfer.name());
        String accAddr = record.getAccAddr();
        Result<BigDecimal> updateAccountResult = null;
        try {
            //转账 冻结金额
            updateAccountResult = updatePlayerAccount(record);
            success = updateAccountResult.getSuccess();

            //新增交易记录
            record.setAccId(record.getAccId());
            Result<PlayerTrade> createPlayerTradeResult = createPlayerTrade(record,
                    updateAccountResult.getData(), updateAccountResult.getMsg());

            String verifyStatus = VerifyStatus.wait.name();
            if (isInsideAccAddr(accAddr)){
                //内部转账 立即到账
                verifyStatus = VerifyStatus.pass.name();
            }else {
                //外部转账 相当于体现 TODO

            }

            //新增交易审核
            if (createPlayerTradeResult.getSuccess()){
                createTradeVerify(createPlayerTradeResult.getData().getTradeId(),updateAccountResult.getData(),
                        verifyStatus,updateAccountResult.getMsg());
            }
        }catch (Exception e){
            logger.error("转账异常",e);
        }
        return new Result(success,updateAccountResult.getMsg(),success);
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
        if (record.getTradeType().equalsIgnoreCase(TradeType.recharge.name())){
            tradeType = TradeType.recharge.getDesc();
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
        if (record.getTradeType().equalsIgnoreCase(TradeType.withdraw.name())){
            tradeType = TradeType.withdraw.getDesc();
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
        if (record.getTradeType().equalsIgnoreCase(TradeType.transfer.name())){
            tradeType = TradeType.transfer.getDesc();
            if (record.getAmountDynType().equalsIgnoreCase(AmountDynType.out.name())){
                //转账转出 账户金额减去转账金额
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
        tradeReq.setTradeType(record.getAmountDynType());
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
