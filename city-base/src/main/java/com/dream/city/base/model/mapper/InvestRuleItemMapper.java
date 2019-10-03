package com.dream.city.base.model.mapper;

import com.dream.city.base.model.entity.InvestAllow;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.RuleItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author wvv
 */
@Repository
public interface InvestRuleItemMapper {

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

    @Insert("insert into `rule_item`(id,player_id,allowed,amount,create_time)values(#{id},#{playerId},#{allowed},#{amount},#{createTime}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(InvestAllow allow);

    @Select({"select * from `rule_item` where 1=1 and item_flag = #{flag} and item_state=1"})
    RuleItem getRuleItemByFlag(String flag);
}
