package com.dream.city.job;

import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.mapper.EarnFalldownLogMapper;
import com.dream.city.base.model.mapper.PlayerAccountLogMapper;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.base.model.mapper.PlayerEarningMapper;
import com.dream.city.service.*;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * TODO 核算 玩家每日收益结果
 *
 * @author WVv
 */
@Slf4j
public class FallDonwJob extends QuartzJobBean {

    @Autowired
    PlayerEarningService playerEarningService;

    @Autowired
    private EarnFalldownLogMapper earnFalldownLogMapper;

    @Autowired
    private PlayerAccountMapper playerAccountMapper;

    @Autowired
    private PlayerAccountLogMapper playerAccountLogMapper;

    @Autowired
    private PlayerLikesService playerLikesService;

    @Autowired
    private PlayerEarningMapper playerEarningMapper;

    @Autowired
    private MessageService messageService;

    private final String RULE_CURRENT = "PROFIT_GRANT";
    private final String ProfitQueue =  "PROFIT_QUEUE";


    /**
     *TODO
     * 1、取出所有投资项目
     * 2、遍历所有投资项目预约的（到截止时间，上一工作日的所有投资订单）
     * 3、在2中，直接根据规则返回所有能够取得成功的成员
     * 4、遍历所有成功的订单，设置为成功状态
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(new Date() + "    捡漏分配处理");
        //TODO 取出所有符合捡漏的玩家收益
        //哪种状态符合条件
        int withdrewState = 2;
        //未复投几小时后
        int afterHours = 12;
        //总收益的比例
        BigDecimal rate = new BigDecimal("0.1");
        //分成几份
        BigDecimal playerNum = new BigDecimal("10");
        //捡漏最小金额，低于此值丢弃
        BigDecimal minDropAmount  = new BigDecimal("0.0000001");


        List<PlayerEarning> playerEarnings = playerEarningService.getPlayerEarningByAfterHours(withdrewState, afterHours);


        playerEarnings.forEach(playerEarning -> {
            //本投资收益的rate 比例，保留4位小数
            BigDecimal earning = playerEarning.getEarnCurrent().multiply(rate).divide(playerNum,4,BigDecimal.ROUND_HALF_DOWN);


            //投资项目ID
            int earn_invest_id = playerEarning.getEarnInvestId();

            if (earning.compareTo(minDropAmount) <= 0){
                log.info(new Date() + "，invest_id = "+earn_invest_id+",  捡漏金额="+earning+"太小，已丢弃");
                return;
            }

            //找到所有点赞的玩家
            List<String> playerIds = playerLikesService.getPlayerIdByInvestId(earn_invest_id);


            if (playerIds.size()==0) {
                log.info(new Date() + "  没有满足捡漏条件的投资");
                return;
            }
            //当前获得额度 earn_current 还是最大预计收益 earn_max ?
            if (playerEarning.getDropTotal().compareTo(playerEarning.getEarnCurrent()) >= 0){
                log.info(new Date() + "，earn_invest_id = "+earn_invest_id+",  捡漏金额已超过经营收益");
                return;
            }
            //随机 0 至 size-1 （都包含）

            Random rand = new Random();
            int MIN = 0;
            int MAX = playerIds.size() - 1;

            int randOne = rand.nextInt(MAX - MIN + 1) + MIN;
            if (randOne < 0 || randOne > MAX) {
                randOne = 0;
            }
            String randPlayerId = playerIds.get(randOne);

            //捡漏明细
            EarnFalldownLog earnFalldownLog = new EarnFalldownLog();
            earnFalldownLog.setFallInvestId(earn_invest_id);
            earnFalldownLog.setFallPlayerId(randPlayerId);
            earnFalldownLog.setFallAmount(earning);
            earnFalldownLog.setCreateTime(new Date());
            earnFalldownLogMapper.insertSelective(earnFalldownLog);

            //玩家账户余额
            PlayerAccount playerAccount = playerAccountMapper.getPlayerAccountByPlayerId(randPlayerId);

            playerAccount.setAccMt(playerAccount.getAccMt().add(earning));
            playerAccount.setAccMtAvailable(playerAccount.getAccMtAvailable().add(earning));
            playerAccount.setUpdateTime(new Date());
            playerAccountMapper.updatePlayerAccount(playerAccount);

            //玩家账户出入账
            PlayerAccountLog playerAccountLog = new PlayerAccountLog();
            playerAccountLog.setPlayerId(randPlayerId);
            playerAccountLog.setAmountUsdt(earning);
            playerAccountLog.setCreateTime(new Date());
            playerAccountLog.setType(1);
            playerAccountLog.setDesc("earn_id="+playerEarning.getEarnId()  + "捡漏收益");
            playerAccountLogMapper.insertSelective(playerAccountLog);


            //更新投资收益表
            playerEarning.setDropTotal(playerEarning.getDropTotal().add(earning));
            BigDecimal amount = playerEarning.getEarnCurrent().subtract(earning);
            if(amount.compareTo(BigDecimal.ZERO) <= 0){
                amount = BigDecimal.ZERO;
            }
            playerEarning.setEarnCurrent(amount);
            playerEarningMapper.updateByPrimaryKeySelective(playerEarning);

            //推送到前台显示
            String desc = "恭喜您捡到 "+earning+" MT，多点赞多惊喜哦";
            messageService.pushJobMessage(randPlayerId, desc);

            log.info(new Date() + "，PlayerId = "+randPlayerId+",  捡漏金额="+earning);

        });


    }
}
