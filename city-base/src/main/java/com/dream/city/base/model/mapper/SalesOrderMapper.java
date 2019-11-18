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

    @Select("select * from `sales_order` where 1=1 and order_id = #{orderId} limit 1 ")
    @ResultMap("BaseSalesOrderResultMap")
    SalesOrder getSalesOrderByOrderId(String orderId);

    @Update("update `sales_order` set order_state=#{orderState} " +
            " where 1=1 and " +
            " order_player_buyer=#{orderPlayerBuyer} " +
            " and order_id=#{orderId}")
    int updateSalesOrder(SalesOrder order);

    @Update({"<script>" +
            "<foreach collection=\"orderList\" item=\"item\" separator=\";\">" +
            " UPDATE" +
            " `sales_order`" +
            " SET order_state = #{item.orderState, jdbcType=TINYINT} " +
            "  WHERE 1=1 " +
            "  AND order_id = #{item.orderId, jdbcType=VARCHAR} " +
            "</foreach>" +
            "</script>"})
    void sendOrderMt(@Param("orderList") List<SalesOrder> orderList);

    @Select("select * from `sales_order` where 1=1 and order_player_buyer = #{playerId} ")
    @ResultMap("BaseSalesOrderResultMap")
    List<SalesOrder> getSalesOrderByBuyerPlayerId(String playerId);

    @Select("select * from `sales_order` where 1=1 and order_state = #{state} ")
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

    /**
     * 某卖家超时或拒绝的交易数量
     * @param sellerId
     * @param status
     * @return
     */
    @Select("select count(*) from `sales_order` where order_state = #{status} and order_player_buyer = #{buyer_id} and order_player_seller = #{sellerId} ")
    int selectSalesSellerRejectTimes(@Param("buyer_id") String buyer_id, @Param("sellerId") String sellerId, @Param("status") int status);

    @Select("select count(*) from `sales_order` where 1=1 and order_player_buyer = #{buyer} and order_player_seller = #{seller} ")
    List<SalesOrder> selectSalesBuyerRefuseOrders(@Param("buyer") String buyer, @Param("seller")String seller);


    /**
     *
     *
     * 查询所有已经超时的并且支付成功 订单
     * @return
     */
    @Select("select * from `sales_order` where 1=1 and order_state='3' and updatetime < #{time}")
    @ResultMap("BaseSalesOrderResultMap")
    List<SalesOrder> getOverTimeOrder(@Param("time")String time);

    /**
     * 更改订单状态为已超时
     * @return
     */
    @Update("update `sales_order` set  order_state='4' where order_id=#{orderId} and order_state='3' ")
    int updateOverTimeOrder(@Param("orderId")String orderId);
}
