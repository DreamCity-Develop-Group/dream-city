package com.dream.city.job.thread;


import com.dream.city.base.exception.BLKException;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.mapper.EarnFalldownLogMapper;
import com.dream.city.base.model.mapper.PlayerAccountLogMapper;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.service.PlayerAccountService;
import com.dream.city.service.PlayerEarningService;
import com.dream.city.service.PlayerLikesService;
import com.dream.city.service.RelationTreeService;
import com.dream.city.service.impl.InvestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2019/7/4 0004.
 */
@Slf4j
public class ProfitFallDownThead implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(ProfitFallDownThead.class);

    private PlayerLikesService playerLikesService;
    private PlayerEarningService playerEarningService;
    private PlayerAccountMapper playerAccountMapper;
    private EarnFalldownLogMapper earnFalldownLogMapper;
    private BigDecimal profit;
    private BigDecimal everyFallDownProfit;
    private BigDecimal number;
    private String playerId;
    private Integer earnInvestId;
    private PlayerEarning playerEarning;
    private PlayerAccountLogMapper playerAccountLogMapper;
    private CountDownLatch endGate;

    public ProfitFallDownThead(PlayerLikesService playerLikesService, PlayerEarningService playerEarningService,EarnFalldownLogMapper earnFalldownLogMapper,
                               BigDecimal profit,BigDecimal everyFallDownProfit,String playerId,
                               PlayerAccountMapper playerAccountMapper, PlayerAccountLogMapper playerAccountLogMapper,
                               PlayerEarning playerEarning,Integer earnInvestId,BigDecimal number, CountDownLatch endGate) {
        this.earnFalldownLogMapper = earnFalldownLogMapper;
        this.playerEarningService = playerEarningService;
        this.playerLikesService = playerLikesService;
        this.playerAccountLogMapper = playerAccountLogMapper;
        this.playerAccountMapper = playerAccountMapper;
        this.profit = profit;//总掉落收益
        this.everyFallDownProfit = everyFallDownProfit;//每个人可得收益
        this.number = number;
        this.playerEarning = playerEarning;
        this.playerId = playerId;
        this.earnInvestId = earnInvestId;
        this.endGate = endGate;
    }

    @Override
    public void run() {
        try {
            //掉落的收益平均分给10个人
            BigDecimal earning = profit.divide(number,4,BigDecimal.ROUND_HALF_DOWN);
            List<String> playerIds = playerLikesService.getLikesPlayerByInvestId(earnInvestId,playerId);//点赞人数
            if (playerIds.size()==0) {
                log.info("earn_invest_id:{},没有满足捡漏条件的投资（没人点赞）",earnInvestId);
                return;
            }
            //当前获得额度 earn_current
            if (playerEarning.getEarnCurrent().compareTo(new BigDecimal("1")) <= 0){
                log.info("earn_invest_id :{} 当前收益不足1usdt，状态改为收益中，不再掉落",earnInvestId);
                playerEarningService.updateEarningWithRawStatus(playerEarning.getEarnId(),1);
                return;
            }
            String playerId="";
            Integer rand = 0;
            EarnFalldownLog earnFalldownLog;
            for (int i=0;i<number.intValue();i++){//随机取出number个会员
                rand = (int)(Math.random()*(playerIds.size()-1));
                playerId = playerIds.get(rand);
                earnFalldownLog = new EarnFalldownLog(playerEarning.getEarnInvestId(),playerEarning.getEarnPlayerId(),everyFallDownProfit);
                earnFalldownLogMapper.insertSelective(earnFalldownLog);
                PlayerAccountLog playerAccountLog = new PlayerAccountLog(playerId,"",everyFallDownProfit,BigDecimal.ZERO,1,"earn_id="+playerEarning.getEarnId()  + "捡漏收益");
                playerAccountLogMapper.insertSelective(playerAccountLog);
                int res = playerAccountMapper.addPlayerUsdtAmount(playerId,everyFallDownProfit);//增加账户资产
                if(res ==0){
                    log.error("更新账户资产异常：预约记录ID：{}-------资产用户：{}",playerEarning.getEarnId(),playerId);
                    throw new BLKException(400,"更新账户资产异常");
                }
            }
            if(playerEarning.getEarnCurrent().subtract(profit).compareTo(BigDecimal.ONE)<0){//若掉落后总额小于1 则改为收益中的状态
                playerEarningService.updateEarningWithRawStatus(playerEarning.getEarnId(),1);
            }
            playerEarningService.updateEarningFallDown(playerEarning.getEarnId(),profit);//更新掉落总额以及当前总额
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            endGate.countDown();
        }

    }
}
