package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.entity.InvestAllow;
import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.Player;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Wvv
 */
@Mapper
public interface InvestRuleMapper {
    int deleteByPrimaryKey(Integer ruleId);

    int insert(InvestRule record);

    int insertSelective(InvestRule record);

    InvestRule selectByPrimaryKey(Integer ruleId);

    int updateByPrimaryKeySelective(InvestRule record);

    int updateByPrimaryKey(InvestRule record);

    @Results(id = "BasePlayerResultMap", value = {
            @Result(property = "id", column = "rule_id", id = true),
            @Result(property = "ruleFlag", column = "rule_flag"),
            @Result(property = "ruleOpt", column = "rule_opt"),
            @Result(property = "ruleName", column = "rule_name"),
            @Result(property = "ruleDesc", column = "rule_desc"),
            @Result(property = "ruleItem", column = "rule_item"),
            @Result(property = "ruleRate", column = "rule_rate"),
            @Result(property = "ruleLevel", column = "rule_level"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    @Select({"select * from `invest_rule` where 1=1 and rule_id = #{ruleId}"})
    Player getPlayer(String ruleId);



    /**
     *  玩家的资金账户
     * @param playerId
     * @return
     */
    @Select("select * from `invest_rule` where 1=1 and  player_id = #{playerId}")
    InvestAllow getInvestAllowByPlayerId(String playerId);

    @Insert("insert into `invest_rule`(id,player_id,allowed,amount,create_time)values(#{id},#{playerId},#{allowed},#{amount},#{createTime}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertInvestAllow(InvestAllow allow);


    @Select("select * from `invest_rule` where 1=1 and  rule_item = #{itemId}")
    List<InvestRule> getRulesByItem(Integer itemId);


    @Results(id = "BaseCityInvestnResultMap", value = {
            @Result(property = "inId", column = "in_id", id = true),
            @Result(property = "inName", column = "in_name"),
            @Result(property = "inLimit", column = "in_limit"),
            @Result(property = "inStart", column = "in_start"),
            @Result(property = "inTax", column = "in_tax"),
            @Result(property = "inEarning", column = "in_earning"),
            @Result(property = "inEnd", column = "in_end"),
            @Result(property = "inImg", column = "in_img"),
            @Result(property = "isValid", column = "is_valid"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })


    /**
     * 获取单个投资
     *
     */
    @Select({"select * from `city_invest` where 1=1 and in_id = #{investId}"})
    CityInvest getCityInvest(Integer investId);


    /**
     * 获取所有投资项列表
     *
     *
     * @return
     */
    @Select("select * from `city_invest` where 1=1 ")
    List<CityInvest> getAllCityInvests();



    @Select({
            "<script>",
            "select",
            "*",
            "from `city_invest`",
            "where 1=1 and and player_id = #{playerId} and order_state in ",
            "<foreach collection='states' item='state' open='(' separator=',' close=')'>",
            "#{state}",
            "</foreach>",
            "</script>"
    })
    List<CityInvest> getSuccessCityInvestnsByPlayerId(@Param("playerId") String playerId, @Param("states") int[] states);

    @Select("select * from `city_invest` where 1=1 and order_invest_id = #{investId} and order_state=#{state}")
    List<CityInvest> getCityInvestsByCurrentDay(Integer investId, int state);

    /**
     * 更新订单
     *
     * @param order
     */
    @Select("update `city_invest` set order_state = #{orderState} where 1=1 and order_invest_id = #{investId} and order_id=#{orderId}")
    void updateOrder(CityInvest order);

    /**
     * 指更新订单状态
     *
     *
     * @param orderList
     */
    @Update({"<script>" +
            "<foreach collection=\"orderList\" item=\"item\" separator=\";\">" +
            " UPDATE" +
            " `city_invest`" +
            " SET order_state = #{item.orderState, jdbcType=TINYINT}, " +
            "  WHERE " +
            "  AND order_id = #{item.orderId, jdbcType=VARCHAR} " +
            "</foreach>" +
            "</script>"})
    void setOrdersState(@Param("orderList") List<CityInvest> orderList);

    @Select("select * from `invest_rule` where 1=1 and order_invest_id = #{investId} and order_state=#{state}")
    List<InvestRule> getInvestRuleByKey(Integer key);
}