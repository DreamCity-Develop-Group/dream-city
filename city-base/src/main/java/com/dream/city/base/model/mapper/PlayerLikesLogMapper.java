package com.dream.city.base.model.mapper;

import com.dream.city.base.model.entity.PlayerLikesLog;
import com.dream.city.base.model.req.PlayerLikesReq;
import com.dream.city.base.model.resp.PlayerLikesResp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlayerLikesLogMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(PlayerLikesLog record);

    PlayerLikesResp selectByPrimaryKey(Integer id);

    List<PlayerLikesResp> getLikesLogList(PlayerLikesReq record);

    int updateByPrimaryKeySelective(PlayerLikesLog record);

    /**
     * 当天点赞次数
     * 好友
     * @param record
     * @return
     */
    int playerLikesCountToday(PlayerLikesLog record);

    /**
     * 当天点赞次数
     * 投资
     * @param record
     * @return
     */
    int investLikesCountToday(PlayerLikesLog record);
}