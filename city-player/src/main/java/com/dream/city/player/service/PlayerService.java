package com.dream.city.player.service;

import com.dream.city.player.domain.entity.Player;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wvv
 */
@Repository
public interface PlayerService {

    boolean save(Player player);

    void delete(String playerId);

    Player update(Player player);

    List<Player> getPlayers(Player player);

}
