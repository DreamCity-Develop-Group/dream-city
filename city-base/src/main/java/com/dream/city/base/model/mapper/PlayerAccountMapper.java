package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.req.PlayerAccountReq;
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
    Integer deleteByPrimaryKey(Integer accId);
    Integer insertSelective(PlayerAccount record);
    PlayerAccount updateByPrimaryKey(Integer accId);
    void updateByPrimaryKeySelective(PlayerAccount account);
    void insert(PlayerAccount account);
    PlayerAccount selectByPrimaryKey(Integer accId);
    /**
     * 获取平台账户
     * @param record
     * @return
     */
    List<PlayerAccount> getPlatformAccounts(PlayerAccount record);

    Integer updateByPlayerId(PlayerAccount record);


    /**
     * 获取平台账户
     * @param record
     * @return
     */
    List<PlayerAccount> getPlatformAccounts(PlayerAccountReq record);
    List<PlayerAccount> getPlayerAccountList(PlayerAccount record);


    @Results(id = "BaseCityAccountResultMap", value = {
            @Result(property = "accId", column = "acc_id", id = true),
            @Result(property = "accPlayerId", column = "acc_player_id", id = true),
            @Result(property = "accAddr", column = "acc_addr", id = true),
            @Result(property = "accPass", column = "acc_pass", id = true),
            @Result(property = "accUsdt", column = "acc_usdt", id = true),
            @Result(property = "accUsdtAvailable", column = "acc_usdt_available", id = true),
            @Result(property = "accUsdtFreeze", column = "acc_usdt_freeze", id = true),
            @Result(property = "accMt", column = "acc_mt", id = true),
            @Result(property = "accMtAvailable", column = "acc_mt_available", id = true),
            @Result(property = "accMtFreeze", column = "acc_mt_freeze", id = true),
            @Result(property = "accIncome", column = "acc_income", id = true),
            @Result(property = "createTime", column = "create_time", id = true),
            @Result(property = "updateTime", column = "update_time", id = true),

    })
    @Select("select * from player_account where 1=1 and  acc_player_id = #{playerId} limit 1 ")
     PlayerAccount getAccountByPlayerId(String playerId);

    @Select("select acc_id accId, from player_account where 1=1 and acc_id=#{accId}")
    PlayerAccount findPlayerAccount(int accid);



    /**
     *  玩家的资金账户
     * @param playerId
     * @return
     */
    @Select("select * from player_account where 1=1 and  acc_player_id = #{playerId}")
    @ResultMap("BaseCityAccountResultMap")
    PlayerAccount getPlayerAccount(String playerId);

    /**
     * 通过地址找账户
     * @param address
     * @return
     */
    @Select("select * from player_account where 1=1 and  acc_addr = #{address}")
    @ResultMap("BaseCityAccountResultMap")
    PlayerAccount getPlayerAccountByAddr(String address);

    @Update("update player_account set acc_usdt = acc_usdt-#{payAmount} ,acc_usdt_availble=acc_usdt_availble-#{payAmount} where 1=1 adnd acc_player_id=#{playerId}")
    void subtractAmount(BigDecimal payAmount, String playerId);

    @Insert("insert into `player_account`(acc_id,acc_player_id,acc_addr)value(0,#{playerId},#{address})")
    void createAccount(@Param("playerId")String playerId,@Param("address")String address);


    @Select("select * from player_account where 1=1 and  acc_player_id = #{playerId}")
    @ResultMap("BaseCityAccountResultMap")
    PlayerAccount getPlayerAccountByPlayerId(String playerId);


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
}