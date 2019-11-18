package com.dream.city.job.thread;


import com.dream.city.base.exception.BLKException;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.mapper.*;
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
            Integer count = salesRefuseOrderMapper.selectSalesBuyerRefuseOrders(order.getOrderPlayerBuyer(),order.getOrderPlayerSeller());
            String playerSellId="";
            if(count>=2){//订单已经超时2次，此次为第三次，需要更改用户关系，并且把订单发送到上上级
                CityBusiness cityBusiness = cityBusinessMapper.getbusinessByPlayerId(order.getOrderPlayerBuyer());
                String tree ="";
                if(null != cityBusiness){
                    cityBusinessMapper.updateBusinessEnable(cityBusiness.getBusinessId());
                    tree = cityBusiness.getBusinessRelation();
                }else{
                    RelationTree relationTree = relationTreeMapper.getTreeByPlayerId(order.getOrderPlayerBuyer());
                    tree = relationTree.getTreeRelation();
                    Player player = playerMapper.getPlayer(relationTree.getTreeParentId());
                    String relationTreeCode = tree.replace("/"+player.getPlayerInvite(),"");
                    CityBusiness business = new CityBusiness(player.getPlayerId(),order.getOrderPlayerBuyer(),relationTreeCode,0);
                    cityBusinessMapper.savebusiness(business);
                }
                String[] trees = tree.split("/");
                String fatherTreeCode = trees[trees.length-2];//找到父级的邀请码
                Player player = playerMapper.getPlayerByInvite(fatherTreeCode);
                playerSellId = player.getPlayerId();
            }else{
                playerSellId = order.getOrderPlayerSeller();
            }
            salesOrderMapper.updateOverTimeOrder(order.getOrderId());
            SalesOrder salesOrder = new SalesOrder(KeyGenerator.generateOrderID(),order.getOrderAmount(),order.getOrderBuyType(),
                    order.getOrderPayType(),order.getOrderPayAmount(),order.getOrderPlayerBuyer(),playerSellId,2);
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
