package com.dream.city.player.service.impl;

import com.alibaba.fastjson.JSON;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Page;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Friends;
import com.dream.city.base.model.entity.PlayerGrade;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.req.PlayerReq;
import com.dream.city.base.model.resp.PlayerResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.mapper.PlayerGradeMapper;
import com.dream.city.base.model.mapper.PlayerMapper;
import com.dream.city.player.service.FriendsService;
import com.dream.city.player.service.PlayerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private PlayerGradeMapper gradeMapper;
    @Autowired
    private FriendsService friendsService;

    @Value("${spring.application.name}")
    private String appName;


    @LcnTransaction
    @Transactional
    @Override
    public Result forgetPwd(String username, String newPwd) throws BusinessException {
        PlayerResp player = getPlayerByName(username, null);
        if (player == null) {
            return Result.result(Boolean.FALSE, CityGlobal.Constant.USER_NOT_EXIT,ReturnStatus.ERROR_NOTEXISTS.getStatus(),null);
        }
        return changePwd(player.getPlayerId(), newPwd);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result resetLoginPwd(String username, String oldPwd, String newPwd)  throws BusinessException{
        String pwdType = "resetLoginPwd";
        PlayerResp player = getPlayerByName(username, null);
        Result result = changePwdValid(username, oldPwd, pwdType);
        if (!result.getSuccess()) {
            return result;
        }

        return changePwd(player.getPlayerId(), newPwd);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result changePwd(String playerId, String newPwd)  throws BusinessException{
        Player player = new Player();
        player.setPlayerId(playerId);
        player.setPlayerPass(newPwd);
        int i = playerMapper.updateByPlayerId(player);
        if (i > 0) {
            // 修改密码成功
            return Result.result(Boolean.TRUE, CityGlobal.Constant.USER_CHANGE_PWD_SUCCESS,ReturnStatus.SUCCESS.getStatus());
        }
        return Result.result(Boolean.FALSE, CityGlobal.Constant.USER_CHANGE_PWD_FAIL,ReturnStatus.SET_FAILED.getStatus());
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result resetTraderPwd(String username, String oldpwshop, String newpwshop)  throws BusinessException{
        String pwdType = "resetTraderPwd";
        if (StringUtils.isBlank(oldpwshop)) {
            pwdType = "setTraderPwd";
        }

        Player player = new Player();
        player.setPlayerName(username);
        PlayerResp playerExit = playerMapper.getPlayerById(player);

        // 用户不存在
        if (playerExit == null) {
            return Result.result(Boolean.FALSE, CityGlobal.Constant.USER_NOT_EXIT, ReturnStatus.ERROR_NOTEXISTS.getStatus());
        }

        if (StringUtils.isBlank(newpwshop)){
            return Result.result(Boolean.FALSE, CityGlobal.Constant.USER_CHANGE_TRADERPWD_FAIL+":新密码为空", ReturnStatus.FAILED.getStatus());
        }

        /*Result result = changePwdValid(username, oldpwshop, pwdType);
        if (!result.getSuccess()) {
            return result;
        }*/

        player.setPlayerTradePass(newpwshop);
        int i = playerMapper.updatePassByName(player);
        if (i > 0) {
            // 修改密码成功
            return Result.result(Boolean.TRUE, CityGlobal.Constant.USER_CHANGE_TRADERPWD_SUCCESS, ReturnStatus.SUCCESS.getStatus());
        }
        return Result.result(Boolean.FALSE, CityGlobal.Constant.USER_CHANGE_TRADERPWD_FAIL, ReturnStatus.FAILED.getStatus());
    }

    @LcnTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional
    @Override
    public Result changePwdValid(String username, String oldpwshop, String pwdType)  throws BusinessException{
        Player player = new Player();
        player.setPlayerName(username);
        PlayerResp playerExit = playerMapper.getPlayerById(player);

        // 用户不存在
        if (playerExit == null) {
            return Result.result(Boolean.FALSE, CityGlobal.Constant.USER_NOT_EXIT, ReturnStatus.ERROR_NOTEXISTS.getStatus());
        }

        // 旧密码不正确 [不进行旧密码判断]
        if ("resetLoginPwd".equalsIgnoreCase(pwdType) && !playerExit.getPlayerPass().equals(oldpwshop)) {
            return Result.result(Boolean.FALSE, CityGlobal.Constant.USER_OLD_PWD_ERROR, ReturnStatus.ERROR_PASS.getStatus());
        }
        // 交易密码 没有交易密码的设置交易密码，有交易密码的修改交易密码
        if ("setTraderPwd".equalsIgnoreCase(pwdType)) {
            return Result.result(Boolean.TRUE, CityGlobal.Constant.USER_CHANGE_PWD_SUCCESS, ReturnStatus.SUCCESS.getStatus());
        }

        // 交易密码 没有交易密码的设置交易密码，有交易密码的修改交易密码
        if ("resetTraderPwd".equalsIgnoreCase(pwdType)&& StringUtils.isNotBlank(playerExit.getPlayerTradePass()) && !playerExit.getPlayerTradePass().equals(oldpwshop)) {
            return Result.result(Boolean.FALSE, CityGlobal.Constant.USER_OLD_PWD_ERROR, ReturnStatus.ERROR_PASS.getStatus());
        }

        return Result.result(Boolean.TRUE, CityGlobal.Constant.USER_CHANGE_PWD_SUCCESS, ReturnStatus.SUCCESS.getStatus());
    }

    @LcnTransaction
    @Transactional
    @Override
    public boolean save(Player player)  throws BusinessException{
        player.setCreateTime(new Date());
        Integer integer = playerMapper.insertSelective(player);
        return (integer != null && integer > 0) ? Boolean.TRUE : Boolean.FALSE;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Integer delete(String playerId) throws BusinessException {
        return playerMapper.deleteByPlayerId(playerId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Player update(Player player) throws BusinessException {
        int i = playerMapper.updateByPlayerId(player);
        return i > 0 ? player : null;
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerResp getPlayer(Player player) throws BusinessException {
        return playerMapper.getPlayerById(player);
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerResp getPlayerById(String playerId) throws BusinessException {
        Player player = new Player();
        if (StringUtils.isBlank(playerId)) {
            return null;
        }
        player.setPlayerId(playerId);
        return playerMapper.getPlayerById(player);
    }

    @LcnTransaction
    @Transactional
    @Override
    public PageInfo<PlayerResp> getPlayers(Page pageReq) throws BusinessException {
        List<PlayerResp> players = null;
        try {
            PlayerReq playerReq = DataUtils.toJavaObject(pageReq.getCondition(), PlayerReq.class);
            PageHelper.startPage(pageReq.getPageNum(), pageReq.getPageSize(), pageReq.isCount());
            //List<PlayerResp> players = playerMapper.searchPlayers(playerReq);
            players = playerMapper.getPlayers(playerReq);
            if (!CollectionUtils.isEmpty(players)) {
                String getFriendAgree = "添加";
                for (int i = 0; i < players.size(); i++) {
                    if (StringUtils.isNotBlank(playerReq.getPlayerId())) {
                        getFriendAgree = this.getFriendAgree(playerReq.getPlayerId(), players.get(i));
                    }
                    players.get(i).setAddfriend(getFriendAgree);
                /*if (players.get(i).getAgree() == null){
                    players.get(i).setAgree(0);
                }*/
                }
            }
        } catch (Exception e) {
            log.error("", e);
        }

        return new PageInfo<>(players);
    }

    @LcnTransaction
    @Transactional
    @Override
    public Integer getPlayersCount(PlayerReq record) throws BusinessException {
        Integer playersCount = playerMapper.getPlayersCount(record);
        return playersCount == null ? 0 : playersCount;
    }

    @LcnTransaction
    @Transactional
    @Override
    public String getFriendAgree(String playerId, PlayerResp player)  throws BusinessException{
        String friendId = player.getPlayerId();
        Friends record = new Friends();
        record.setPlayerId(playerId);
        record.setFriendId(friendId);
        Integer getFriendAgree = friendsService.getFriendAgree(record);

        String result = "添加";
        if (getFriendAgree == null) {
            result = "添加";
        } else {
            if (getFriendAgree == 0) {
                result = "已申请";
            } else if (getFriendAgree == 1) {
                result = "已添加";
            }
        }
        return result;
    }

    @LcnTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional
    @Override
    public PlayerResp getPlayerByName(String playerName, String playerNick) throws BusinessException {
        Player player = new Player();
        if (StringUtils.isNotBlank(playerName)) {
            player.setPlayerName(playerName);
        }
        if (StringUtils.isNotBlank(playerNick)) {
            player.setPlayerNick(playerNick);
        }
        PlayerResp playerByName = null;
        if (StringUtils.isNotBlank(playerName) || StringUtils.isNotBlank(playerNick)) {
            playerByName = playerMapper.getPlayerById(player);
        }
        PlayerResp playerResp = null;
        if (playerByName != null) {
            playerResp = JSON.toJavaObject(JSON.parseObject(JSON.toJSONString(playerByName)), PlayerResp.class);

            /*这里不用查询级别
            PlayerGrade playerGrade = getPlayerGradeByPlayerId(player.getPlayerId());
            playerResp.setGrade(playerGrade.getGrade());
            playerResp.setCommerceMember(0); //商会成员数 todo
            */
            return playerResp;
        }
        return playerResp;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Player getPlayerByInvite(String invite) throws BusinessException {
        Player player = playerMapper.getPlayerByInvite(invite);
        return player;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Player getPlayerByAccount(String account)  throws BusinessException{
        Player player = playerMapper.getPlayerByAccount(account);
        return player;
    }

    @LcnTransaction
    @Transactional
    @Override
    public PlayerGrade getPlayerGradeByPlayerId(String playerId)  throws BusinessException{
        PlayerGrade record = new PlayerGrade();
        record.setPlayerId(playerId);
        return gradeMapper.getPlayerGradeByPlayerId(record);
    }

    @LcnTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional
    @Override
    public Player getPlayerByPlayerId(String playerId) throws BusinessException {
        return playerMapper.getPlayer(playerId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public boolean setTraderPwd(String playerId, String tradePass) throws BusinessException {
        try {
            Player player = playerMapper.getPlayer(playerId);
            player.setPlayerTradePass(tradePass);
            playerMapper.updateByPlayerId(player);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
