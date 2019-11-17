package com.dream.city.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.Constants;
import com.dream.city.base.model.enu.InvestStatus;
import com.dream.city.base.model.mapper.EarnFalldownLogMapper;
import com.dream.city.base.model.mapper.InvestMapper;
import com.dream.city.base.model.mapper.PlayerAccountLogMapper;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.base.model.resp.PlayerEarningResp;
import com.dream.city.base.utils.CommDateUtil;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.job.thread.*;
import com.dream.city.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.time.DateUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author WVv
 *
 *
 */
@Slf4j
@Service
public class InvestServiceImpl implements InvestService {


    @Autowired
    private PlayerLikesService playerLikesService;
    @Autowired
    private PlayerAccountMapper playerAccountMapper;

    @Autowired
    private PlayerAccountLogMapper playerAccountLogMapper;


    @Autowired
    private PlayerAccountService accountService;
    @Autowired
    private InvestOrderService orderService;
    @Autowired
    private InvestRuleService ruleService;

    @Autowired
    private PlayerEarningService playerEarningService;

    @Autowired
    private EarnFalldownLogMapper earnFalldownLogMapper;

    @Autowired
    private RelationTreeService relationTreeService;

    @Autowired
    private InvestMapper investMapper;

    @Autowired
    RedisUtils redisUtils;


    public static List<IncreaseVo> incraseCount = new ArrayList<IncreaseVo>();
    @LcnTransaction
    @Transactional
    @Override
    public List<CityInvest> getAllCityInvests() {

        return investMapper.getAllCityInvests();
    }

    @LcnTransaction
    @Transactional
    @Override
    public CityInvest getCityInvest(Integer investId)throws BusinessException {
        return investMapper.getCityInvest(investId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Date getEndTimeAt(Integer orderInvestId)throws BusinessException {
        CityInvest invest = investMapper.getCityInvest(orderInvestId);

        return invest.getInEnd();
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<CityInvest> getInvests()throws BusinessException {
        return investMapper.getAllCityInvests();
    }

    @LcnTransaction
    @Transactional
    @Override
    public Map<String, String> getProfitCalculateTime(Date date)throws BusinessException {
        Map<String,String> times = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        //时分格式
        SimpleDateFormat df1 = new SimpleDateFormat("HH:mm");
        //年月日格式
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = df1.format(date);

        //昨天
        cal.add(Calendar.DATE, -1);
        String yesterday = dt.format(cal.getTime());
        String end = yesterday + " " + date1;
        //前天
        cal.add(Calendar.DATE, -1);
        String yesterdayMore = dt.format(cal.getTime());
        String start = yesterdayMore + " " + date1;
        times.put("start",start);
        times.put("end",end);
        return times;
    }



    @LcnTransaction
    @Transactional
    @Override
    public CityInvest getInvestById(Integer orderInvestId)throws BusinessException {
        return investMapper.getCityInvest(orderInvestId);
    }

    @Transactional
    @Override
    public void profitGrant(CityInvest invest) {
        //先掉落收益
        fallDown();

        //执行发收益
        Object obj = redisUtils.lpop("PROFIT_QUEUE_" + invest.getInId());
        if (obj == null){
            log.info(new Date() + "，invest.getInId() = "+invest.getInId()+",  redisUtils.lpop 为null，中途退出");
            return;
        }
        BigDecimal  profitSum = new BigDecimal(obj.toString());//当前项目待分配的总收益
        log.info("取出总收益为：{}",profitSum);
        RuleItem ruleItem = ruleService.getInvestRuleItemByKey(Constants.RULE_CURRENT);//
        List<InvestRule> rules = ruleService.getInvestRuleByItemKey(ruleItem.getItemId());//规则明细
        String ruleFlag ="";
        BigDecimal profit = BigDecimal.ZERO;
        BigDecimal everyOneProfit = BigDecimal.ZERO;//所有会员平均每个人可得收益
        BigDecimal firstTimeOrdersProfitPer = BigDecimal.ZERO;//第一次投资会员平均每个人可得收益
        for (InvestRule rule : rules ) {

            ruleFlag = rule.getRuleFlag();
            profit = profitSum.multiply(rule.getRuleRate());
            log.info("ruleFlag:{}",ruleFlag);
            log.info("当前所发收益对应规则:{}，收益总额：{}",ruleFlag,profit);
            if(Constants.ALL_ORDERS.equalsIgnoreCase(ruleFlag)){//所有订单收益
                allOrder(invest,profit);
            }else if(Constants.FIRST_TIME.equalsIgnoreCase(ruleFlag)){//第一次投资收益
                firstTime(rule,invest,profit);
            }else if(Constants.INVEST_LONG.equalsIgnoreCase(ruleFlag)){//投资时间最长收益
                investLong(rule,invest,profit);
            }else if(Constants.LIKES_GATHER.equalsIgnoreCase(ruleFlag)){//点赞最多玩家收益
                likes(rule,invest,profit);
            }
//            else if(Constants.TOP_MEMBERS.equalsIgnoreCase(ruleFlag)){//每日新增会员最多玩家收益
//                checkMmber(invest);
//                topMember(rule,invest,profit);
//            }
        }
    }


    private void fallDown(){
        Date date = DateUtil.subHours(new Date(),1); //上一次发收益的时间
        String time = CommDateUtil.getDateTimeFormat( DateUtil.addMinutes(date,10));//增加10分钟时间差
        //查询出所有满足掉落条件的记录
        List<PlayerEarning> playerEarnings = playerEarningService.getPlayerEarningCanFallDown(time);
        RuleItem fallDown = ruleService.getInvestRuleItemByKey(Constants.PROFIT_FALL_DOWN);//
        List<InvestRule> fallDownRules = ruleService.getInvestRuleByKey(fallDown.getItemId());//规则明细
        InvestRule investRule = fallDownRules.get(0);
        BigDecimal minDropAmount  = new BigDecimal("0.0001");
        BigDecimal number = investRule.getRuleRatePre();//掉落的收益平均分配的人数
        BigDecimal rate = investRule.getRuleRate();//掉落的收益所占比例
        BigDecimal everyFallDownProfit = BigDecimal.ZERO;
        BigDecimal fallDownProfit = BigDecimal.ZERO;
        for (PlayerEarning playerEarning:playerEarnings) {
            fallDownProfit = playerEarning.getEarnPreProfit().multiply(rate);
            everyFallDownProfit = fallDownProfit.divide(number);
            if(fallDownProfit.compareTo(minDropAmount)<0){
                playerEarnings.remove(playerEarning);
            }
        }
        final int falldownthreadSize = playerEarnings.size();
        CountDownLatch fallDownGate = new CountDownLatch(falldownthreadSize);
        for (int i=0;i<playerEarnings.size();i++) {
            ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                    new ProfitFallDownThead(playerLikesService,playerEarningService,earnFalldownLogMapper,fallDownProfit,
                            everyFallDownProfit,playerEarnings.get(i).getEarnPlayerId(),playerAccountMapper,playerAccountLogMapper,playerEarnings.get(i),
                            playerEarnings.get(i).getEarnInvestId(),number,fallDownGate));
            if(i==playerEarnings.size()-1){
                fallDownGate.countDown();
            }
        }
        try{
            fallDownGate.await();
        }catch (Exception e){
            log.info("等待计算---FALL_PROFIT---掉落收益计算结束发生异常");
        }
        log.info("计算---FALL_PROFIT---结束");
    }


    private void allOrder(CityInvest invest,BigDecimal profit){
        log.info("计算---ALL_ORDERS---开始");
        List<InvestOrder> orders = orderService.getInvestOrdersByCurrentReload(invest.getInId(), InvestStatus.MANAGEMENT.getStatus());
        BigDecimal everyOneProfit = profit.divide(new BigDecimal(orders.size()));
        log.info("ALL_ORDERS---每个人所得收益：{}",everyOneProfit);
        final int threadSize = orders.size();
        log.info("ALL_ORDERS---等待执行记录数：{}",threadSize);
        CountDownLatch endGate = new CountDownLatch(threadSize);
        for (int i=0;i<orders.size();i++) {
            ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                    new OrderProfitThead(everyOneProfit,orders.get(i),this,playerAccountMapper,accountService,playerEarningService,endGate));
            if(i==orders.size()-1){
                endGate.countDown();
            }
        }
        try{
            endGate.await();
        }catch (Exception e){
            log.info("等待计算---ALL_ORDERS---收益计算结束发生异常");
        }
        log.info("计算---ALL_ORDERS---结束");
    }


    private void firstTime(InvestRule rule,CityInvest invest, BigDecimal profit){
        Integer count = orderService.getInvestOrdersFirstTimeCount(invest.getInId());//第一次投资玩家总数量
        log.info("firstTime---第一次投资玩家总数量：{}",count);
        Integer limit = new BigDecimal(count).multiply(rule.getRuleRatePre()).intValue();//确定可获得收益人数
        //第一次投资订单 通过时间正序排序  去前limit 条订单 进行收益发放
        List<InvestOrder> orders = orderService.getInvestOrdersFirstTimeReload(invest.getInId(),limit);
        //计算每个人
        BigDecimal everyOneProfit = profit.divide(new BigDecimal(orders.size()));
        log.info("firstTime---每个人所得收益：{}",everyOneProfit);
        final int threadSize = orders.size();
        log.info("firstTime---等待执行记录数：{}",threadSize);
        CountDownLatch endGate = new CountDownLatch(threadSize);
        for (int i=0;i<orders.size();i++) {
            ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                    new OrderProfitThead(everyOneProfit,orders.get(i),this,playerAccountMapper,accountService,playerEarningService,endGate));
            if(i==orders.size()-1){
                endGate.countDown();
            }
        }
        try{
            endGate.await();
        }catch (Exception e){
            log.info("等待计算---FIRST_TIME---收益计算结束发生异常");
        }
        log.info("计算---FIRST_TIME---结束");
    }


    private void investLong(InvestRule rule,CityInvest invest, BigDecimal profit){
        Integer topLong = rule.getRuleRatePre().intValue();
        BigDecimal everyOneProfit = profit.divide(new BigDecimal(topLong));
        log.info("investLong---每个人所得收益：{}",everyOneProfit);
        List<InvestOrder> orders = orderService.getInvestLongOrdersReload(invest.getInId(),topLong);
        final int threadSize = orders.size();
        log.info("investLong---等待执行记录数：{}",threadSize);
        CountDownLatch endGate = new CountDownLatch(threadSize);
        for (int i=0;i<orders.size();i++) {
            ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                    new OrderProfitThead(everyOneProfit,orders.get(i),this,playerAccountMapper,accountService,playerEarningService,endGate));
            if(i==orders.size()-1){
                endGate.countDown();
            }
        }
        try{
            endGate.await();
        }catch (Exception e){
            log.info("等待计算---INVEST_LONG---收益计算结束发生异常");
        }
        log.info("计算---INVEST_LONG---结束");
    }

    private void likes(InvestRule rule,CityInvest invest, BigDecimal profit){
        BigDecimal everyOneProfit = profit.divide(rule.getRuleRatePre());
        log.info("likes---每个人所得收益：{}",everyOneProfit);
        List<InvestOrder> orders = orderService.getLikesGatherReload(invest.getInId(),rule.getRuleRatePre().intValue());
        final int threadSize = orders.size();
        log.info("likes---等待执行记录数：{}",threadSize);
        CountDownLatch endGate = new CountDownLatch(threadSize);
        for (int i=0;i<orders.size();i++) {
            ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                    new OrderProfitThead(everyOneProfit,orders.get(i),this,playerAccountMapper,accountService,playerEarningService,endGate));
            if(i==orders.size()-1){
                endGate.countDown();
            }
        }
        try{
            endGate.await();
        }catch (Exception e){
            log.info("等待计算---LIKES_GATHER---收益计算结束发生异常");
        }
        log.info("计算---LIKES_GATHER---结束");
    }

    private void checkMmber(CityInvest invest){
        List<InvestOrder> orders = orderService.getInvestOrdersByCurrentReload(invest.getInId(), InvestStatus.MANAGEMENT.getStatus());
        Map<String,String> times = getProfitCalculateTime(invest.getInStart());
        final int threadSize = orders.size();
        CountDownLatch endGate = new CountDownLatch(threadSize);
        for (int i=0;i<orders.size();i++) {
            ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                    new IncreaseMemberCountThead(orders.get(i),relationTreeService,times.get("start"),times.get("end"),accountService,endGate));
            if(i==orders.size()-1){
                endGate.countDown();
            }
        }
        try{
            endGate.await();
        }catch (Exception e){
            log.info("组装-----TOP_MEMBERS-----新增人数以及订单数据异常");
        }
        log.info("组装-----TOP_MEMBERS-----新增人数以及订单数据结束----");
        Collections.sort(incraseCount, new Comparator<IncreaseVo>() {
            public int compare(IncreaseVo o1, IncreaseVo o2) {
                Integer count1 = o1.getCount();
                Integer count2 = o2.getCount();
                int res = count2-count1;
                return res;
            }
        });
        incraseCount.sort((o1,o2)->{
            return o2.getCount()-o1.getCount();
        });//得到按照日新增人数正序排序的列表
    }


    private void topMember(InvestRule rule,CityInvest invest, BigDecimal profit){
        List<InvestOrder> orders  = new ArrayList<InvestOrder>();
        Integer topCount = rule.getRuleRatePre().intValue();
        BigDecimal everyOneProfit = profit.divide(rule.getRuleRatePre());
        if(incraseCount.size()>topCount){
            for (int i=0;i<topCount;i++){
                orders.add(incraseCount.get(i).getOrder());
            }
        }
        final int topMemberthreadSize = topCount;
        CountDownLatch topMemberendGate = new CountDownLatch(topMemberthreadSize);
        for (int i=0;i<orders.size();i++) {
            ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                    new OrderProfitThead(everyOneProfit,orders.get(i),this,playerAccountMapper,accountService,playerEarningService,topMemberendGate));
            if(i==orders.size()-1){
                topMemberendGate.countDown();
            }
        }
        try{
            topMemberendGate.await();
        }catch (Exception e){
            log.info("计算---TOP_MEMBERS---收益结束");
        }
        incraseCount.clear();
    }
}
