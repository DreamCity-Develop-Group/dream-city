package com.dream.city.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Notice;
import com.dream.city.base.model.enu.GameSettingType;
import com.dream.city.base.model.entity.PlayerGameSetting;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.mapper.PlayerGameSettingMapper;
import com.dream.city.base.model.mapper.NoticeMapper;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.GameSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author
 */
@Service
public class GameSettingServiceImpl implements GameSettingService {

    @Autowired
    private PlayerGameSettingMapper gameSettingMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    RedisUtils redisUtils;



    @LcnTransaction
    @Transactional
    @Override
    public boolean settingGameVioce(String playerId,boolean isOpen)throws BusinessException {
        PlayerGameSetting gameSetting = gameSettingMapper.selectByType(GameSettingType.GAME.name());
        gameSetting.setPlayerId(playerId);
        gameSetting.setType(GameSettingType.GAME.name());
        gameSetting.setVal(String.valueOf(isOpen));
        gameSetting.setUpdateDate(new Date());
        return gameSettingMapper.updateByPrimaryKeySelective(gameSetting)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @LcnTransaction
    @Transactional
    @Override
    public boolean settingBgVioce(String playerId,boolean isOpen) throws BusinessException{
        PlayerGameSetting gameSetting = gameSettingMapper.selectByType(GameSettingType.BG.name());
        gameSetting.setPlayerId(playerId);
        gameSetting.setType(GameSettingType.BG.name());
        gameSetting.setVal(String.valueOf(isOpen));
        gameSetting.setUpdateDate(new Date());
        return gameSettingMapper.updateByPrimaryKeySelective(gameSetting)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result getGameNotices() throws BusinessException {
        //取出公告
        List<Notice> notices = new ArrayList<>();
        Map<Object,Object> gameMap = redisUtils.hmget("game_default");
        Map<String,Notice> noticesMap = (Map)gameMap.get("notice_list");
        if (null == noticesMap){
            return Result.result(false,"取公告消息成功,没有公告", ReturnStatus.FAILED.getStatus());
        }
        Iterator<Map.Entry<String,Notice>> iterator = noticesMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Notice> entry = iterator.next();
            notices.add(entry.getValue());
        }
        //返回公告
        return Result.result(true,"取公告消息成功",ReturnStatus.SUCCESS.getStatus(),notices);
    }
}
