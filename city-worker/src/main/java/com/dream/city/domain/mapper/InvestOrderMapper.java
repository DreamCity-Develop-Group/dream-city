package com.dream.city.domain.mapper;

import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.SalesOrder;
import com.dream.city.base.model.enu.InvestStatus;
import com.dream.city.base.model.enu.OrderState;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wvv
 */
@Repository
public interface InvestOrderMapper {


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

    /**
     * 更新订单
     *
     * @param order
     */
    @Select("update `invest_order` set order_state = #{orderState} where 1=1 and order_invest_id = #{investId} and order_id=#{orderId}")
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
}
