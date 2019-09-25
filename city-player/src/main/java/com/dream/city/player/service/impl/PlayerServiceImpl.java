package com.dream.city.player.service.impl;

import com.alibaba.fastjson.JSON;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerExt;
import com.dream.city.base.model.entity.PlayerGrade;
import com.dream.city.base.model.req.PageReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.utils.KeyGenerator;
import com.dream.city.base.model.entity.Player;
import com.dream.city.player.domain.mapper.PlayerExtMapper;
import com.dream.city.player.domain.mapper.PlayerGradeMapper;
import com.dream.city.player.domain.mapper.PlayerMapper;
import com.dream.city.player.service.PlayerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author Wvv
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private PlayerExtMapper playerExtMapper;
    @Autowired
    private PlayerGradeMapper gradeMapper;

    @Value("${spring.application.name}")
    private String appName;


    @Override
    public Result forgetPwd(String username, String newPwd) {
        PlayerResp player = getPlayerByName(username,null);
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
            return new Result(Boolean.TRUE, CityGlobal.Constant.USER_CHANGE_TRADERPWD_SUCCESS);
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
    @Transactional
    public boolean save(Player player) {
        player.setCreateTime(new Date());
        Integer integer = playerMapper.insertSelective(player);
        return (integer != null && integer>0)?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public Integer delete(String playerId) {
        return playerMapper.deleteByPlayerId(playerId);
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
            PlayerGrade playerGrade = null;
            for (Player player:players){
                map = JSON.parseObject(JSON.toJSONString(player),Map.class);

                playerGrade = getPlayerGradeByPlayerId(player.getPlayerId());
                map.put("commerce_lv",playerGrade.getGrade());
                map.put("commerce_member",0); //商会成员数 todo
                playersMap.add(map);
            }
        }
        page.setResult(playersMap);
        page.setTotalCount( count== null?0:count);

        return page;
    }

    @Override
    public PlayerResp getPlayerByName(String playerName,String playerNick) {
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
        PlayerResp playerResp = new PlayerResp();
        if (playerByName != null){
            playerResp = JSON.toJavaObject(JSON.parseObject(JSON.toJSONString(playerByName)),PlayerResp.class);

            PlayerGrade playerGrade = getPlayerGradeByPlayerId(player.getPlayerId());
            playerResp.setGrade(playerGrade.getGrade());
            playerResp.setCommerceMember(0); //商会成员数 todo
        }
        return playerResp;
    }

    @Override
    public Player getPlayerByInvite(String invite){
        Player player = playerMapper.getPlayerByInvite(invite);
        return player;
    }

    @Override
    public PlayerGrade getPlayerGradeByPlayerId(String playerId) {
        PlayerGrade record = new PlayerGrade();
        record.setPlayerId(playerId);
        return gradeMapper.getPlayerGradeByPlayerId(record);
    }


}
