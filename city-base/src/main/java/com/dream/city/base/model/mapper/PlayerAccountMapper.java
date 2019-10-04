package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.req.PlayerAccountReq;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
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
    List<PlayerAccount> getPlatformAccounts(PlayerAccountReq record);


    List<PlayerAccount> getPlayerAccountList(PlayerAccount record);

    /**
     *  玩家的资金账户
     * @param playerId
     * @return
     */
    @Select("select * from player_account where 1=1 and  acc_player_id = #{playerId}")
    PlayerAccount getPlayerAccount(String playerId);

    @Update("update player_account set acc_usdt = acc_usdt-#{payAmount} ,acc_usdt_availble=acc_usdt_availble-#{payAmount} where 1=1 adnd acc_player_id=#{playerId}")
    void subtractAmount(BigDecimal payAmount, String playerId);

    @Insert("insert into `player_account`(acc_id,acc_player_id,acc_addr)value(0,#{playerId},#{address})")
    void createAccount(@Param("playerId")String playerId,@Param("address")String address);

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