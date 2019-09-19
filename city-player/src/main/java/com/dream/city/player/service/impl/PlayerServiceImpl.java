package com.dream.city.player.service.impl;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.req.PageReq;
import com.dream.city.base.utils.KeyGenerator;
import com.dream.city.player.domain.entity.Player;
import com.dream.city.player.domain.mapper.PlayerMapper;
import com.dream.city.player.service.PlayerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author Wvv
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerMapper playerMapper;

    @Value("${spring.application.name}")
    private String appName;


    @Override
    public Result forgetPwd(String username, String newPwd) {
        Player player = getPlayerByName(username,null);
        if (player == null){
            return new Result(Boolean.FALSE, CityGlobal.Constant.USER_NOT_EXIT);
        }

        return changePwd(player.getPlayerId(), newPwd);
    }

    @Override
    public Result resetLoginPwd(String playerId, String oldPwd, String newPwd) {
        Result result = changePwdVelid(playerId, oldPwd);
        if (!result.getSuccess()){
            return result;
        }

        return changePwd(playerId, newPwd);
    }

    private Result changePwd(String playerId, String newPwd){
        Player player = new Player();
        player.setPlayerId(playerId);
        player.setPlayerPass(newPwd);
        int i = playerMapper.updateByPlayerId(player);
        if (i>0){
            // 修改密码成功
            return new Result(Boolean.TRUE, CityGlobal.Constant.USER_CHANGE_PWD_SUCCESS);
        }
        return new Result(Boolean.FALSE, CityGlobal.Constant.USER_CHANGE_PWD_FAIL);
    }


    @Override
    public Result resetTraderPwd(String playerId, String oldPwd, String newPwd) {
        Result result = changePwdVelid(playerId, oldPwd);
        if (!result.getSuccess()){
            return result;
        }

        Player player = new Player();
        player.setPlayerId(playerId);
        player.setPlayerTradePass(newPwd);
        int i = playerMapper.updateByPlayerId(player);
        if (i>0){
            // 修改密码成功
            return new Result(Boolean.TRUE, CityGlobal.Constant.USER_CHANGE_PWD_SUCCESS);
        }
        return new Result(Boolean.FALSE, CityGlobal.Constant.USER_CHANGE_TRADERPWD_FAIL);
    }

    private Result changePwdVelid(String playerId, String oldPwd){
        Player player = new Player();
        player.setPlayerId(playerId);
        Player playerExit = playerMapper.getPlayerById(player);

        // 用户不存在
        if(playerExit == null){
            return new Result(Boolean.FALSE, CityGlobal.Constant.USER_NOT_EXIT);
        }

        // 旧密码不正确
        if (playerExit.getPlayerPass().equals(oldPwd)){
            return new Result(Boolean.FALSE, CityGlobal.Constant.USER_OLD_PWD_ERROR);
        }

        return new Result(Boolean.TRUE, CityGlobal.Constant.USER_OLD_PWD_ERROR);
    }

    @Override
    public boolean save(Player player) {
        player.setPlayerId(KeyGenerator.getUUID());
        player.setCreateTime(new Date());
        //加密  前端加密，后端不加密
        player.setPlayerPass(player.getPlayerPass());
        // todo
        player.setPlayerInvite("");
        return playerMapper.insertSelective(player)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public void delete(String playerId) {
        playerMapper.deleteByPlayerId(playerId);
    }

    @Override
    public Player update(Player player) {
        int i = playerMapper.updateByPlayerId(player);
        return i > 0? player: null;
    }

    @Override
    public Player getPlayer(Player player) {
        return playerMapper.getPlayerById(player);
    }

    @Override
    public Player getPlayerById(String playerId) {
        Player player = new Player();
        if (StringUtils.isBlank(playerId)){
            return null;
        }
        player.setPlayerId(playerId);
        return playerMapper.getPlayerById(player);
    }


    @Override
    public Page getPlayers(PageReq pageReq) {
        Page page = new Page();
        page.setCondition(pageReq.getCondition());

        Integer count = playerMapper.getPlayersCount(pageReq);
        List<Player> players = playerMapper.getPlayers(pageReq);

        List<Map> playersMap = new ArrayList<>();
        if (!CollectionUtils.isEmpty(players)){
            Map map = null;
            for (Player player:players){
                map = JSON.parseObject(JSON.toJSONString(player),Map.class);
                playersMap.add(map);
            }
        }
        page.setResult(playersMap);
        page.setTotalCount( count== null?0:count);

        return page;
    }

    @Override
    public Player getPlayerByName(String playerName,String playerNick) {
        Player player = new Player();
        if (StringUtils.isNotBlank(playerName)) {
            player.setPlayerName(playerName);
        }
        if (StringUtils.isNotBlank(playerNick)) {
            player.setPlayerNick(playerNick);
        }
        Player playerByName = null;
        if (StringUtils.isNotBlank(playerName) || StringUtils.isNotBlank(playerNick)) {
            playerByName = playerMapper.getPlayerById(player);
        }
        return playerByName;
    }


}
