package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.InvestOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Wvv
 */
@Mapper
public interface InvestOrderMapper {

    Integer deleteByPrimaryKey(Integer orderId);

    InvestOrder insertSelective(InvestOrder record);

    InvestOrder selectByPrimaryKey(InvestOrder record);

    Integer updateByPrimaryKeySelective(InvestOrder record);

    List<InvestOrder> getInvestOrders(InvestOrder record);

    int countOrdersByPayerIdInvestId(InvestOrder record);



    @Results(id = "BaseInvestOrderResultMap", value = {
            @Result(property = "orderId", column = "order_id", id = true),
            @Result(property = "orderInvestId", column = "order_invest_id"),
            @Result(property = "orderPlayerId", column = "order_player_id"),
            @Result(property = "orderAmount", column = "order_amount"),
            @Result(property = "orderState", column = "order_state"),
            @Result(property = "orderRepeat", column = "order_repeat"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    @Select({"select * from `invest_order` where 1=1 and order_id = #{orderId}"})
    InvestOrder getInvestOrder(String orderId);


    @Select("select * from `invest_order` where 1=1 and player_id = #{playerId} ")
    List<InvestOrder> getAllInvestOrdersByPlayerId(String playerId);

    @Select({
            "<script>",
            "select",
            "*",
            "from `invest_order`",
            "where 1=1 and and player_id = #{playerId} and order_state in ",
            "<foreach collection='states' item='state' open='(' separator=',' close=')'>",
            "#{state}",
            "</foreach>",
            "</script>"
    })
    List<InvestOrder> getSuccessInvestOrdersByPlayerId(@Param("playerId") String playerId, @Param("states") int[]states);

    @Select("select * from `invest_order` where 1=1 and order_invest_id = #{investId} and order_state=#{state}")
    List<InvestOrder> getInvestOrdersByCurrentDay(Integer investId,int state);

    @Select("select * from `invest_order` where 1=1 and order_invest_id = #{investId} and order_state=#{state} and order_repeat = 0 ")
    List<InvestOrder> getInvestOrdersNoRepeat(Integer investId,int state);

    @Select("select * from `invest_order` where 1=1 and order_invest_id = #{investId} and order_state=#{state} and order_repeat = 1 ")
    List<InvestOrder> getInvestOrdersRepeat(Integer investId,int state);

    /**
     * 更新订单
     *
     * @param order
     */
    @Update("update `invest_order` set order_state = #{orderState} where 1=1 and order_invest_id = #{investId} and order_id=#{orderId}")
    void updateOrder(InvestOrder order);


    /**
     * 指更新订单状态
     *
     *
     * @param orderList
     */
    @Update({"<script>" +
            "<foreach collection=\"orderList\" item=\"item\" separator=\";\">" +
            " UPDATE" +
            " `invest_order`" +
            " SET order_state = #{item.orderState, jdbcType=TINYINT}, " +
            "  WHERE " +
            "  AND order_id = #{item.orderId, jdbcType=VARCHAR} " +
            "</foreach>" +
            "</script>"})
    void setOrdersState(@Param("orderList") List<InvestOrder> orderList);

    /**
     * 找出对应时间的订单数据
     *
     * @param investId
     * @param state
     * @param start
     * @param end
     * @return
     */
    @Select("select * from `invest_order` where 1=1 and order_invest_id = #{investId} and order_state=#{state} and create_time BETWEEN #{start} and #{end} ")
    List<InvestOrder> getInvestOrdersAmountByDayInterval(Integer investId,int state,String start,String end);

    @Select({
            "<script>",
            "select",
            "*",
            "from `invest_order`",
            "where 1=1 and order_invest_id = #{investId} and order_state in ",
            "<foreach collection='states' item='state' open='(' separator=',' close=')'>",
            "#{state}",
            "</foreach>",
            "</script>"
    })
    int getInvestOrdersSum(Integer investId,int[]states);

    @Select({
            "<script>",
            "select",
            "*",
            "from `invest_order`",
            "where 1=1 and order_invest_id = #{investId} and order_state in ",
            "<foreach collection='states' item='state' open='(' separator=',' close=')'>",
            "#{state}",
            "</foreach>",
            " limit #{start},#{end}",
            "</script>"
    })
    List<InvestOrder> getInvestOrdersByCurrent(Integer inId, int[] states, int start, int end);

}