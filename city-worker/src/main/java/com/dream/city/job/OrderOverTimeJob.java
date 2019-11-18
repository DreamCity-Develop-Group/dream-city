package com.dream.city.job;

import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.Constants;
import com.dream.city.base.model.enu.OrderState;
import com.dream.city.base.model.mapper.*;
import com.dream.city.base.utils.CommDateUtil;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.job.thread.OrderOverTimeThead;
import com.dream.city.job.thread.OrderProfitThead;
import com.dream.city.job.thread.ThreadPoolUtil;
import com.dream.city.job.thread.ThreadTask;
import com.dream.city.service.*;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springside.modules.utils.time.DateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * TODO 购买订单处理逻辑
 *
 * @author WVv
 */
@Slf4j
public class OrderOverTimeJob extends QuartzJobBean {

    @Autowired
    InvestRuleService ruleService;
    @Autowired
    SalesOrderService salesOrderService;
    @Autowired
    RedisUtils redisUtils;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PlayerAccountService playerAccountService;

    @Autowired
    private CityBusinessMapper cityBusinessMapper;

    @Autowired
    private RelationTreeMapper relationTreeMapper;

    @Autowired
    private SalesOrderMapper salesOrderMapper;
    @Autowired
    private SalesRefuseOrderMapper salesRefuseOrderMapper;
    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private RelationTreeService  treeService;
    @Autowired
    private ThreadTask threadTask;
    /**
     *TODO
     * 1、取出所有【在线玩家】【等待处理】订单集合
     * 1.1、取出总累计超时次数
     * 2、遍历所有订单，推送提示到玩家
     * 3、在2中，将已经超时的订单转接给【上上家】，生成转接记录，累计超时次数
     * 4、
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(new Date() + "    超时兑换交易处理");
        Date date = DateUtil.subHours(new Date(),1);
        String dateTime = CommDateUtil.getDateTimeFormat(date);
        // 取出所有的支付成功的交易并且已经超时的订单
        List<SalesOrder> salesOrders = salesOrderService.getOverTimeOrder(dateTime);
        final int threadSize = salesOrders.size();
        CountDownLatch endGate = new CountDownLatch(threadSize);
        for (int i = 0; i < salesOrders.size(); i++) {
            ThreadPoolUtil.submit(ThreadPoolUtil.poolCount, ThreadPoolUtil.MODULE_MESSAGE_RESEND,
                    new OrderOverTimeThead(salesOrderMapper, salesOrders.get(i), salesRefuseOrderMapper, playerMapper, relationTreeMapper, cityBusinessMapper, endGate));
            if (i == salesOrders.size() - 1) {
                endGate.countDown();
            }
        }
        try {
            endGate.await();
        } catch (Exception e) {
            log.info("等待计算---OrderOverTimeJob---收益计算结束发生异常");
        }

    }

}
