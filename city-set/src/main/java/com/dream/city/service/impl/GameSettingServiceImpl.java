package com.dream.city.service.impl;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Notice;
import com.dream.city.base.model.enu.GameSettingType;
import com.dream.city.base.model.entity.GameSetting;
import com.dream.city.base.model.mapper.GameSettingMapper;
import com.dream.city.base.model.mapper.NoticeMapper;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.GameSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameSettingServiceImpl implements GameSettingService {

    @Autowired
    private GameSettingMapper gameSettingMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    RedisUtils redisUtils;



    @Override
    public boolean settingGameVioce(String playerId,boolean isOpen) {
        GameSetting gameSetting = gameSettingMapper.selectByType(GameSettingType.game.name());
        gameSetting.setPlayerId(playerId);
        gameSetting.setType(GameSettingType.game.name());
        gameSetting.setVal(String.valueOf(isOpen));
        gameSetting.setUpdateDate(new Date());
        return gameSettingMapper.updateByPrimaryKeySelective(gameSetting)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public boolean settingBgVioce(String playerId,boolean isOpen) {
        GameSetting gameSetting = gameSettingMapper.selectByType(GameSettingType.bg.name());
        gameSetting.setPlayerId(playerId);
        gameSetting.setType(GameSettingType.bg.name());
        gameSetting.setVal(String.valueOf(isOpen));
        gameSetting.setUpdateDate(new Date());
        return gameSettingMapper.updateByPrimaryKeySelective(gameSetting)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public Result getGameNotices(){
        //取出公告
        List<Notice> notices = new ArrayList<>();
        Map<Object,Object> gameMap = redisUtils.hmget("game_default");
        Map<String,Notice> noticesMap = (Map)gameMap.get("notice_list");
        if (null == noticesMap){
            return new Result(true,"取公告消息成功,没有公告",200,null);
        }
        Iterator<Map.Entry<String,Notice>> iterator = noticesMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Notice> entry = iterator.next();
            notices.add(entry.getValue());
        }
        //返回公告
        return new Result(true,"取公告消息成功",200,notices);
    }
}
