package com.dream.city.player.service;

import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerGrade;
import com.dream.city.base.model.req.PageReq;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.resp.PlayerResp;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    Result resetLoginPwd(String playerId, String oldPwd, String newPwd);

    /**
     *交易密码
     * @return
     */
    Result resetTraderPwd(String playerId, String oldPwd, String newPwd);

    boolean save(Player player);

    Integer delete(String playerId);

    Player update(Player player);

    Player getPlayer(Player player);

    Player getPlayerById(String playerId);

    /**
     * 玩家列表
     * @param pageReq
     * @return
     */
    Page getPlayers(PageReq pageReq);

    PlayerResp getPlayerByName(String username, String playerNick);

    Player getPlayerByInvite(String invite);

    Player getPlayerByAccount(String account);

    /**
     * 获取玩家等级
     * @param playerId
     * @return
     */
    PlayerGrade getPlayerGradeByPlayerId(String playerId);



}
