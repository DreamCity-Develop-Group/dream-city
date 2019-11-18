package com.dream.city.job.thread;


import com.dream.city.base.exception.BLKException;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.Constants;
import com.dream.city.base.model.mapper.EarnFalldownLogMapper;
import com.dream.city.base.model.mapper.PlayerAccountLogMapper;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.base.utils.JSONHelper;
import com.dream.city.service.PlayerAccountService;
import com.dream.city.service.PlayerEarningService;
import com.dream.city.service.PlayerLikesService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2019/7/4 0004.
 */
@Slf4j
public class ProfitFallDownThead implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(ProfitFallDownThead.class);

    public static final BigDecimal minDropAmount  = new BigDecimal("0.0001");

    private PlayerAccountService accountService;
    private PlayerLikesService playerLikesService;
    private PlayerEarningService playerEarningService;
    private PlayerAccountMapper playerAccountMapper;
    private EarnFalldownLogMapper earnFalldownLogMapper;
    private BigDecimal fallDownAmount;
    private BigDecimal number;
    private PlayerEarning playerEarning;
    private PlayerAccountLogMapper playerAccountLogMapper;
    private CountDownLatch endGate;

    public ProfitFallDownThead(PlayerAccountService accountService,PlayerLikesService playerLikesService, PlayerEarningService playerEarningService,EarnFalldownLogMapper earnFalldownLogMapper,
                               BigDecimal fallDownAmount,PlayerAccountMapper playerAccountMapper, PlayerAccountLogMapper playerAccountLogMapper,
                               PlayerEarning playerEarning,BigDecimal number, CountDownLatch endGate) {
        this.accountService = accountService;
        this.earnFalldownLogMapper = earnFalldownLogMapper;
        this.playerEarningService = playerEarningService;
        this.playerLikesService = playerLikesService;
        this.playerAccountLogMapper = playerAccountLogMapper;
        this.playerAccountMapper = playerAccountMapper;
        this.fallDownAmount = fallDownAmount;//掉落收益
        this.number = number;
        this.playerEarning = playerEarning;
        this.endGate = endGate;
    }

    @Override
    public void run() {
        if(fallDownAmount.compareTo(minDropAmount)<0){
            log.info("小于最低掉落额度----playerEarning:{}------不掉落：{}", JSONHelper.toJson(playerEarning),fallDownAmount);
            return;
        }
        try {
           //掉落的收益平均分给10个人
            Integer count = number.intValue();
            List<String> playerIds = playerLikesService.getLikesPlayerByInvestId(playerEarning.getEarnPlayerId(),count);//点赞人数

            playerEarningService.updateEarningFallDown(playerEarning.getEarnId(),fallDownAmount);//更新掉落总额以及当前总额
            EarnFalldownLog earnFalldownLog = new EarnFalldownLog(playerEarning.getEarnInvestId(),playerEarning.getEarnPlayerId(),fallDownAmount);
            earnFalldownLogMapper.insertSelective(earnFalldownLog);
            if(playerEarning.getEarnCurrent().subtract(fallDownAmount).compareTo(BigDecimal.ONE)<0){//若掉落后总额小于1 则改为收益中的状态
                playerEarningService.updateEarningWithRawStatus(playerEarning.getEarnId(),1);
            }

            if(playerIds.size()<=0||
                    fallDownAmount.divide(number,4, RoundingMode.FLOOR).compareTo(minDropAmount)<0){
                log.info("没人满足收取条件或者每人所得收益小于最低额度：{}",fallDownAmount);
                PlayerAccount account = accountService.getPlayerAccount(Constants.SYSTEM_ACCOUNT);
                playerAccountMapper.addPlayerUsdtAmount(account.getAccPlayerId(),fallDownAmount);
                PlayerAccountLog accountLog = new PlayerAccountLog(Constants.SYSTEM_ACCOUNT,account.getAccAddr(),fallDownAmount,BigDecimal.ZERO,1,"掉落收益未查询到满足条件用户，总金额为："+fallDownAmount);
                accountService.addAccountLog(accountLog);
                playerEarningService.updateEarningFallDown(playerEarning.getEarnId(),fallDownAmount);//更新掉落总额以及当前总额
                return ;
            }
            BigDecimal earning = fallDownAmount.divide(number,4,RoundingMode.FLOOR);
            log.info("掉落收益 playerIds:{}=====================================每个人可得：{}",playerIds,earning);
            for (int i=0;i<playerIds.size();i++){//随机取出number个会员
                PlayerAccountLog playerAccountLog = new PlayerAccountLog(playerIds.get(i),"",earning,BigDecimal.ZERO,1,"earn_id="+playerEarning.getEarnId()  + "捡漏收益");
                playerAccountLogMapper.insertSelective(playerAccountLog);
                int res = playerAccountMapper.addPlayerUsdtAmount(playerIds.get(i),earning);//增加账户资产
                if(res ==0){
                    log.error("更新账户资产异常：预约记录ID：{}-------资产用户：{}",playerEarning.getEarnId(),playerIds.get(i));
                    throw new BLKException(400,"更新账户资产异常");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            endGate.countDown();
        }

    }

}
