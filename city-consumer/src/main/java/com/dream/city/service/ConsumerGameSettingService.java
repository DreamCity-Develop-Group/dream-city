package com.dream.city.service;

import com.dream.city.service.impl.FallBackPlayer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "city-player", fallback = FallBackPlayer.class)
public interface ConsumerGameSettingService {

    /**
     * 游戏音效
     * @return
     */
    @RequestMapping("/player/settingGameVioce")
    String settingGameVioce(@RequestParam String playerId, @RequestParam Boolean isOpen);


    /**
     * 背景音效
     * @param isOpen
     * @return
     */
    @RequestMapping("/player/settingBgVioce")
    String settingBgVioce(@RequestParam String playerId,@RequestParam Boolean isOpen);

}
