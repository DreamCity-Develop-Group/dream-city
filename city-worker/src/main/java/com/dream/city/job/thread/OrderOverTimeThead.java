package com.dream.city.job.thread;


import com.dream.city.base.exception.BLKException;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.mapper.*;
import com.dream.city.base.utils.JSONHelper;
import com.dream.city.base.utils.KeyGenerator;
import com.dream.city.service.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2019/7/4 0004.
 */
@Slf4j
public class OrderOverTimeThead implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(OrderOverTimeThead.class);

    private SalesOrder order;

    private SalesRefuseOrderMapper salesRefuseOrderMapper;

    private PlayerMapper playerMapper;

    private CityBusinessMapper cityBusinessMapper;

    private RelationTreeMapper relationTreeMapper;

    private SalesOrderMapper salesOrderMapper;

    private CountDownLatch endGate;



    public OrderOverTimeThead(SalesOrderMapper salesOrderMapper,SalesOrder order,SalesRefuseOrderMapper salesRefuseOrderMapper,
                              PlayerMapper playerMapper,RelationTreeMapper relationTreeMapper,CityBusinessMapper cityBusinessMapper,CountDownLatch endGate) {
        this.salesOrderMapper = salesOrderMapper;
        this.cityBusinessMapper = cityBusinessMapper;
        this.order = order;
        this.salesRefuseOrderMapper = salesRefuseOrderMapper;
        this.relationTreeMapper = relationTreeMapper;
        this.playerMapper = playerMapper;
        this.endGate = endGate;
    }

    @Override
    public void run() {
        try {
            log.info("order=========================================:{}", JSONHelper.toJson(order));
            Integer count = salesRefuseOrderMapper.selectSalesBuyerRefuseOrders(order.getOrderPlayerBuyer(),order.getOrderPlayerSeller());
            log.info("count:-========================================={}",count);
            String playerSellId="";
            if(count>=2){//订单已经超时2次，此次为第三次，需要更改用户关系，并且把订单发送到上上级
                RelationTree relationTree = relationTreeMapper.getTreeByPlayerId(order.getOrderPlayerBuyer());//找出父级账号
                CityBusiness cityBusiness = cityBusinessMapper.getbusinessByPlayerId(order.getOrderPlayerBuyer());
                String tree ="";
                if(null != cityBusiness){
                    cityBusinessMapper.updateBusinessEnable(cityBusiness.getBusinessId());
                    tree = cityBusiness.getBusinessRelation();
                }else{
                    tree = relationTree.getTreeRelation();
                }
                String[] trees = tree.split("/");//当前关系树
                log.info("当前关系树tree============:{}",tree);
                String oldTreeCode = trees[trees.length-2];//找到父级的邀请码
                log.info("替换后的关系树oldTreeCode============:{}",oldTreeCode);
                String relationTreeCode = tree.replace("/"+oldTreeCode,"");//替换后的关系树
                log.info("替换后的关系树relationTreeCode============:{}",relationTreeCode);
                String[] replaceTrees = relationTreeCode.split("/");
                String newTreeCode = replaceTrees[trees.length-2];//找到替换后的父级的邀请码
                log.info("替换后的关系树newTreeCode============:{}",newTreeCode);
                Player fatherPlayer = playerMapper.getPlayerByInvite(newTreeCode);
                CityBusiness business = new CityBusiness(fatherPlayer.getPlayerId(),order.getOrderPlayerBuyer(),relationTreeCode,0);
                cityBusinessMapper.savebusiness(business);
                playerSellId = fatherPlayer.getPlayerId();
            }else{
                playerSellId = order.getOrderPlayerSeller();
            }
            salesOrderMapper.updateOverTimeOrder(order.getOrderId());
            SalesOrder salesOrder = new SalesOrder(KeyGenerator.generateOrderID(),order.getOrderAmount(),order.getOrderBuyType(),
                    order.getOrderPayType(),order.getOrderPayAmount(),order.getOrderPlayerBuyer(),playerSellId,3);
            salesOrderMapper.createSalesOrder(salesOrder);
            SalesRefuseOrder salesRefuseOrder = new SalesRefuseOrder(order.getOrderId(),"",order.getOrderPlayerBuyer(),order.getOrderPlayerSeller());
            salesRefuseOrderMapper.createSalesRefuseOrder(salesRefuseOrder);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            endGate.countDown();
        }

    }
}
