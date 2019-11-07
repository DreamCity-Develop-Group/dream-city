package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.entity.InvestAllow;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.RuleItem;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface RuleItemMapper {
    int deleteByPrimaryKey(Integer itemId);

    int insert(RuleItem record);

    int insertSelective(RuleItem record);

    RuleItem selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(RuleItem record);

    int updateByPrimaryKey(RuleItem record);


    List<RuleItem> getRuleItemList(RuleItem record);

    List<RuleItem> getRuleItemFlagList(@Param("itemFlag") String itemFlag);


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

    @Select("select * from  `rule_item` where 1=1 and item_flag= #{key}")
    @Results(id = "BaseRuleItemResultMap", value = {
            @Result(property = "itemId", column = "item_id"),
            @Result(property = "itemName", column = "item_name"),
            @Result(property = "itemDesc", column = "item_desc"),
            @Result(property = "itemFlag", column = "item_flag"),
            @Result(property = "itemState", column = "item_state"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    RuleItem getInvestRuleItemByKey(String key);


    @Results(id = "BasePlayerResultMap", value = {
            @Result(property = "id", column = "item_id", id = true),
            @Result(property = "itemFlag", column = "item_flag"),
            @Result(property = "itemName", column = "item_name"),
            @Result(property = "itemDesc", column = "item_desc"),
            @Result(property = "itemState", column = "item_state"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    @Select({"select * from `rule_item` where 1=1 and player_id = #{playerId}"})
    Player getPlayer(String playerId);



    /**
     *  玩家的资金账户
     * @param playerId
     * @return
     */
    @Select("select * from `rule_item` where 1=1 and  player_id = #{playerId}")
    InvestAllow getInvestAllowByPlayerId(String playerId);

//    @Insert("insert into `rule_item`(id,player_id,allowed,amount,create_time)values(#{id},#{playerId},#{allowed},#{amount},#{createTime}) ")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    void insert(InvestAllow allow);

    @Select({"select item_id itemId,item_flag itemFlag,item_name itemName,item_desc itemDesc," +
            "item_state itemState,create_time createTime,update_time updateTime " +
            "from `rule_item` where 1=1 and item_flag = #{flag} and item_state=1"})
    RuleItem getRuleItemByFlag(String flag);


}