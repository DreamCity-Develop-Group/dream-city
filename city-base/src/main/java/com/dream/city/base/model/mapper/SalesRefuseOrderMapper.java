package com.dream.city.base.model.mapper;

import com.dream.city.base.model.entity.SalesRefuseOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author wvv
 */
@Mapper
public interface SalesRefuseOrderMapper {



    SalesRefuseOrder getSalesRefuseOrderById(Integer id);

    List<SalesRefuseOrder> getSalesRefuseOrderList(SalesRefuseOrder record);

    @Results(id = "BaseSalesRefuseOrderResultMap", value = {
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
    @Select({"select * from `sales_refuse_order` where id = #{id}"})
    SalesRefuseOrder selectSalesRefuseOrderByPrimaryKey(Long key);


    /**
     *  玩家的购买请求订单：玩家是卖家
     * @param playerId
     * @return
     */
    @Select("select * from sales_refuse_order " +
            "where 1=1 and order_player_seller = #{playerId} " +
            "ORDER BY createtime DESC "+
            "limit #{start}, #{size} ")
    @ResultMap("BaseSalesRefuseOrderResultMap")
    List<SalesRefuseOrder> selectSalesSellerOrder(@Param("playerId") String playerId, @Param("start") Integer start, @Param("size") Integer size);

    /**
     *  玩家的购买请求订单：玩家是买家
     * @param playerId
     * @return
     */
    @Select("select * from sales_refuse_order where order_player_buyer = #{playerId}")
    List<SalesRefuseOrder> selectSalesBuyerOrder(String playerId);

    @Insert("insert into `sales_refuse_order`(" +
            "refuse_id,refuse_order_old,refuse_order_new," +
            "refuse_player_seller," +
            "refuse_player_buyer," +
            "create_time)" +
            "values" +
            "(#{refuseId},#{refuseOrderOld},#{refuseOrderNew}," +
            "#{refusePlayerSeller}," +
            "#{refusePlayerBuyer}," +
            "#{createTime} ) ")
    int createSalesRefuseOrder(SalesRefuseOrder order);

    @Select("select * from `sales_refuse_order` where 1=1 and refuse_order_old = #{orderId} limit 1 ")
    @ResultMap("BaseSalesRefuseOrderResultMap")
    SalesRefuseOrder getSalesRefuseOrderByOrderId(@Param("orderId") String orderId);

    @Update("update `sales_refuse_order` set order_state=#{orderState} " +
            " where 1=1 and " +
            " order_player_buyer=#{orderPlayerBuyer} " +
            " and order_id=#{orderId}")
    int updateSalesRefuseOrder(SalesRefuseOrder order);

    @Update({"<script>" +
            "<foreach collection=\"orderList\" item=\"item\" separator=\";\">" +
            " UPDATE" +
            " `sales_refuse_order`" +
            " SET order_state = #{item.orderState, jdbcType=TINYINT} " +
            "  WHERE 1=1 " +
            "  AND order_id = #{item.orderId, jdbcType=VARCHAR} " +
            "</foreach>" +
            "</script>"})
    void sendOrderMt(@Param("orderList") List<SalesRefuseOrder> orderList);

    @Select("select * from `sales_refuse_order` where 1=1 and order_player_buyer = #{playerId} ")
    @ResultMap("BaseSalesRefuseOrderResultMap")
    List<SalesRefuseOrder> getSalesRefuseOrderByBuyerPlayerId(String playerId);

    @Select("select * from `sales_refuse_order` where 1=1 and order_state = #{state} ")
    @ResultMap("BaseSalesRefuseOrderResultMap")
    List<SalesRefuseOrder> getSalesRefuseOrdersByState(int state);

    @Select("select * from `sales_refuse_order` where 1=1 and order_state = '1' and order_player_buyer = #{playerId} limit 1 ")
    @ResultMap("BaseSalesRefuseOrderResultMap")
    SalesRefuseOrder getBuyerNoPayOrder(String playerId);

    @Select("select * from `sales_refuse_order` where 1=1 and order_state = #{status} and order_player_buyer = #{playerId}")
    @ResultMap("BaseSalesRefuseOrderResultMap")
    List<SalesRefuseOrder> selectPlayerSalesRefuseOrdersByState(@Param("playerId") String playerId, @Param("status") int status);

    @Select("select * from `sales_refuse_order` where 1=1 and order_state = '1' and order_player_buyer = #{playerId} limit 1 ")
    @ResultMap("BaseSalesRefuseOrderResultMap")
    List<SalesRefuseOrder> selectSalesSellerOrders(String playerId);

    /**
     * 某卖家超时或拒绝的交易数量
     * @param sellerId
     * @param status
     * @return
     */
    @Select("select count(*) from `sales_refuse_order` where order_state = #{status} and order_player_buyer = #{buyer_id} and order_player_seller = #{sellerId} ")
    int selectSalesSellerRejectTimes(@Param("buyer_id") String buyer_id, @Param("sellerId") String sellerId, @Param("status") int status);

    @Select("select count(*) from `sales_refuse_order` where 1=1 and order_player_buyer = #{buyer} and order_player_seller = #{seller} ")
    Integer selectSalesBuyerRefuseOrders(@Param("buyer") String buyer, @Param("seller") String seller);
}
