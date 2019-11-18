package com.dream.city.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.Constants;
import com.dream.city.base.model.enu.InvestStatus;
import com.dream.city.base.model.mapper.EarnFalldownLogMapper;
import com.dream.city.base.model.mapper.InvestMapper;
import com.dream.city.base.model.mapper.PlayerAccountLogMapper;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.base.utils.CommDateUtil;
import com.dream.city.base.utils.JSONHelper;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.job.thread.*;
import com.dream.city.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.time.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
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

    public static void main(String[] args) {
        System.out.println(new BigDecimal(30).divide(new BigDecimal(7),3,RoundingMode.HALF_UP));

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
        //执行发收益
        Object obj = redisUtils.lpop("PROFIT_QUEUE_" + invest.getInId());
        if (obj == null){
            log.info(new Date() + "，invest.getInId() = "+invest.getInId()+",  redisUtils.lpop 为null，中途退出");
            return;
        }
        BigDecimal  profitSum = new BigDecimal(obj.toString());//当前项目待分配的总收益
        log.info("这次发收益-----------------取出总收益为：{}",profitSum);
        RuleItem ruleItem = ruleService.getInvestRuleItemByKey(Constants.RULE_CURRENT);//
        List<InvestRule> rules = ruleService.getInvestRuleByItemKey(ruleItem.getItemId());//规则明细
        String ruleFlag ="";
        BigDecimal profit = BigDecimal.ZERO;
        for (InvestRule rule : rules ) {
            ruleFlag = rule.getRuleFlag();
            profit = profitSum.multiply(rule.getRuleRate());
            if(Constants.ALL_ORDERS.equalsIgnoreCase(ruleFlag)){//所有订单收益
                allOrder(invest,profit,profitSum);
            }else if(Constants.FIRST_TIME.equalsIgnoreCase(ruleFlag)){//第一次投资收益
                firstTime(rule,invest,profit,profitSum);
            }
            else if(Constants.INVEST_LONG.equalsIgnoreCase(ruleFlag)){//投资时间最长收益
                investLong(rule,invest,profit,profitSum);
            }else if(Constants.LIKES_GATHER.equalsIgnoreCase(ruleFlag)){//点赞最多玩家收益
                likes(rule,invest,profit,profitSum);
            }else if(Constants.TOP_MEMBERS.equalsIgnoreCase(ruleFlag)){//每日新增会员最多玩家收益
                checkMmber(invest);
                topMember(rule,invest,profit,profitSum);
            }
        }
    }

    @Override
    public void fallDown(){
        Date date = DateUtil.subHours(new Date(),1); //上一次发收益的时间
        String time = CommDateUtil.getDateTimeFormat( DateUtil.addMinutes(date,10));//增加10分钟时间差
        //查询出所有满足掉落条件的记录
        List<PlayerEarning> playerEarnings = playerEarningService.getPlayerEarningCanFallDown(time);
        RuleItem fallDown = ruleService.getInvestRuleItemByKey(Constants.PROFIT_FALL_DOWN);//
        List<InvestRule> fallDownRules = ruleService.getInvestRuleByItemKey(fallDown.getItemId());//规则明细
        InvestRule investRule = fallDownRules.get(0);

        BigDecimal number = investRule.getRuleRatePre();//掉落的收益平均分配的人数
        log.info("fallDown=============================number:{}------------",number);
        BigDecimal rate = investRule.getRuleRate();//掉落的收益所占比例

        final int falldownthreadSize = playerEarnings.size();
        CountDownLatch fallDownGate = new CountDownLatch(falldownthreadSize);
        PlayerEarning playerEarning ;
        for (int i=0;i<playerEarnings.size();i++) {
            playerEarning = playerEarnings.get(i);
            log.info("playerEarning:{}===============rate:{}",playerEarning,rate);
            ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                    new ProfitFallDownThead(accountService,playerLikesService,playerEarningService,earnFalldownLogMapper,playerEarning.getEarnPreProfit().multiply(rate)
                            ,playerAccountMapper,playerAccountLogMapper, playerEarning,number,fallDownGate));
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


    private void allOrder(CityInvest invest,BigDecimal profit,BigDecimal profitSum){
        long start =  System.currentTimeMillis();
        log.info("计算---ALL_ORDERS---开始");
        List<InvestOrder> orders = orderService.getInvestOrdersByCurrentReload(invest.getInId(), InvestStatus.MANAGEMENT.getStatus());
        log.info("ALL_ORDERS---等待执行记录数：{}", orders.size());
        if(orders.size()>0) {
            log.info("orders==================:{}", JSONHelper.toJson(orders));
            BigDecimal everyOneProfit = profit.divide(new BigDecimal(orders.size()),3,RoundingMode.HALF_UP);
            log.info("ALL_ORDERS---每个人所得收益：{}",everyOneProfit);
            final int threadSize = orders.size();
            CountDownLatch endGate = new CountDownLatch(threadSize);
            for (int i = 0; i < orders.size(); i++) {
                ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                        new OrderProfitThead(everyOneProfit, orders.get(i), this, playerAccountMapper, accountService, playerEarningService,Constants.ALL_ORDERS, endGate));
                if (i == orders.size() - 1) {
                    endGate.countDown();
                }
            }
            try {
                endGate.await();
            } catch (Exception e) {
                log.info("等待计算---ALL_ORDERS---收益计算结束发生异常");
            }
            log.info("计算---ALL_ORDERS---结束");
        }else{
            PlayerAccount account = accountService.getPlayerAccount(Constants.SYSTEM_ACCOUNT);
            playerAccountMapper.addPlayerUsdtAmount(account.getAccPlayerId(),profit);
            PlayerAccountLog accountLog = new PlayerAccountLog(Constants.SYSTEM_ACCOUNT,account.getAccAddr(),profit,BigDecimal.ZERO,1,"ALL_ORDERS收益未查询到满足条件用户，总金额为："+profit);
            accountService.addAccountLog(accountLog);

        }
        long end =  System.currentTimeMillis();
        log.info("ALL_ORDERS==================执行时间：{}",end-start);
    }

    private void firstTime(InvestRule rule,CityInvest invest, BigDecimal profit,BigDecimal profitSum){
        long start =  System.currentTimeMillis();
        Integer count = orderService.getInvestOrdersFirstTimeCount(invest.getInId());//第一次投资玩家总数量
        log.info("FIRST_TIME---第一次投资玩家总数量：{}",count);
        Integer limit = new BigDecimal(count).multiply(rule.getRuleRatePre()).setScale(0, RoundingMode.CEILING).intValue();//确定可获得收益人数
        //第一次投资订单 通过时间正序排序  去前limit 条订单 进行收益发放
        List<InvestOrder> orders = orderService.getInvestOrdersFirstTimeReload(invest.getInId(),limit);
        log.info("FIRST_TIME---等待执行记录数：{}",orders.size());
        //计算每个人
        if(orders.size()>0){
            BigDecimal everyOneProfit = profit.divide(new BigDecimal(orders.size()),3,RoundingMode.HALF_UP);
            log.info("FIRST_TIME---每个人所得收益：{}",everyOneProfit);
            final int threadSize = orders.size();
            CountDownLatch endGate = new CountDownLatch(threadSize);
            for (int i=0;i<orders.size();i++) {
                ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                        new OrderProfitThead(everyOneProfit,orders.get(i),this,playerAccountMapper,accountService,playerEarningService,Constants.FIRST_TIME,endGate));
                if(i==orders.size()-1){
                    endGate.countDown();
                }
            }
            try{
                endGate.await();
            }catch (Exception e){
                log.info("等待计算---FIRST_TIME---收益计算结束发生异常");
            }
        }else{
            PlayerAccount account = accountService.getPlayerAccount(Constants.SYSTEM_ACCOUNT);
            playerAccountMapper.addPlayerUsdtAmount(account.getAccPlayerId(),profit);
            PlayerAccountLog accountLog = new PlayerAccountLog(Constants.SYSTEM_ACCOUNT,account.getAccAddr(),profit,BigDecimal.ZERO,1,"FIRST_TIME收益未查询到满足条件用户，总金额为："+profit);
            accountService.addAccountLog(accountLog);
        }

        log.info("计算---FIRST_TIME---结束");
        long end =  System.currentTimeMillis();
        log.info("FIRST_TIME==================执行时间：{}",end-start);
    }


    private void investLong(InvestRule rule,CityInvest invest, BigDecimal profit,BigDecimal profitSum){
        long start =  System.currentTimeMillis();
        Integer topLong = rule.getRuleRatePre().intValue();
        List<InvestOrder> orders = orderService.getInvestLongOrdersReload(invest.getInId(),topLong);
        log.info("investLong---等待执行记录数：{}", orders.size());
        if(orders.size()>0) {
            BigDecimal everyOneProfit = profit.divide(new BigDecimal(orders.size()),3,RoundingMode.HALF_UP);
            log.info("investLong---每个人所得收益：{}",everyOneProfit);
            final int threadSize = orders.size();
            CountDownLatch endGate = new CountDownLatch(threadSize);
            for (int i = 0; i < orders.size(); i++) {
                ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                        new OrderProfitThead(everyOneProfit, orders.get(i), this, playerAccountMapper, accountService, playerEarningService,Constants.INVEST_LONG, endGate));
                if (i == orders.size() - 1) {
                    endGate.countDown();
                }
            }
            try {
                endGate.await();
            } catch (Exception e) {
                log.info("等待计算---INVEST_LONG---收益计算结束发生异常");
            }
            log.info("计算---INVEST_LONG---结束");
        }else{
            PlayerAccount account = accountService.getPlayerAccount(Constants.SYSTEM_ACCOUNT);
            playerAccountMapper.addPlayerUsdtAmount(account.getAccPlayerId(),profit);
            PlayerAccountLog accountLog = new PlayerAccountLog(Constants.SYSTEM_ACCOUNT,account.getAccAddr(),profit,BigDecimal.ZERO,1,"INVEST_LONG收益未查询到满足条件用户，总金额为："+profit);
            accountService.addAccountLog(accountLog);
        }
        long end =  System.currentTimeMillis();
        log.info("INVEST_LONG==================执行时间：{}",end-start);
    }

    private void likes(InvestRule rule,CityInvest invest, BigDecimal profit,BigDecimal profitSum){
        long start =  System.currentTimeMillis();
        List<InvestOrder> orders = orderService.getLikesGatherReload(invest.getInId(),rule.getRuleRatePre().intValue());
        log.info("likes---等待执行记录数：{}",orders.size());
        if(orders.size()>0){
            BigDecimal everyOneProfit = profit.divide(new BigDecimal(orders.size()),3,RoundingMode.HALF_UP);
            log.info("likes---每个人所得收益：{}",everyOneProfit);
            final int threadSize = orders.size();
            CountDownLatch endGate = new CountDownLatch(threadSize);
            for (int i=0;i<orders.size();i++) {
                ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                        new OrderProfitThead(everyOneProfit,orders.get(i),this,playerAccountMapper,accountService,playerEarningService,Constants.LIKES_GATHER,endGate));
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
        }else{
            PlayerAccount account = accountService.getPlayerAccount(Constants.SYSTEM_ACCOUNT);
            playerAccountMapper.addPlayerUsdtAmount(account.getAccPlayerId(),profit);
            PlayerAccountLog accountLog = new PlayerAccountLog(Constants.SYSTEM_ACCOUNT,account.getAccAddr(),profit,BigDecimal.ZERO,1,"LIKES_GATHER收益未查询到满足条件用户，总金额为："+profit);
            accountService.addAccountLog(accountLog);
        }
        long end =  System.currentTimeMillis();
        log.info("LIKES_GATHER==================执行时间：{}",end-start);
    }

    private void checkMmber(CityInvest invest){
        long start =  System.currentTimeMillis();
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
        long end =  System.currentTimeMillis();
        log.info("TOP_MEMBERS==================执行时间：{}",end-start);
    }


    private void topMember(InvestRule rule,CityInvest invest, BigDecimal profit,BigDecimal profitSum){
        long start =  System.currentTimeMillis();
        List<InvestOrder> orders  = new ArrayList<InvestOrder>();
        Integer topCount = rule.getRuleRatePre().intValue();
        if(incraseCount.size()>topCount){
            for (int i=0;i<topCount;i++){
                orders.add(incraseCount.get(i).getOrder());
            }
        }
        log.info("likes---等待执行记录数：{}",orders.size());
        if(orders.size()>0){
            BigDecimal everyOneProfit = profit.divide(rule.getRuleRatePre(),3,RoundingMode.HALF_UP);
            final int topMemberthreadSize = topCount;
            CountDownLatch topMemberendGate = new CountDownLatch(topMemberthreadSize);
            for (int i=0;i<orders.size();i++) {
                ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                        new OrderProfitThead(everyOneProfit,orders.get(i),this,playerAccountMapper,accountService,playerEarningService,Constants.TOP_MEMBERS,topMemberendGate));
                if(i==orders.size()-1){
                    topMemberendGate.countDown();
                }
            }
            try{
                topMemberendGate.await();
            }catch (Exception e){
                log.info("计算---TOP_MEMBERS---收益结束");
            }
        }else{
            PlayerAccount account = accountService.getPlayerAccount(Constants.SYSTEM_ACCOUNT);
            playerAccountMapper.addPlayerUsdtAmount(account.getAccPlayerId(),profit);
            PlayerAccountLog accountLog = new PlayerAccountLog(Constants.SYSTEM_ACCOUNT,account.getAccAddr(),profit,BigDecimal.ZERO,1,"TOP_MEMBER收益未查询到满足条件用户，总金额为："+profit);
            accountService.addAccountLog(accountLog);
        }
        incraseCount.clear();
        long end =  System.currentTimeMillis();
        log.info("TOP_MEMBERS==================执行时间：{}",end-start);

    }
}
