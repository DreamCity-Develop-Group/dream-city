package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.PlayerAccountLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlayerAccountLogMapper {

    @Results(id = "playerAccountLogMap", value = {
            @Result(property = "Id", column = "id", id = true),
            @Result(property = "accId", column = "acc_id"),
            @Result(property = "playerId", column = "player_id"),
            @Result(property = "address", column = "address"),
            @Result(property = "amountMt", column = "amount_mt"),
            @Result(property = "accountUsdt", column = "account_usdt"),
            @Result(property = "type", column = "type"),
            @Result(property = "desc", column = "desc"),
            @Result(property = "create_time", column = "createTime"),
    })
    @Select("select * from player_account_log where 1=1 and id=#{id}")
    PlayerAccountLog getPlayerAccountLogById(int id);

    @Insert("insert into player_account_log values(#{id},#{accId},#{playerId},#{address},#{amountMt},#{amountUsdt},#{type},#{desc},#{createTime})")
    Integer insertSelective(PlayerAccountLog record);

    @Select("select * from player_account_log where id=#{accId}")
    PlayerAccountLog selectByPrimaryKey(Integer accId);

    @Insert("insert into player_account_log values(#{id},#{accId},#{playerId},#{address},#{amountMt},#{amountUsdt},#{type},#{desc},#{createTime})")
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