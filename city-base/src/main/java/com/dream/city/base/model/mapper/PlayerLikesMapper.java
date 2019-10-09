package com.dream.city.base.model.mapper;


import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.entity.Likes;
import com.dream.city.base.model.entity.PlayerLikes;
import com.dream.city.base.model.req.PlayerLikesReq;
import com.dream.city.base.model.resp.PlayerLikesResp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlayerLikesMapper {

    Integer deleteByPrimaryKey(Integer likedId);


    Integer insertSelective(PlayerLikesReq record);


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


    @Results(id = "BaseInvestOrderResultMap", value = {
            @Result(property = "orderId", column = "order_id", id = true),
            @Result(property = "orderInvestId", column = "order_invest_id"),
            @Result(property = "orderPlayerId", column = "order_player_id"),
            @Result(property = "orderAmount", column = "order_amount"),
            @Result(property = "orderState", column = "order_state"),
            @Result(property = "orderRepeat", column = "order_repeat"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    @Select({"select * from `invest_order` where 1=1 and order_id = #{orderId}"})
    Likes getInvestOrder(String orderId);


    @Select("select * from `invest_order` where 1=1 and player_id = #{playerId} ")
    List<InvestOrder> getAllInvestOrdersByPlayerId(String playerId);

    @Select({
            "<script>",
            "select",
            "sum(liked_get_total)",
            "from `player_likes`",
            "where 1=1 and and liked_player_id = #{playerId} and order_state in ",
            "<foreach collection='states' item='state' open='(' separator=',' close=')'>",
            "#{state}",
            "</foreach>",
            "</script>"
    })
    List<PlayerLikes> getLikesByIds(@Param("playerId") String playerId, @Param("states") int[] states);

    @Select("select sum(liked_get_total) from `player_likes` where 1=1 and  liked_player_id = #{playerId}")
    int getLikesGetByPlayerId(String playerId);

    @Select("select * from `invest_order` where 1=1 and player_id = #{playerId} ")
    int getLikesById(String orderPayerId);
}