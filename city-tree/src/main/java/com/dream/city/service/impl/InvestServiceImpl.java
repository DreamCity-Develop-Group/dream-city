package com.dream.city.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.AmountDynType;
import com.dream.city.base.model.enu.TradeDetailType;
import com.dream.city.base.model.enu.TradeStatus;
import com.dream.city.base.model.enu.TradeType;
import com.dream.city.base.model.mapper.*;
import com.dream.city.base.model.resp.PlayerEarningResp;
import com.dream.city.service.InvestAllowService;
import com.dream.city.service.PlayerAccountService;
import com.dream.city.service.RelationTreeService;
import com.dream.city.service.SalesOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Wvv
 */
@Slf4j
@Service
public class InvestServiceImpl implements InvestAllowService {
    @Value("${platform.account}")
    private String platformAccount;

    @Autowired
    InvestAllowMapper investMapper;

    @Autowired
    PlayerAccountMapper accountMapper;

    @Autowired
    PlayerAccountLogMapper accountLogMapper;

    @Autowired
    InvestOrderMapper investOrderMapper;

    @Autowired
    PlayerEarningMapper earningMapper;

    @Autowired
    TradeDetailMapper tradeDetailMapper;
    @Autowired
    PlayerTradeMapper playerTradeMapper;

    @Autowired
    PlayerAccountService accountService;
    @Autowired
    RelationTreeService relationTreeService;
    @Autowired
    SalesOrderService salesOrderService;


    @LcnTransaction
    @Transactional
    @Override
    public InvestAllow getInvestAllowByPlayerId(String playerId)  throws BusinessException{

        return investMapper.getInvestAllowByPlayerId(playerId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public boolean addInvestAllow(String playerId, BigDecimal amount) throws BusinessException {
        InvestAllow allow = new InvestAllow();
        allow.setAmount(amount);
        allow.setPlayerId(playerId);
        allow.setId(0);
        allow.setAllowed("1");
        allow.setCreateTime(new Timestamp(System.currentTimeMillis()));

        investMapper.insert(allow);
        return true;
    }

    @LcnTransaction
    @Transactional
    @Override
    public BigDecimal getRateDirection()  throws BusinessException{

        return new BigDecimal(0.35);
    }

    @LcnTransaction
    @Transactional
    @Override
    public BigDecimal getRateInterpolation() throws BusinessException {
        return new BigDecimal(0.05);
    }

    /**
     * TODO 分配最终收益到平台账户
     *
     * @param amount
     */
    @LcnTransaction
    @Transactional
    @Override
    public void allowcationUSDTToPlatform(BigDecimal amount) throws BusinessException {
        PlayerAccount accountPlatform = accountMapper.getPlayerAccountByAddr(platformAccount);

        accountPlatform.setAccUsdt(accountPlatform.getAccUsdt().add(amount));
        accountPlatform.setAccUsdtAvailable(accountPlatform.getAccUsdtAvailable().add(amount));

        //TODO 这里要加日志log
        boolean logRet = addAccountLog(accountPlatform, amount,"usdt");
        if (logRet) {
            accountMapper.updatePlayerAccount(accountPlatform);
        }
    }

    /**
     * TODO 分配比例收益到玩家
     *
     * @param amount
     * @param relationTree
     */
    @LcnTransaction
    @Transactional
    @Override
    public void allowcationUSDTToPlayer(BigDecimal amount, RelationTree relationTree) throws BusinessException {
        PlayerAccount accountPlayer = accountMapper.getPlayerAccount(relationTree.getTreePlayerId());

        accountPlayer.setAccUsdt(accountPlayer.getAccUsdt().add(amount));
        accountPlayer.setAccUsdtAvailable(accountPlayer.getAccUsdtAvailable().add(amount));

        //TODO 这里要加日志log
        boolean logRet = addAccountLog(accountPlayer, amount,"usdt");
        if (logRet) {
            accountMapper.updatePlayerAccount(accountPlayer);
        }
        SalesOrder order = new SalesOrder();
        order.setOrderPayAmount(amount);
        order.setOrderPlayerBuyer(accountPlayer.getAccPlayerId());
        salesOrderService.addPlayerTrade(order,
                accountPlayer,
                TradeType.LEVEL_EARN.getCode(),
                TradeStatus.IN.getCode(),
                AmountDynType.IN.getCode(),
                "印记分成");
        salesOrderService.addTradeDetail(order, 0, accountPlayer, TradeDetailType.LEVEL_EARN.getCode(), "印记分成");

    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerEarningResp investCollectEarning(String playerId, Integer investId)  throws BusinessException{
        PlayerEarning earning = new PlayerEarning();
        earning.setEarnInvestId(investId);
        earning.setEarnPlayerId(playerId);
        PlayerEarningResp earn = earningMapper.getPlayerEarning(earning);
        return earn;
    }

    @LcnTransaction
    @Transactional
    @Override
    public void updateEarning(PlayerEarning earning) throws BusinessException {
        earningMapper.updateByPrimaryKeySelective(earning);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result allocationToPlayer(String playerId, BigDecimal amount) throws BusinessException {
        try {
            //TODO锁定付费额度
            Result result = accountService.lockUstdAmount(playerId, amount);
            if (result.getSuccess()) {

                //TODO 遍历印记分配
                Map<Integer, RelationTree> parents = relationTreeService.getParents(playerId);
                BigDecimal rateDirection = getRateDirection();
                BigDecimal rateInterpolation = getRateInterpolation();
                /**
                 *TODO
                 * 分配USDT印记收益,key>1 表示为直推的收益分配
                 */
                BigDecimal total = amount;
                if (parents.size() > 1) {
                    for (Map.Entry<Integer, RelationTree> entry : parents.entrySet()) {
                        int key = entry.getKey();
                        RelationTree tree = entry.getValue();
                        BigDecimal allocation = new BigDecimal(0);

                        if (key > 1) {
                            //超出9级
                            if(key>9){
                                break;
                            }else {
                                //直接推的人数是否符合
                                List<RelationTree> trees = relationTreeService.getChilds(tree.getTreePlayerId());
                                //int pushers = key;
                                if (trees.size() >= key) {
                                    allocation = total.multiply(rateInterpolation);
                                }else {
                                    //不符合跳过
                                    continue;
                                }
                            }
                        } else {
                            allocation = total.multiply(rateDirection);
                        }

                        allowcationUSDTToPlayer(allocation, tree);
                        amount = amount.subtract(allocation);
                    }

                    //剩余USDT归平台
                    allowcationUSDTToPlatform(amount);

                } else if (parents.size() == 1) {
                    allowcationUSDTToPlatform(amount);
                }
                //释放账户额度
                Result result1 = accountService.unlockUstdAmount(playerId, total);
                if (result1.getSuccess()) {
                    return Result.result(true);
                }else {
                    //释放账户额度
                    result1 = accountService.unlockUstdAmount(playerId, total);
                }
                log.info("释放账户失败...");
                return Result.result(false);

            } else {
                return Result.result(false, "锁定费用失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(false, "分配失败失败");
        }

    }

    @LcnTransaction
    @Transactional
    @Override
    public boolean addAccountLog(PlayerAccount account, BigDecimal amount,String type) throws BusinessException {
        try {
            PlayerAccountLog accountLog = new PlayerAccountLog();
            accountLog.setId(0L);
            accountLog.setAccId(account.getAccId());
            accountLog.setPlayerId(account.getAccPlayerId());
            accountLog.setAddress(account.getAccAddr());

            if ("usdt".equals(type)) {
                accountLog.setAmountMt(new BigDecimal(0));
                accountLog.setAmountUsdt(amount);
            }else if("mt".equals(type)){
                accountLog.setAmountMt(amount);
                accountLog.setAmountUsdt(new BigDecimal(0));
            }else {
                accountLog.setAmountMt(amount);
                accountLog.setAmountUsdt(amount);
            }

            accountLog.setType(1);
            accountLog.setDesc("获取印记收益");
            accountLog.setCreateTime(new Date());
            accountLogMapper.insert(accountLog);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
