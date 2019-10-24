package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.EarnIncomeLog;
import com.dream.city.base.model.entity.Genesis;
import com.dream.city.base.model.entity.PlayerAccountLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface GenesisMapper {

    @Results(id = "GenesisRuleBaseMap", value = {
            @Result(property = "playerId", column = "player_id",id = true),
            @Result(property = "playerName", column = "player_name"),
            @Result(property = "accAddr", column = "acc_addr"),
            @Result(property = "accMt", column = "acc_mt"),
            @Result(property = "mtQuantum", column = "mt_quantum"),
            @Result(property = "superior", column = "superior"),
            @Result(property = "createTime", column = "createTime")
    })

    @Update("replace into genesis values(#{playerId},#{playerName},#{accAddr},#{accMt},#{mtQuantum},#{superior},#{createTime})")
    Integer updateGenesis(@Param("playerId") String playerId, @Param("accAddr") String accAddr);

    @Select("select * from `genesis`  where 1=1")
    List<Genesis> getListGenesis(Genesis genesis);

    @Select("select * from genesis where player_id = #{playerId}")
    Genesis getGenesis(@Param("playerId") String playerId);

    @Insert("insert into genesis values(#{playerId},#{playerName},#{accAddr},#{accMt},#{mtQuantum},#{superior},#{createTime})")
    Integer add(Genesis genesis);


    //List<Genesis> getList(Genesis genesis);


}
