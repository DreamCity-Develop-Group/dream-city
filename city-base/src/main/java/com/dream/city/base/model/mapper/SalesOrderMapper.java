package com.dream.city.base.model.mapper;

import com.dream.city.base.model.entity.SalesOrder;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wvv
 */
@Mapper
public interface SalesOrderMapper {



    SalesOrder getSalesOrderById(Integer id);

    List<SalesOrder> getSalesOrderList(SalesOrder record);

    @Results(id = "BaseSalesOrderResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "orderAmount", column = "order_amount"),
            @Result(property = "orderBuyType", column = "order_buy_type"),
            @Result(property = "orderPayType", column = "order_pay_type"),
            @Result(property = "orderPayAmount", column = "order_pay_amount"),
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
    @Select("select * from sales_order " +
            "where 1=1 and order_player_seller = #{playerId} " +
            "ORDER BY createtime DESC "+
            "limit #{start}, #{size} ")
    @ResultMap("BaseSalesOrderResultMap")
    List<SalesOrder> selectSalesSellerOrder(@Param("playerId") String playerId,@Param("start") Integer start,@Param("size")Integer size);

    /**
     *  玩家的购买请求订单：玩家是买家
     * @param playerId
     * @return
     */
    @Select("select * from sales_order where order_player_buyer = #{playerId}")
    List<SalesOrder> selectSalesBuyerOrder(String playerId);

    @Insert("insert into `sales_order`(" +
            "id,order_id,order_amount,order_buy_type," +
            "order_pay_type,order_pay_amount," +
            "order_player_buyer,order_player_seller," +
            "order_state,createtime)" +
            "values" +
            "(#{id},#{orderId},#{orderAmount},#{orderBuyType}," +
            "#{orderPayType},#{orderPayAmount}," +
            "#{orderPlayerBuyer},#{orderPlayerSeller}," +
            "#{orderState},#{createTime} ) ")
    int createSalesOrder(SalesOrder order);

    @Select("select * from sales_order where 1=1 and ")
    SalesOrder findSalesOrder();

    @Select("select * from `sales_order` where 1=1 and order_id = #{orderId} limit 1 ")
    @ResultMap("BaseSalesOrderResultMap")
    SalesOrder getSalesOrderByOrderId(String orderId);

    @Update("update `sales_order` set order_state=#{orderState} where 1=1 and order_player_buyer=#{orderPlayerBuyer}")
    int updateSalesOrder(SalesOrder order);

    @Update({"<script>" +
            "<foreach collection=\"orderList\" item=\"item\" separator=\";\">" +
            " UPDATE" +
            " `sales_order0`" +
            " SET order_state = #{item.orderState, jdbcType=TINYINT}, " +
            "  WHERE " +
            "  AND order_id = #{item.orderId, jdbcType=VARCHAR} " +
            "</foreach>" +
            "</script>"})
    void sendOrderMt(@Param("orderList") List<SalesOrder> orderList);

    @Select("select * from `sales_order` where 1=1 and order_player_buyer = #{playerId} limit 1 ")
    List<SalesOrder> getSalesOrderByBuyerPlayerId(String playerId);

    @Select("select * from `sales_order` where 1=1 and order_state = #{state} limit 1 ")
    @ResultMap("BaseSalesOrderResultMap")
    List<SalesOrder> getSalesOrdersByState(int state);

    @Select("select * from `sales_order` where 1=1 and order_state = '1' and order_player_buyer = #{playerId} limit 1 ")
    @ResultMap("BaseSalesOrderResultMap")
    SalesOrder getBuyerNoPayOrder(String playerId);

    @Select("select * from `sales_order` where 1=1 and order_state = #{status} and order_player_buyer = #{playerId}")
    @ResultMap("BaseSalesOrderResultMap")
    List<SalesOrder> selectPlayerSalesOrdersByState(@Param("playerId") String playerId, @Param("status") int status);

    @Select("select * from `sales_order` where 1=1 and order_state = '1' and order_player_buyer = #{playerId} limit 1 ")
    @ResultMap("BaseSalesOrderResultMap")
    List<SalesOrder> selectSalesSellerOrders(String playerId);
}
