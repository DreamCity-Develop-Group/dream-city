package com.dream.city.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.GameSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wvv
 * 游戏设置
 */
@RestController
@RequestMapping("/set")
public class GameSettingController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GameSettingService settingService;

    @Autowired
    RedisUtils redisUtils;


    /**
     * 游戏音效
     * @param playerId
     * @param isOpen
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/settingGameVoice")
    public Result settingGameVioce(@RequestParam(value = "playerId") String playerId,
                            @RequestParam(value = "isOpen") Boolean isOpen){
        logger.info("游戏音效设置,playerId:{},isOpen:{}",playerId,isOpen);
        Result result = new Result();
        boolean b = settingService.settingGameVioce(playerId, isOpen);
        result.setSuccess(b);
        return result;
    }


    /**
     * 背景音效
     * @param playerId
     * @param isOpen
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/settingBgVoice")
    public Result settingBgVioce(@RequestParam(value = "playerId") String playerId,
                            @RequestParam(value = "isOpen") Boolean isOpen){
        logger.info("背景音效设置,playerId:{},isOpen:{}",playerId,isOpen);
        Result result = new Result();
        boolean b = settingService.settingBgVioce(playerId, isOpen);
        result.setSuccess(b);
        return result;
    }

    @RequestMapping(value = "/getGameNotices",method = RequestMethod.POST)
    public Result getGameNotices(){
        return settingService.getGameNotices();
    }



}
