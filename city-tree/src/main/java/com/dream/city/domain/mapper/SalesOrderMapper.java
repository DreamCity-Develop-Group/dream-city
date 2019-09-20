package com.dream.city.domain.mapper;

import com.dream.city.base.model.entity.SalesOrder;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wvv
 */
@Repository
public interface SalesOrderMapper {

    @Results(id = "BaseSalesOrderResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "orderAmount", column = "order_amount"),
            @Result(property = "orderBuyType", column = "order_buy_type"),
            @Result(property = "orderPayType", column = "order_pay_type"),
            @Result(property = "orderPlayerBuyer", column = "order_player_buyer"),
            @Result(property = "orderPlayerSeller", column = "order_player_seller"),
            @Result(property = "orderState", column = "order_state"),
            @Result(property = "createTime", column = "createtime"),
            @Result(property = "updateTime", column = "updatetime")
    })
    @Select({"select * from `sales_order` where id = #{id}"})
    SalesOrder selectSalesOrderByPrimaryKey(Long key);


    /**
     *  玩家的购买请求订单：玩家是卖家
     * @param playerId
     * @return
     */
    @Select("select * from sales_order where order_player_seller = #{playerId}")
    List<SalesOrder> selectSalesSellerOrder(String playerId);

    /**
     *  玩家的购买请求订单：玩家是买家
     * @param playerId
     * @return
     */
    @Select("select * from sales_order where order_player_buyer = #{playerId}")
    List<SalesOrder> selectSalesBuyerOrder(String playerId);

    @Insert("insert into `sales_order`(" +
            "order_id,order_amount,order_buy_type,order_pay_type,order_player_buyer,order_player_seller,order_state,createtime)values" +
            "(orderId,orderAmount,orderBuyType,orderPayType,orderPlayerBuyer,orderPlayerSeller,orderState,createTime) ")
    void createSalesOrder(SalesOrder order);

    @Select("select * from sales_order where 1=1 and ")
    SalesOrder findSalesOrder();

    @Select("select * from `sales_order` where 1=1 and order_id = #{orderId} limit 1 ")
    SalesOrder getSalesOrderByOrderId(String orderId);

    @Update("update `sales_order` set order_state=#{order.orderState},update_time=#{order.updateTime} where 1=1 and order_player_id=#{order.playerId}")
    void updateSalesOrder(SalesOrder order);
}
