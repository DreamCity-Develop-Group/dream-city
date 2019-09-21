package com.dream.city.invest.service;

import com.dream.city.base.model.entity.PlayerAccount;

import java.util.List;

/**
 * 玩家账户
 */
public interface AccountService {

    /**
     * 新增玩家账户
     * @param record
     * @return
     */
    int createPlayerAccount(PlayerAccount record);

    /**
     * 获取玩家账户
     * @param record
     * @return
     */
    PlayerAccount getPlayerAccount(PlayerAccount record);

    /**
     * 玩家账户列表
     * @param record
     * @return
     */
    List<PlayerAccount> getPlayerAccountList(PlayerAccount record);

    /**
     * 更新玩家账户
     * @param record
     * @return
     */
    int updatePlayerAccount(PlayerAccount record);

}
