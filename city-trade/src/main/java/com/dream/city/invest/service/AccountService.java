package com.dream.city.invest.service;

import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.PlayerAccountLog;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.model.resp.PlayerAccountResp;

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
     * @param playerId
     * @return
     */
    PlayerAccount getPlayerAccount(String playerId);

    PlayerAccountResp getPlayerAccount(PlayerAccountReq record);

    /**
     * 玩家账户列表
     * @param record
     * @return
     */
    List<PlayerAccountResp> getPlayerAccountList(PlayerAccountReq record);

    /**
     * 更新玩家账户
     * @param record
     * @return
     */
    int updatePlayerAccount(PlayerAccount record);

    int updatePlayerAccountById(PlayerAccount record);


    /**
     * 获取平台账户
     * @param record
     * @return
     */
    List<PlayerAccount> getPlatformAccounts(PlayerAccountReq record);

    PlayerAccount getPlayerAccountByPlayerId(String playerId);
    PlayerAccount getPlayerAccountByAddr(String addr);


    int addAccountLog(PlayerAccountLog accountLog);
}
