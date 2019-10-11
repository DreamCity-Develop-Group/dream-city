package com.dream.city.service;

import com.dream.city.base.model.entity.Player;

public interface PusherService {
    void receive(Player player,int type);
}
