package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.PlayerEarning;
import com.dream.city.base.model.req.EarningReq;
import com.dream.city.base.model.resp.PlayerEarningResp;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 玩家提现收入
 * @author Wvv
 */
@Mapper
public interface PlayerEarningMapper {

    Integer deleteByPrimaryKey(Integer earnId);

    Integer insertSelective(PlayerEarning record);

    PlayerEarning selectByPrimaryKey(Integer earnId);

    PlayerEarningResp getPlayerEarning(PlayerEarning record);

    List<PlayerEarning> selectPlayerEarningList(PlayerEarning record);

    List<PlayerEarningResp> getEarningList(EarningReq record);

    Integer updateByPrimaryKeySelective(PlayerEarning record);


    @Results(id = "BasePlayerEarningResultMap", value = {
            @Result(property = "earnId", column = "earn_id", id = true),
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "earnInvestId", column = "earn_invest_id"),
            @Result(property = "inType", column = "in_type"),
            @Result(property = "earnPlayerId", column = "earn_player_id"),
            @Result(property = "earnMax", column = "earn_max"),
            @Result(property = "earnCurrent", column = "earn_current"),
            @Result(property = "earnPersonalTax", column = "earn_personal_tax"),
            @Result(property = "earnEnterpriseTax", column = "earn_enterprise_tax"),
            @Result(property = "earnQuotaTax", column = "earn_quota_tax"),
            @Result(property = "withdrewTotal", column = "withdrew_total"),
            @Result(property = "dropTotal", column = "drop_total"),
            @Result(property = "withdrewTimes", column = "withdrew_times"),
            @Result(property = "isWithdrew", column = "is_withdrew"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })

    /**
     *查找当前玩家当前项目的收益
     *
     * @param playerId
     * @param investId
     * @return
     */
//    @Select("select * from player_earning where 1=1 and earn_player_id=#{playerId} and earn_invest_id=#{investId}")
//    PlayerEarning getPlayerEarning(@Param("playerId") String playerId, @Param("investId")Integer investId);

    /**
     *
     * @param withdrewState
     * @param afterHours
     * @return
     */

    @Select("select * from player_earning where is_withdrew=#{withdrewState} and DATEDIFF(now(),date_add(`update_time`, interval #{afterHours} hour)) > 0")
    //@ResultMap("BasePlayerEarningResultMap")
    List<PlayerEarning> getPlayerEarningByAfterHours(@Param("withdrewState") Integer withdrewState, @Param("afterHours") Integer afterHours);

}