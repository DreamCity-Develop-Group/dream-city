package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.entity.PlayerLikes;
import com.dream.city.base.model.req.PlayerLikesReq;
import com.dream.city.base.model.resp.PlayerLikesResp;
import org.apache.ibatis.annotations.*;

import javax.jws.WebParam;
import java.util.List;

@Mapper
public interface PlayerLikesMapper {

    Integer deleteByPrimaryKey(Integer likedId);


    Integer insertSelective(PlayerLikesReq record);

    Integer insert(PlayerLikes record);


    PlayerLikes selectByPrimaryKey(Integer likedId);


    Integer updateByPrimaryKeySelective(PlayerLikesReq record);


    /**
     * 玩家点赞总数
     * @param record
     * @return
     */
    Integer playerLikesCount(PlayerLikesReq record);

    /**
     * 点赞项目
     * @param record
     * @return
     */
    List<PlayerLikesResp> playerLikesList(PlayerLikesReq record);


    @Results(id = "BaseInvestInvestResultMap", value = {
            @Result(property = "likedId", column = "liked_id", id = true),
            @Result(property = "likeInvestId", column = "liked_invest_id"),
            @Result(property = "likedPlayerId", column = "liked_player_id"),
            @Result(property = "likedGetTotal", column = "liked_get_total"),
            @Result(property = "likedSetTotal", column = "liked_set_total"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    @Select({"select * from `invest_order` where 1=1 and order_id = #{orderId}"})
    PlayerLikes getInvestOrder(String orderId);


    @Select("select * from `invest_order` where 1=1 and player_id = #{playerId} ")
    List<InvestOrder> getAllInvestOrdersByPlayerId(String playerId);

    @Select("select liked_id, liked_player_id, liked_invest_id, liked_set_total, " +
            " liked_get_total, DATE_FORMAT(create_time,'%Y-%m-%d %h:%m:%s') create_time, " +
            " DATE_FORMAT(update_time,'%Y-%m-%d %h:%m:%s') update_time" +
            " from `player_likes` where 1=1 and liked_player_id = #{playerId,jdbcType=VARCHAR} " +
            " and liked_invest_id=#{investId,jdbcType=INTEGER} ")
    @ResultMap(value = "BaseInvestInvestResultMap")
    PlayerLikes getLikesByInvest(@Param("playerId")String playerId, @Param("investId")int investId);

    @Select({
            "<script>",
            "select",
            "sum(liked_get_total)",
            "from `player_likes`",
            "where 1=1 and liked_player_id = #{playerId} and order_state in ",
            "<foreach collection='states' item='state' open='(' separator=',' close=')'>",
            "#{state}",
            "</foreach>",
            "</script>"
    })
    List<PlayerLikes> getLikesByIds(@Param("playerId") String playerId, @Param("states") int[] states);

    @Select("select sum(liked_get_total) from `player_likes` where 1=1 and  liked_player_id = #{playerId}")
    @ResultMap(value = "BaseInvestInvestResultMap")
    int getLikesGetByPlayerId(String playerId);

    @Select("select * from `invest_order` where 1=1 and player_id = #{playerId} ")
    @ResultMap(value = "BaseInvestInvestResultMap")
    int getLikesById(String orderPayerId);

    @Select("select liked_player_id from `player_likes` where  liked_invest_id = #{investId} ")
    @ResultMap(value = "BaseInvestInvestResultMap")
    List<String> getPlayerIdByInvestId(Integer investId);


    int deleteByPrimaryKey(Long id);

    int insertSelective(PlayerLikes record);

    PlayerLikes selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlayerLikes record);

    int updateByPrimaryKey(PlayerLikes record);

    List<PlayerLikes> getInvestLikes(String playerId);

    List<PlayerLikes> getLikes(String playerId);


    /**
     * 获取对用项目点赞人数
     * @return
     */
    @Select("SELECT like_player_id FROM `likes_log` WHERE like_player_id!=#{playerId} GROUP BY like_player_id ORDER BY rand() limit #{limit}")
    List<String> getLikesByPlayerIdAndInvestId(@Param("playerId") String playerId,@Param("limit") Integer limit);

}