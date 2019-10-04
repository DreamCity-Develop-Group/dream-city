package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.PlayerAccountLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlayerAccountLogMapper {
    @Insert("insert into player_account_log values(#{id},#{playerId},#{address},#{amountMt},#{amountUsdt},#{type},#{desc},#{createTime})")
    Integer insertSelective(PlayerAccountLog record);

    @Select("select * from player_account_log where id=#{accId}")
    PlayerAccountLog selectByPrimaryKey(Integer accId);

    @Insert("insert into player_account_log values(#{id},#{playerId},#{address},#{amountMt},#{amountUsdt},#{type},#{desc},#{createTime})")
    void insert(PlayerAccountLog account);
    /**
     *  玩家的资金账户
     * @param playerId
     * @return
     */
    @Select("select * from player_account_log where 1=1 and  player_id = #{playerId}")
    PlayerAccountLog getPlayerAccountLog(String playerId);

    @Update("update player_account_log set amount_mt=#{amountMt},amount_usdt#{amountUsdt},type=#{type},desc=#{desc},create_time=#{createTime} where 1=1 adnd acc_player_id=#{playerId}")
    void subtractAmount(String playerId);

    @Insert("insert into `player_account`(acc_id,acc_player_id,acc_addr)value(0,#{playerId},#{address})")
    void update(@Param("playerId") String playerId, @Param("address") String address);

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
    void updateBuyerAccount(@Param("accounts") List<PlayerAccountLog> accounts);

}