package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.req.InvestOrderReq;
import com.dream.city.base.model.resp.InvestOrderResp;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Wvv
 */
@Mapper
public interface InvestOrderMapper {

    Integer deleteByPrimaryKey(Integer orderId);

    Integer insertSelective(InvestOrder record);

    InvestOrderResp selectByPrimaryKey(Integer orderId);

    Integer updateByPrimaryKeySelective(InvestOrder record);

    List<InvestOrderResp> getInvestOrders(InvestOrderReq record);

    int countOrdersByPayerIdInvestId(InvestOrder record);

    Integer updateOrderStateById(InvestOrder record);

    InvestOrder getOrderByPlayerIdInvestId(InvestOrder record);


    @Results(id = "BaseInvestOrderResultMap", value = {
            @Result(property = "orderId", column = "order_id", id = true),
            @Result(property = "orderInvestId", column = "order_invest_id"),
            @Result(property = "orderPayerId", column = "order_payer_id"),
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

    @Select("select * from `invest_order` where order_state=#{state}")
    @ResultMap("BaseInvestOrderResultMap")
    List<InvestOrder> getInvestOrdersByState(@Param("state") Integer state);

    @Select({
            "<script>",
            " select",
            " *",
            " from `invest_order`",
            " where 1=1 and order_payer_id = #{playerId} and order_state in ",
            " <foreach collection='states' item='state' open='(' separator=',' close=')'>",
            " #{state}",
            " </foreach>",
            "</script>"
    })
    @ResultMap("BaseInvestOrderResultMap")
    List<InvestOrder> getSuccessInvestOrdersByPlayerId(@Param("playerId") String playerId, @Param("states") int[]states);

    @Select("select * from `invest_order` where 1=1 and order_invest_id = #{investId} and order_state=#{state}")
    //List<InvestOrder> getInvestOrdersByCurrentDay(Integer investId,int state);
    @ResultMap("BaseInvestOrderResultMap")
    List<InvestOrder> getInvestOrdersByCurrentDay(@Param("investId") Integer investId,@Param("state") Integer state);

    //所有的第一次投资的订单
    @Select("select * from `invest_order` where 1=1 and order_invest_id = #{investId} and order_state=#{state} and order_repeat = 0 ")
    List<InvestOrder> getInvestOrdersNoRepeat(Integer investId,int state);
    //所有的第一次投资的订单数量
    @Select("select count(0) from `invest_order` where 1=1 and order_invest_id = #{investId} and order_state='5' and order_repeat = 0 ")
    Integer getInvestOrdersNoRepeatCount(Integer investId);


    //投资物业的前20%玩家
    @Select("select * from `invest_order` where 1=1 and order_invest_id = #{investId} and order_state='5' and order_repeat = 0 order by create_time asc limit #{limit}")
    @ResultMap("BaseInvestOrderResultMap")
    List<InvestOrder> getInvestOrdersNoRepeatReload(@Param("investId") Integer investId,@Param("limit") Integer limit);

    @Select("select * from `invest_order` where 1=1 and order_invest_id = #{investId} and order_state=#{state} and order_repeat = 1 ")
    List<InvestOrder> getInvestOrdersRepeat(Integer investId,int state);


    @Select("select * from `invest_order` where 1=1 and order_invest_id=#{investId} and order_state='5' order by create_time asc limit #{limit}")
    @ResultMap("BaseInvestOrderResultMap")
    List<InvestOrder> getInvestLongOrdersReload(@Param("investId") Integer investId,@Param("limit") Integer limit);


    @Select("select io.* from invest_order as io , player_likes as pl where pl.liked_invest_id = io.order_invest_id and pl.liked_invest_id = #{investId} order by pl.liked_get_total desc limit #{limit} ")
    @ResultMap("BaseInvestOrderResultMap")
    List<InvestOrder>  getLikesGatherReload(@Param("investId") Integer investId,@Param("limit") Integer limit);

    /**
     * 更新订单
     *
     * @param order
     */
    //有错，不要用。There is no getter for property named 'investId'
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
            //" SET order_state = #{item.orderState, jdbcType=TINYINT}, " +
            " SET order_state = #{item.orderState, jdbcType=TINYINT} " +
            "  WHERE 1 = 1" +
            "  AND order_id = #{item.orderId, jdbcType=INTEGER} " +
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
    @ResultMap("BaseInvestOrderResultMap")
    List<InvestOrder> getInvestOrdersAmountByDayInterval(@Param("investId") Integer investId,@Param("state") int state,@Param("start") String start,@Param("end") String end);

    @Select({
            "<script>",
            "select",
            "count(*)",
            "from `invest_order`",
            "where 1=1 and order_invest_id = #{investId} and order_state in ",
            "<foreach collection='states' item='state' open='(' separator=',' close=')'>",
            "#{state}",
            "</foreach>",
            "</script>"
    })
    int getInvestOrdersSum(@Param("investId") Integer investId, @Param("states") int[]states);

    @Select("select count(*) from `invest_order` where 1=1 and order_invest_id = #{investId} and order_state=#{state}")
    int getInvestOrdersSumReload(@Param("investId") Integer investId, @Param("states") int states);

    @Select({
            "<script>",
            "select",
            "*",
            "from `invest_order`",
            "where 1=1 and order_invest_id = #{inId} and order_state in ",
            "<foreach collection='states' item='state' open='(' separator=',' close=')'>",
            "#{state}",
            "</foreach>",
            " limit #{start},#{end}",
            "</script>"
    })

    List<InvestOrder> getInvestOrdersByCurrent(@Param("inId") Integer inId, @Param("states") int[] states, @Param("start") int start, @Param("end") int end);


    @Select("select * from `invest_order` where 1=1 and order_invest_id = #{inId} and order_state = #{state}")
    @ResultMap("BaseInvestOrderResultMap")
    List<InvestOrder> getInvestOrdersByCurrentReload(@Param("inId") Integer inId, @Param("state") int states);

}