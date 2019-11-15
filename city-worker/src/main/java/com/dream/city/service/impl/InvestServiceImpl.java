package com.dream.city.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.Constants;
import com.dream.city.base.model.enu.InvestStatus;
import com.dream.city.base.model.mapper.InvestMapper;
import com.dream.city.base.model.resp.PlayerEarningResp;
import com.dream.city.base.utils.CommDateUtil;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.job.thread.IncreaseMemberCountThead;
import com.dream.city.job.thread.IncreaseVo;
import com.dream.city.job.thread.OrderProfitThead;
import com.dream.city.job.thread.ThreadPoolUtil;
import com.dream.city.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    InvestMapper investMapper;

    @Autowired
    PlayerAccountService accountService;

    @Autowired
    private InvestOrderService orderService;

    @Autowired
    private InvestRuleService ruleService;

    @Autowired
    private PlayerEarningService playerEarningService;

    @Autowired
    private RelationTreeService relationTreeService;


    @Autowired
    RedisUtils redisUtils;

    private final String RULE_CURRENT = "PROFIT_GRANT";
    private final String SYSTEM_ACCOUNT = "SYSTEM_DC_ACCOUNT";
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

    @Override
    public void profitGrant() {
        List<CityInvest> invests = getInvests();//所有的项目
        RuleItem ruleItem = ruleService.getInvestRuleItemByKey(RULE_CURRENT);//
        List<InvestRule> rules = ruleService.getInvestRuleByKey(ruleItem.getItemId());//规则明细

        BigDecimal profitSum = BigDecimal.ZERO;
        String ruleFlag ="";
        BigDecimal profit = BigDecimal.ZERO;
        BigDecimal everyOneProfit = BigDecimal.ZERO;//所有会员平均每个人可得收益
        BigDecimal firstTimeOrdersProfitPer = BigDecimal.ZERO;//第一次投资会员平均每个人可得收益
        for (CityInvest invest : invests ) {
            Object obj = redisUtils.lpop("PROFIT_QUEUE_" + invest.getInId());
            if (obj == null){
                log.info(new Date() + "，invest.getInId() = "+invest.getInId()+",  redisUtils.lpop 为null，中途退出");
                return;
            }
            profitSum = new BigDecimal(obj.toString());//当前项目待分配的总收益
            for (InvestRule rule : rules ) {
                ruleFlag = rule.getRuleFlag();
//                ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
//                        new CalcInterestThead(date,sysConfig.getConfigValue(),vgoPrice, userfundboxDto, financesTransferRecords,endGate));
                if(Constants.ALL_ORDERS.equalsIgnoreCase(ruleFlag)){//所有订单收益
                    profit = profitSum.multiply(rule.getRuleRate());
                    List<InvestOrder> orders = orderService.getInvestOrdersByCurrentReload(invest.getInId(), InvestStatus.MANAGEMENT.getStatus());
                    everyOneProfit = profit.divide(new BigDecimal(orders.size()));
                    final int threadSize = orders.size();
                    CountDownLatch endGate = new CountDownLatch(threadSize);
                    for (int i=0;i<orders.size();i++) {
                        ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                                new OrderProfitThead(everyOneProfit,orders.get(i),this,accountService,playerEarningService,endGate));
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
                }else if(Constants.FIRST_TIME.equalsIgnoreCase(ruleFlag)){
                    profit = profitSum.multiply(rule.getRuleRate());//第一次投资分配到的收益总额
                    Integer count = orderService.getInvestOrdersFirstTimeCount(invest.getInId());//第一次投资玩家总数量
                    Integer limit = new BigDecimal(count).multiply(rule.getRuleRatePre()).intValue();//确定可获得收益人数
                    //第一次投资订单 通过时间正序排序  去前limit 条订单 进行收益发放
                    List<InvestOrder> orders = orderService.getInvestOrdersFirstTimeReload(invest.getInId(),limit);
                    //计算每个人
                    firstTimeOrdersProfitPer = profit.divide(new BigDecimal(orders.size()));
                    final int threadSize = orders.size();
                    CountDownLatch endGate = new CountDownLatch(threadSize);
                    for (int i=0;i<orders.size();i++) {
                        ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                                new OrderProfitThead(firstTimeOrdersProfitPer,orders.get(i),this,accountService,playerEarningService,endGate));
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
                }else if(Constants.INVEST_LONG.equalsIgnoreCase(ruleFlag)){
                    Integer topLong = rule.getRuleRatePre().intValue();
                    List<InvestOrder> orders = orderService.getInvestLongOrdersReload(invest.getInId(),topLong);
                    final int threadSize = orders.size();
                    CountDownLatch endGate = new CountDownLatch(threadSize);
                    for (int i=0;i<orders.size();i++) {
                        ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                                new OrderProfitThead(firstTimeOrdersProfitPer,orders.get(i),this,accountService,playerEarningService,endGate));
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
                }else if(Constants.LIKES_GATHER.equalsIgnoreCase(ruleFlag)){
                    BigDecimal topLikesProfit = profitSum.multiply(rule.getRuleRate());
                    BigDecimal everyTopLikesProfit = topLikesProfit.divide(rule.getRuleRatePre());
                    List<InvestOrder> orders = orderService.getLikesGatherReload(invest.getInId(),rule.getRuleRatePre().intValue());
                    final int threadSize = orders.size();
                    CountDownLatch endGate = new CountDownLatch(threadSize);
                    for (int i=0;i<orders.size();i++) {
                        ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                                new OrderProfitThead(firstTimeOrdersProfitPer,orders.get(i),this,accountService,playerEarningService,endGate));
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
                }else if(Constants.TOP_MEMBERS.equalsIgnoreCase(ruleFlag)){
                    List<InvestOrder> orders = orderService.getInvestOrdersByCurrentReload(invest.getInId(), InvestStatus.MANAGEMENT.getStatus());
                    Map<String,String> times = getProfitCalculateTime(invest.getInEnd());
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
                    orders.clear();
                    BigDecimal topMemberProfit= profitSum.multiply(rule.getRuleRate());
                    Integer topCount = rule.getRuleRatePre().intValue();
                    BigDecimal everyTopMmberProfit = topMemberProfit.divide(rule.getRuleRatePre());
                    if(incraseCount.size()>topCount){
                        for (int i=0;i<topCount-1;i++){
                            orders.add(incraseCount.get(i).getOrder());
                        }
                    }
                    final int topMemberthreadSize = topCount;
                    CountDownLatch topMemberendGate = new CountDownLatch(topMemberthreadSize);
                    for (int i=0;i<orders.size();i++) {
                        ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                                new OrderProfitThead(everyTopMmberProfit,orders.get(i),this,accountService,playerEarningService,topMemberendGate));
                        if(i==orders.size()-1){
                            endGate.countDown();
                        }
                    }
                    try{
                        endGate.await();
                    }catch (Exception e){
                        log.info("计算---TOP_MEMBERS---收益结束");
                    }
                }
            }
        }
    }
}
