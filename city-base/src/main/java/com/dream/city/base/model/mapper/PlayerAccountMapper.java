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

    PlayerAccount getPlayerAccount(PlayerAccountReq record);

    PlayerAccount getPlayerAccountByPlayerId(String playerId);

    /**
     * 获取平台账户
     * @param record
     * @return
     */
    List<PlayerAccount> getPlatformAccounts(PlayerAccountReq record);

    Integer updateByPlayerId(PlayerAccount record);

    List<PlayerAccount> getPlayerAccountList(PlayerAccount record);


    @Results(id = "BasePlayerAccountResultMap", value = {
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
    @ResultMap("BasePlayerAccountResultMap")
    @Select("select * from player_account where 1=1 and  acc_player_id = #{playerId}")
    PlayerAccount getPlayerAccount(String playerId);

    @Update("update player_account set acc_usdt = acc_usdt-#{payAmount} ,acc_usdt_availble=acc_usdt_availble-#{payAmount} where 1=1 adnd acc_player_id=#{playerId}")
    void subtractAmount(BigDecimal payAmount, String playerId);

    @Insert("insert into `player_account`(acc_id,acc_player_id,acc_addr)value(0,#{playerId},#{address})")
        //@Options(useGeneratedKeys = true, keyProperty = "acc_id")
    void createAccount(String playerId,String address);

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