package com.dream.city.player.controller;

import com.dream.city.base.model.Result;
import com.dream.city.player.service.GameSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 游戏设置
 */
@RestController
@RequestMapping("/player")
public class GameSettingController {


    @Autowired
    private GameSettingService settingService;


    /**
     * 游戏音效
     * @param playerId
     * @param isOpen
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/settingGameVioce")
    public Result settingGameVioce(@RequestParam(value = "playerId") String playerId,
                            @RequestParam(value = "isOpen") Boolean isOpen){
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
    @RequestMapping(method = RequestMethod.POST, value = "/settingBgVioce")
    public Result settingBgVioce(@RequestParam(value = "playerId") String playerId,
                            @RequestParam(value = "isOpen") Boolean isOpen){
        Result result = new Result();
        boolean b = settingService.settingBgVioce(playerId, isOpen);
        result.setSuccess(b);
        return result;
    }



}
