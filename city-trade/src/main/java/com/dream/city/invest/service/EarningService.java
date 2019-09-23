package com.dream.city.invest.service;

import com.dream.city.base.model.entity.PlayerEarning;

import java.util.List;

/**
 * 提现规则
 */
public interface EarningService {

    /**
     * 删除提现规则
     * @param earnId
     * @return
     */
    int deleteEarningById(Integer earnId);

    /**
     * 新增提现规则
     * @param record
     * @return
     */
    int insertEarning(PlayerEarning record);

    /**
     * 查询提现规则
     * @param earnId
     * @return
     */
    PlayerEarning getEarning(Integer earnId);

    PlayerEarning getPlayerEarningByPlayerId(String playerId);

    /**
     * 查询提现规则列表
     * @param record
     * @return
     */
    List<PlayerEarning> getEarningList(PlayerEarning record);

    /**
     * 更新提现规则
     * @param record
     * @return
     */
    int updateEarningById(PlayerEarning record);

}
