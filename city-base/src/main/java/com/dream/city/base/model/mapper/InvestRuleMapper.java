package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.InvestRule;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wvv
 */
@Repository
@Mapper
public interface InvestRuleMapper {
    int deleteByPrimaryKey(Integer ruleId);

    int insert(InvestRule record);

    int insertSelective(InvestRule record);

    InvestRule selectByPrimaryKey(Integer ruleId);

    int updateByPrimaryKeySelective(InvestRule record);

    int updateByPrimaryKey(InvestRule record);

    @Results(id = "BasePlayerResultMap1", value = {
            @Result(property = "ruleId", column = "rule_id", id = true),
            @Result(property = "ruleFlag", column = "rule_flag"),
            @Result(property = "ruleOpt", column = "rule_opt"),
            @Result(property = "ruleName", column = "rule_name"),
            @Result(property = "ruleDesc", column = "rule_desc"),
            @Result(property = "ruleItem", column = "rule_item"),
            @Result(property = "ruleRate", column = "rule_rate"),
            @Result(property = "ruleRatePre", column = "rule_rate_pre"),
            @Result(property = "ruleLevel", column = "rule_level"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })



    @Select({"select * from `invest_rule` where 1=1 and rule_id = #{ruleId}"})
    InvestRule getPlayer(String ruleId);

    /**
     *  玩家的资金账户
     * @param playerId
     * @return
     */
    @Select("select * from `invest_rule` where 1=1 and  player_id = #{playerId}")
    InvestRule getInvestAllowByPlayerId(String playerId);

    @Select("select * from `invest_rule` where 1=1 and  rule_item = #{itemId}")
    List<InvestRule> getRulesByItem(Integer itemId);

    /**
     * 获取单个投资
     *
     */
    @Select({"select * from `invest_rule` where 1=1 and in_id = #{investId}"})
    InvestRule getCityInvest(Integer investId);

    /**
     * 获取所有投资项列表
     * @return
     */
    @Select("select * from `invest_rule` where 1=1 ")
    List<InvestRule> getAllCityInvests();

    @Select({
            "<script>",
            "select",
            "*",
            "from `invest_rule`",
            "where 1=1 and and player_id = #{playerId} and order_state in ",
            "<foreach collection='states' item='state' open='(' separator=',' close=')'>",
            "#{state}",
            "</foreach>",
            "</script>"
    })
    List<InvestRule> getSuccessCityInvestnsByPlayerId(@Param("playerId") String playerId, @Param("states") int[] states);

    @Select("select * from `invest_rule` where 1=1 and order_invest_id = #{investId} and order_state=#{state}")
    List<InvestRule> getCityInvestsByCurrentDay(Integer investId, int state);

    /**
     * 更新订单
     *
     * @param order
     */
    @Select("update `invest_rule` set order_state = #{orderState} where 1=1 and order_invest_id = #{investId} and order_id=#{orderId}")
    void updateOrder(InvestRule order);

    /**
     * 指更新订单状态
     *
     *
     * @param orderList
     */
    @Update({"<script>" +
            "<foreach collection=\"orderList\" item=\"item\" separator=\";\">" +
            " UPDATE" +
            " `invest_rule`" +
            " SET order_state = #{item.orderState, jdbcType=TINYINT}, " +
            "  WHERE " +
            "  AND order_id = #{item.orderId, jdbcType=VARCHAR} " +
            "</foreach>" +
            "</script>"})
    void setOrdersState(@Param("orderList") List<InvestRule> orderList);

    @Select("select * from `invest_rule` where 1=1 and order_invest_id = #{investId} and order_state=#{state}")
    List<InvestRule> getInvestRuleByKey(Integer key);

    @Select("select * from `invest_rule` where 1=1 ")
    @ResultMap("BasePlayerResultMap1")
    List<InvestRule> selectRules();
}