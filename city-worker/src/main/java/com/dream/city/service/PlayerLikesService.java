package com.dream.city.service;

import java.util.List;

public interface PlayerLikesService {
    int getLikesById(String orderPayerId);

    int getLikesGetByPlayerId(String orderPayerId);


    List<String> getPlayerIdByInvestId(int investId);

    List<String> getLikesPlayerByInvestId(int investId,String playerId);
}
