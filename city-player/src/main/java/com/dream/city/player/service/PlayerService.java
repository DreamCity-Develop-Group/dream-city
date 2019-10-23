package com.dream.city.player.service;

import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerGrade;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.req.PlayerReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author Wvv
 */
@Repository
public interface PlayerService {

    /**
     *忘记密码
     * @return
     */
    Result forgetPwd(String username,String newPwd);

    /**
     *修改密码
     * @return
     */
    Result resetLoginPwd(String username, String oldPwd, String newPwd);

    /**
     *交易密码
     * @return
     */
    Result resetTraderPwd(String username, String oldpwshop, String newpwshop);

    boolean save(Player player);

    Integer delete(String playerId);

    Player update(Player player);

    PlayerResp getPlayer(Player player);

    PlayerResp  getPlayerById(String playerId);

    /**
     * 玩家列表
     * @param pageReq
     * @return
     */
    PageInfo<PlayerResp> getPlayers(Page pageReq);
    Integer getPlayersCount(PlayerReq record);

    PlayerResp getPlayerByName(String username, String playerNick);

    Player getPlayerByInvite(String invite);

    Player getPlayerByAccount(String account);

    /**
     * 获取玩家等级
     * @param playerId
     * @return
     */
    PlayerGrade getPlayerGradeByPlayerId(String playerId);

    Player getPlayerByPlayerId(String playerId);


    boolean setTraderPwd(String playerId, String tradePass);
}
