package com.dream.city.invest.service.impl;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.PlayerTrade;
import com.dream.city.base.model.enu.AmountDynType;
import com.dream.city.base.model.enu.AmountType;
import com.dream.city.base.model.enu.TradeType;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.invest.service.TradeService;
import com.dream.city.invest.service.AccountService;
import com.dream.city.invest.service.PlayerTradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PlayerTradeServiceImpl implements PlayerTradeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AccountService accountService;
    @Autowired
    TradeService tradeService;



    @Override
    @Transactional
    public Result playerRecharge(PlayerAccountReq record) {
        boolean success = Boolean.FALSE;
        record.setAmountDynType(AmountDynType.in.name());
        record.setTradeType(TradeType.recharge.name());
        Result<PlayerAccount> updateAccount = null;
        try {
            //充值
            updateAccount = updatePlayerAccount(record);
            success = updateAccount.getSuccess();
        }catch (Exception e){
            logger.error("充值异常",e);
        }
        return new Result(success,updateAccount.getMsg(),success);
    }

    @Override
    public Result playerWithdraw(PlayerAccountReq record) {
        boolean success = Boolean.FALSE;
        record.setAmountDynType(AmountDynType.out.name());
        record.setTradeType(TradeType.withdraw.name());
        Result<PlayerAccount> updateAccount = null;
        try {
            //提现 冻结金额
            updateAccount = updatePlayerAccount(record);
            success = updateAccount.getSuccess();

            String accAddr = record.getAccAddr();
            if (isInsideAccAddr(accAddr)){
                //内部提现 待审核  todo

            } else {
                //外部提现 todo

            }
        }catch (Exception e){
            logger.error("提现异常",e);
        }
        return new Result(success,updateAccount.getMsg(),success);
    }

    @Override
    public Result playerTransfer(PlayerAccountReq record) {
        boolean success = Boolean.FALSE;
        record.setAmountDynType(AmountDynType.out.name());
        record.setTradeType(TradeType.transfer.name());
        String accAddr = record.getAccAddr();
        Result<PlayerAccount> updateAccount = null;
        try {
            //转账 冻结金额
            updateAccount = updatePlayerAccount(record);
            success = updateAccount.getSuccess();

            if (isInsideAccAddr(accAddr)){
                //内部转账 立即到账 已审核

            }else {
                //外部转账

            }
        }catch (Exception e){
            logger.error("转账异常",e);
        }
        return new Result(success,updateAccount.getMsg(),success);
    }

    /**
     * 判断是内部还是外部地址
     * @param accAddr 玩家账户地址
     * @return TRUE：内部地址，FALSE外部地址
     */
    private boolean isInsideAccAddr(String accAddr){
        //判断是内部还是外部地址 todo

        return Boolean.TRUE;
    }

    /**
     * 更新账户数据
     * 充值、提现、转账
     * @param record
     * @return
     */
    private Result<PlayerAccount> updatePlayerAccount(PlayerAccountReq record) throws Exception{
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
        BigDecimal accUsdt = BigDecimal.ZERO;
        //充值
        if (record.getTradeType().equalsIgnoreCase(TradeType.recharge.name())){
            tradeType = TradeType.recharge.getDesc();
            //账户金额加上充值金额
            if (record.getTradeType().equalsIgnoreCase(AmountType.mt.name())){
                accMt = getPlayerAccount.getAccMt().add(record.getAccMt());
            }else if (record.getTradeType().equalsIgnoreCase(AmountType.usdt.name())){
                accUsdt = getPlayerAccount.getAccUsdt().add(record.getAccUsdt());
            }
        }
        //提现
        if (record.getTradeType().equalsIgnoreCase(TradeType.withdraw.name())){
            tradeType = TradeType.withdraw.getDesc();
            //账户金额减去提现金额
            if (record.getTradeType().equalsIgnoreCase(AmountType.mt.name())){
                accMt = getPlayerAccount.getAccMt().subtract(record.getAccMt());
            }else if (record.getTradeType().equalsIgnoreCase(AmountType.usdt.name())){
                accUsdt = getPlayerAccount.getAccUsdt().subtract(record.getAccUsdt());
            }
        }
        //转账
        if (record.getTradeType().equalsIgnoreCase(TradeType.transfer.name())){
            tradeType = TradeType.transfer.getDesc();
            if (record.getAmountDynType().equalsIgnoreCase(AmountDynType.out.name())){
                //转账转出 账户金额减去转账金额
                if (record.getTradeType().equalsIgnoreCase(AmountType.mt.name())){
                    accMt = getPlayerAccount.getAccMt().subtract(record.getAccMt());
                }else if (record.getTradeType().equalsIgnoreCase(AmountType.usdt.name())){
                    accUsdt = getPlayerAccount.getAccUsdt().subtract(record.getAccUsdt());
                }
            } else {
                //转账转入 账户金额加上转账金额
                if (record.getTradeType().equalsIgnoreCase(AmountType.mt.name())){
                    accMt = getPlayerAccount.getAccMt().add(record.getAccMt());
                }else if (record.getTradeType().equalsIgnoreCase(AmountType.usdt.name())){
                    accUsdt = getPlayerAccount.getAccUsdt().add(record.getAccUsdt());
                }
            }

        }
        accountUpdate.setAccMt(accMt);
        accountUpdate.setAccUsdt(accUsdt);
        desc = tradeType + "失败！";

        //更新PlayerAccount
        int updateResult = accountService.updatePlayerAccount(accountUpdate);
        if (updateResult > 0){
            success = Boolean.TRUE;
            desc = tradeType+"成功！";
        }

        // 新增交易记录
        record.setAccId(accountUpdate.getAccId());
        createAccountDynamic(record,desc);

        return new Result(success,desc,accountUpdate);
    }

    /**
     * 新增交易记录
     * @param record
     */
    private void createAccountDynamic(PlayerAccountReq record,String desc) throws Exception{
        PlayerTrade accountDynamicReq = new PlayerTrade();
        accountDynamicReq.setTradeType(record.getAmountDynType());
        accountDynamicReq.setTradeAmountType(record.getTradeType());
        accountDynamicReq.setTradeDesc(desc);
        accountDynamicReq.setTradeAccId(record.getAccId());
        accountDynamicReq.setTradePlayerId(record.getAccPlayerId());

        //交易金额类型 todo
        if (record.getTradeType().equalsIgnoreCase(AmountType.mt.name())){
            accountDynamicReq.setTradeAmount(record.getAccMt());
        }else if (record.getTradeType().equalsIgnoreCase(AmountType.usdt.name())){
            accountDynamicReq.setTradeAmount(record.getAccUsdt());
        }


        tradeService.insertPlayerTrade(accountDynamicReq);
    }


}
