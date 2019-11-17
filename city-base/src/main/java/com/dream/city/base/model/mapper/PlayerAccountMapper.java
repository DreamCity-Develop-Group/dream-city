package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.TradeDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Wvv
 */
@Mapper
@Repository
public interface PlayerAccountMapper {

    @Results(id = "BaseCityAccountResultMap", value = {
            @Result(property = "accId", column = "acc_id", id = true),
            @Result(property = "accPlayerId", column = "acc_player_id"),
            @Result(property = "accAddr", column = "acc_addr"),
            @Result(property = "accPass", column = "acc_pass"),
            @Result(property = "accUsdt", column = "acc_usdt"),
            @Result(property = "accUsdtAvailable", column = "acc_usdt_available"),
            @Result(property = "accUsdtFreeze", column = "acc_usdt_freeze"),
            @Result(property = "accMt", column = "acc_mt"),
            @Result(property = "accMtAvailable", column = "acc_mt_available"),
            @Result(property = "accMtFreeze", column = "acc_mt_freeze"),
            @Result(property = "accIncome", column = "acc_income"),
            @Result(property = "version", column = "version"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),

    })
    @Select("select * from player_account where 1=1 and  acc_player_id = #{playerId} limit 1 ")
     PlayerAccount getAccountByPlayerId(@Param("playerId")String playerId);

    @Select("select acc_id accId, from player_account where 1=1 and acc_id=#{accId}")
    PlayerAccount findPlayerAccount(@Param("accid")int accid);



    /**
     *  玩家的资金账户
     * @param playerId
     * @return
     */
    @Select("select * from player_account where 1=1 and  acc_player_id = #{playerId}")
    @ResultMap("BaseCityAccountResultMap")
    PlayerAccount getPlayerAccount(@Param("playerId") String playerId);

    /**
     * 通过地址找账户
     * @param address
     * @return
     */
    @Select("select * from player_account where 1=1 and  acc_addr = #{address}")
    @ResultMap("BaseCityAccountResultMap")
    PlayerAccount getPlayerAccountByAddr(@Param("address")String address);

    @Update("update player_account set acc_usdt = acc_usdt-#{payAmount} ,acc_usdt_availble=acc_usdt_availble-#{payAmount} where 1=1 adnd acc_player_id=#{playerId}")
    void subtractAmount(@Param("payAmount")BigDecimal payAmount, @Param("playerId")String playerId);

    @Insert("insert into `player_account`(acc_id,acc_player_id,acc_addr)value(0,#{accPlayerId},#{accAddr})")
    void createAccount(@Param("accPlayerId")String accPlayerId,@Param("accAddr")String accAddr);


    @Select("select * from player_account where 1=1 and  acc_player_id = #{playerId}")
    @ResultMap("BaseCityAccountResultMap")
    PlayerAccount getPlayerAccountByPlayerId(@Param("playerId")String playerId);



    @Update({"<script>" +
            "<foreach collection=\"accounts\" item=\"item\" separator=\";\">" +
            " UPDATE" +
            " `player_account`" +
            " SET acc_usdt = #{item.accUsdt}, " +
            "  acc_usdt_freeze = #{item.accUsdtFreeze}, " +
            "  acc_usdt_available = #{item.accUsdtAvailable}, " +
            "  acc_mt = #{item.accMt}, " +
            "  acc_mt_freeze = #{item.accMtFreeze}, " +
            "  acc_mt_available = #{item.accMtAvailable}  " +
            "  WHERE 1=1 " +
            "  AND acc_player_id = #{item.accPlayerId} " +
            "</foreach>" +
            "</script>"})

    void updatePlayerAccounts(@Param("accounts") List<PlayerAccount> accounts);


    @Update({" UPDATE" +
            " `player_account`" +
            "  SET acc_usdt = #{accUsdt}, " +
            "  acc_usdt_freeze = #{accUsdtFreeze}, " +
            "  acc_usdt_available = #{accUsdtAvailable}, " +

            "  acc_mt = #{accMt}, " +
            "  acc_mt_freeze = #{accMtFreeze}, " +
            "  acc_mt_available = #{accMtAvailable}  " +
            "  WHERE 1=1 " +
            "  AND acc_player_id = #{accPlayerId} "})
    int updatePlayerAccount(PlayerAccount payAmount);


    @Insert("insert into `player_account`(" +
            "  acc_id ," +
            "  acc_player_id ," +
            "  acc_addr ," +
            "  acc_usdt , " +
            "  acc_usdt_freeze , " +
            "  acc_usdt_available , " +
            "  acc_mt , " +
            "  acc_mt_freeze ," +
            "  acc_mt_available " +
            ")value(0,#{accPlayerId},#{accAddr},#{accUsdt},#{accUsdtFreeze},#{accUsdtAvailable},#{accMt},#{accMtFreeze},#{accMtAvailable})")
    void insertAccount(PlayerAccount payAmount);
}