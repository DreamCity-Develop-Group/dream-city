package com.dream.city.service;

public interface PlayerLikesService {
    int getLikesById(String orderPayerId);

    int getLikesGetByPlayerId(String orderPayerId);
}
