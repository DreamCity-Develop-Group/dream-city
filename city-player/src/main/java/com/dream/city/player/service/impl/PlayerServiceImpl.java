package com.dream.city.player.service.impl;

import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Result;
import com.dream.city.base.utils.KeyGenerator;
import com.dream.city.player.domain.entity.Player;
import com.dream.city.player.domain.mapper.PlayerMapper;
import com.dream.city.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author Wvv
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerMapper playerMapper;

    @Value("${spring.application.name}")
    private String appName;


    @Override
    public Result forgetPwd(String username, String newPwd) {
        Player player = new Player();
        player.setUsername(username);
        List<Player> players = playerMapper.getPlayers(player);
        if (CollectionUtils.isEmpty(players)){
            return new Result(Boolean.FALSE, CityGlobal.Constant.USER_NOT_EXIT);
        }

        return changePwd(players.get(0).getPlayerId(), newPwd);
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
        player.setUserpass(newPwd);
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
        player.setPwshop(newPwd);
        int i = playerMapper.updateByPlayerId(player);
        if (i>0){
            // 修改密码成功
            return new Result(Boolean.TRUE, CityGlobal.Constant.USER_CHANGE_PWD_SUCCESS);
        }
        return new Result(Boolean.FALSE, CityGlobal.Constant.USER_CHANGE_TRADERPWD_FAIL);
    }

    private Result changePwdVelid(String playerId, String oldPwd){
        Player playerExit = playerMapper.getPlayerById(playerId);

        // 用户不存在
        if(playerExit == null){
            return new Result(Boolean.FALSE, CityGlobal.Constant.USER_NOT_EXIT);
        }

        // 旧密码不正确
        if (playerExit.getUserpass().equals(oldPwd)){
            return new Result(Boolean.FALSE, CityGlobal.Constant.USER_OLD_PWD_ERROR);
        }

        return new Result(Boolean.TRUE, CityGlobal.Constant.USER_OLD_PWD_ERROR);
    }

    @Override
    public boolean save(Player player) {
        player.setPlayerId(KeyGenerator.getUUID());
        player.setCreateDate(new Date());
        //加密  前端加密，后端不加密
        player.setUserpass(player.getUserpass());
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
        List<Player> players = playerMapper.getPlayers(player);
        if (!CollectionUtils.isEmpty(players)) {
            return players.get(0);
        }
        return null;
    }

    @Override
    public Player getPlayerById(String playerId) {
        if (StringUtils.isEmpty(playerId)){
            return null;
        }
        return playerMapper.getPlayerById(playerId);
    }

    @Override
    public List<Player> getPlayers(Player player) {
        return playerMapper.getPlayers(player);
    }





}
