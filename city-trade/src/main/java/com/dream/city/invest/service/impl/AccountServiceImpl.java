package com.dream.city.invest.service.impl;

import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.enu.AmountDynType;
import com.dream.city.base.model.enu.TradeType;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.invest.domain.mapper.PlayerAccountMapper;
import com.dream.city.invest.service.AccountService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    PlayerAccountMapper accountMapper;



    @Override
    public int createPlayerAccount(PlayerAccount record) {
        Integer integer = accountMapper.insertSelective(record);
        return integer ==null?0:integer;
    }

    @Override
    public PlayerAccount getPlayerAccount(PlayerAccount record) {
        if (record.getAccId() == null && StringUtils.isBlank(record.getAccPlayerId())){
            return null;
        }
        return accountMapper.getPlayerAccount(record);
    }

    @Override
    public List<PlayerAccount> getPlayerAccountList(PlayerAccount record) {
        return accountMapper.getPlayerAccountList(record);
    }

    @Override
    public int updatePlayerAccount(PlayerAccountReq record) {
        PlayerAccount account = new PlayerAccount();
        account.setAccPlayerId(record.getAccPlayerId());
        PlayerAccount getPlayerAccount = accountMapper.getPlayerAccount(account);

        Integer integer = 0;
        if (getPlayerAccount != null){  //todo
            PlayerAccount req = new PlayerAccount();
            req.setAccPlayerId(account.getAccPlayerId());
            /*req.setAccMtAvailable();
            req.setAccMtFreeze();
            req.setAccUsdtAvailable();
            req.setAccUsdtFreeze();*/

            BigDecimal accMt = BigDecimal.ZERO;
            BigDecimal accUsdt = BigDecimal.ZERO;
            if (record.getTradeType().equalsIgnoreCase(TradeType.recharge.name())){
                accMt = account.getAccMt().add(getPlayerAccount.getAccMt());
                accUsdt = account.getAccUsdt().add(getPlayerAccount.getAccUsdt());

            } else if (record.getTradeType().equalsIgnoreCase(TradeType.withdraw.name())){
                accMt = getPlayerAccount.getAccMt().subtract(account.getAccMt());
                accUsdt = getPlayerAccount.getAccUsdt().subtract(account.getAccUsdt());

            } else if (record.getTradeType().equalsIgnoreCase(TradeType.mt.name())){
                accMt = getPlayerAccount.getAccMt().subtract(account.getAccMt());

            } else if (record.getTradeType().equalsIgnoreCase(TradeType.usdt.name())){
                accUsdt = getPlayerAccount.getAccUsdt().subtract(account.getAccUsdt());

            } else if (record.getTradeType().equalsIgnoreCase(TradeType.transfer.name())){
                if (record.getAmountDynType().equalsIgnoreCase(AmountDynType.out.name())){
                    accMt = account.getAccMt().subtract(getPlayerAccount.getAccMt());
                    accUsdt = account.getAccUsdt().subtract(getPlayerAccount.getAccUsdt());
                } else {
                    accMt = account.getAccMt().add(getPlayerAccount.getAccMt());
                    accUsdt = account.getAccUsdt().add(getPlayerAccount.getAccUsdt());
                }
            }
            req.setAccMt(accMt);
            req.setAccUsdt(accUsdt);

            integer = accountMapper.updateByPlayerId(req);
        }

        return integer ==null?0:integer;
    }
}
