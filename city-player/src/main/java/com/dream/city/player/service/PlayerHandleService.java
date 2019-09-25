package com.dream.city.player.service;

import com.dream.city.base.model.entity.Player;
import org.springframework.stereotype.Repository;

/**
 * @author Wvv
 */
@Repository
public interface PlayerHandleService {


    /**
     * 新增玩家
     * @param player
     * @return
     */
    boolean createPlayer(Player player);


}
