package com.dream.city.job.thread;


import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.service.InvestService;
import com.dream.city.service.PlayerAccountService;
import com.dream.city.service.PlayerEarningService;
import com.dream.city.service.RelationTreeService;
import com.dream.city.service.impl.InvestServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2019/7/4 0004.
 */
public class IncreaseMemberCountThead implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(IncreaseMemberCountThead.class);

    private InvestOrder order;
    private RelationTreeService relationTreeService;
    private PlayerAccountService payerAccountService;
    private String startTime;
    private String endTime;
    private CountDownLatch endGate;

    public IncreaseMemberCountThead(InvestOrder order, RelationTreeService relationTreeService,
                                    String startTime,String endTime,PlayerAccountService payerAccountService,CountDownLatch endGate) {
        this.relationTreeService = relationTreeService;
        this.payerAccountService = payerAccountService;
        this.order = order;
        this.startTime = startTime;
        this.endTime = endTime;
        this.endGate = endGate;
    }

    @Override
    public void run() {
        try {
            RelationTree relationTree = relationTreeService.getSelfTree(order.getOrderPayerId());
            String tree = relationTree.getTreeRelation();
            Integer count = relationTreeService.getTeamListCount(tree,startTime,endTime);
            IncreaseVo increaseVo = new IncreaseVo(order,count);
            InvestServiceImpl.incraseCount.add(increaseVo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("用户收益计算错误，用户ID为：" + order.getOrderPayerId());
        } finally {
            endGate.countDown();
        }

    }

}
