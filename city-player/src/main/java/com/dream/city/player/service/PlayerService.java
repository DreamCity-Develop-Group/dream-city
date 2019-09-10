package com.dream.city.player.service;

import com.dream.city.player.domain.entity.Player;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wvv
 */
@Repository
public interface PlayerService {


    /**
     *修改密码
     * @return
     */
    boolean resetLoginPwd(String playerId, String userpass);

    /**
     *交易密码
     * @return
     */
    boolean resetTraderPwd(String playerId, String pwshop);

    boolean save(Player player);

    void delete(String playerId);

    Player update(Player player);

    Player getPlayer(Player player);

    Player getPlayerById(String playerId);

    /**
     * 玩家列表
     * @param player
     * @return
     */
    List<Player> getPlayers(Player player);

}
