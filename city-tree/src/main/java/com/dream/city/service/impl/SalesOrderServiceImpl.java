package com.dream.city.service.impl;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.entity.SalesOrder;
import com.dream.city.domain.mapper.PlayerAccountMapper;
import com.dream.city.domain.mapper.SalesOrderMapper;
import com.dream.city.domain.mapper.TreeMapper;
import com.dream.city.service.SalesOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {
    @Autowired
    private SalesOrderMapper salesOrderMapper;
    @Autowired
    private PlayerAccountMapper playerAccountMapper;
    @Autowired
    private TreeMapper treeMapper;

    @Override
    public List<SalesOrder> selectSalesOrder(String playerId) {
        return null;
    }

    @Override
    public SalesOrder getSalesOrder(Long id){
        return salesOrderMapper.selectSalesOrderByPrimaryKey(id);
    }

    @Override
    public List<SalesOrder> selectSalesSellerOrder(String playerId) {

        return salesOrderMapper.selectSalesSellerOrder(playerId);
    }

    @Override
    public List<SalesOrder> selectSalesBuyerOrder(String playerId) {
        return salesOrderMapper.selectSalesBuyerOrder(playerId);
    }

    @Override
    public Result buyMt(BigDecimal buyAmount, String playerId){
        //判断是否有足够的支付USDT:剩余额度
        PlayerAccount playerAccount = playerAccountMapper.getPlayerAccount(playerId);
        if (playerAccount.getAccUsdtAvailable().compareTo(buyAmount.multiply(new BigDecimal(0.1))) < 0){
            return new Result("USDT剩余可用额度不足",503);
        }
        //找出上家
        RelationTree tree =  treeMapper.getByPlayer(playerId);
        String parentId = tree.getParentId();

        //生成订单
        SalesOrder order = new SalesOrder();
        order.setId(0);
        order.setCreateTime(Timestamp.valueOf(String.valueOf(System.currentTimeMillis())));
        order.setOrderId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        order.setOrderBuyType("MT");
        order.setOrderPayType("USDT");
        order.setOrderPlayerBuyer(playerId);
        order.setOrderPlayerSeller(parentId);
        order.setOrderState(1);
        order.setOrderAmount(buyAmount);
        salesOrderMapper.createSalesOrder(order);

        return new Result("下单成功",200);

    }




}
