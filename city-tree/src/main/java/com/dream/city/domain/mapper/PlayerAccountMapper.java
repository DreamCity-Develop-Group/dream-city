package com.dream.city.domain.mapper;

import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.SalesOrder;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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

    @Update("update player_account set acc_usdt = acc_usdt-#{payAmount} ,acc_usdt_availble=acc_usdt_availble-#{payAmount} where 1=1 adnd acc_player_id=#{playerId}")
    void subtractAmount(BigDecimal payAmount, String playerId);

    @Update({"<script>" +
            "<foreach collection=\"accounts\" item=\"item\" separator=\";\">" +
            " UPDATE" +
            " `player_account`" +
            " SET acc_usdt = #{item.accUsdt, jdbcType=VARCHAR}, " +
            "  acc_usdt_available = #{item.accUsdtAvailable, jdbcType=VARCHAR}, " +
            "  WHERE 1=1 " +
            "  AND message_player_id = #{item.accPlayerId, jdbcType=VARCHAR} " +
            "</foreach>" +
            "</script>"})
    void updateBuyerAccount(@Param("accounts") List<PlayerAccount> accounts);
}
