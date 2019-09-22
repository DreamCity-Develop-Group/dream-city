package com.dream.city.service.impl;

import com.dream.city.base.model.Message;
import com.dream.city.service.PlayerTradeService;
import org.springframework.stereotype.Service;

@Service
public class PlayerTradeServiceImpl implements PlayerTradeService {


    @Override
    public boolean playerRecharge(Message msg) {
        return false;
    }

    @Override
    public boolean playerWithdraw(Message msg) {
        return false;
    }

    @Override
    public boolean playerTransfer(Message msg) {
        return false;
    }

    @Override
    public boolean playerInvest(Message msg) {
        return false;
    }
}
