package com.dream.city.service;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Notice;
import com.dream.city.service.impl.FallBackPlayer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "city-set", fallback = FallBackPlayer.class)
public interface ConsumerGameSettingService {

    /**
     * 游戏音效
     * @return
     */
    @RequestMapping("/set/settingGameVioce")
    Result settingGameVioce(@RequestParam String playerId, @RequestParam Boolean isOpen);


    /**
     * 背景音效
     * @param isOpen
     * @return
     */
    @RequestMapping("/set/settingBgVoice")
    Result settingBgVioce(@RequestParam String playerId,@RequestParam Boolean isOpen);

    @RequestMapping("/set/getGameNotices")
    List<Notice> getGameNoties();


}
