package com.dream.city.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.req.PlayerAccountReq;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.service.ConsumerAccountService;
import com.dream.city.service.ConsumerPlayerService;
import com.dream.city.service.PlayerTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerTradeServiceImpl implements PlayerTradeService {

    @Autowired
    ConsumerPlayerService playerService;
    @Autowired
    ConsumerAccountService accountService;

    @Override
    public Message playerRecharge(Message msg) {
        PlayerAccountReq accountReq = DataUtils.getPlayerAccountReqFromMessage(msg);
        String playerName = accountReq.getUsername();
        String playerNick = accountReq.getNick();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("playerName",playerName);
        jsonObject.put("playerNick",playerNick);
        Result<String> playerResult = playerService.getPlayerByName(jsonObject.toJSONString());
        Player player = JSONObject.toJavaObject(JSON.parseObject(playerResult.getData()),Player.class);

        PlayerAccount record = new PlayerAccount();
        record.setAccPlayerId(player.getPlayerId());
        Result<PlayerAccount> accountResult = accountService.getPlayerAccount(record);
        PlayerAccount account = accountResult.getData();

        PlayerAccount accountUpdate = new PlayerAccount();
        accountUpdate.setAccPlayerId(account.getAccPlayerId());
        accountUpdate.setAccMt(account.getAccMt().subtract(accountReq.getAccMt()));
        accountUpdate.setAccUsdt(account.getAccUsdt().subtract(accountReq.getAccUsdt()));
        Result result = accountService.updatePlayerAccount(accountUpdate);

        String desc = "充值失败！";
        if (result.getSuccess()){
            desc = "充值成功！";
        }
        msg.setDesc(desc);
        msg.getData().setT(result.getData());
        return msg;
    }

    @Override
    public Message playerWithdraw(Message msg) {


        return msg;
    }

    @Override
    public Message playerTransfer(Message msg) {


        return msg;
    }

    @Override
    public Message playerInvest(Message msg) {


        return msg;
    }
}
