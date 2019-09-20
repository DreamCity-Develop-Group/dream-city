package com.dream.city.domain.mapper;

import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.SalesOrder;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wvv
 */
@Repository
public interface PlayerAccountMapper {

    @Results(id = "BasePlayerAccountResultMap", value = {
            @Result(property = "accId", column = "id", id = true),
            @Result(property = "accPlayerId", column = "acc_player_id"),
            @Result(property = "accUsdt", column = "acc_usdt"),
            @Result(property = "accUsdtAvailable", column = "acc_usdt_available"),
            @Result(property = "accUsdtFreeze", column = "acc_usdt_freeze"),
            @Result(property = "accMt", column = "acc_mt"),
            @Result(property = "accMtAvailable", column = "acc_mt_available"),
            @Result(property = "accMtFreeze", column = "acc_mt_freeze"),
            @Result(property = "createTime", column = "createtime"),
            @Result(property = "updateTime", column = "updatetime")
    })
    @Select({"select * from `sales_order` where 1=1 and id = #{id}"})
    PlayerAccount selectPlayerAccountByPrimaryKey(Long key);



    /**
     *  玩家的资金账户
     * @param playerId
     * @return
     */
    @Select("select * from player_account where 1=1 and  acc_player_id = #{playerId}")
    PlayerAccount getPlayerAccount(String playerId);


}
