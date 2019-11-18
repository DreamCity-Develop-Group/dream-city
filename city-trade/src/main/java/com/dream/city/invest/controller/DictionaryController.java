package com.dream.city.invest.controller;

import com.dream.city.base.model.entity.Dictionary;
import com.dream.city.invest.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 玩家账户
 */
@RestController
@RequestMapping("/dict")
public class DictionaryController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DictionaryService dictionaryService;




    @RequestMapping("/getValByKey")
    public String getValByKey(@RequestParam("key")String key){
        return dictionaryService.getValByKey(key);
    }

    @RequestMapping("/getKeyByVal")
    public String getKeyByVal(@RequestParam("val")String val){
        return dictionaryService.getKeyByVal(val);
    }

    @RequestMapping("/getDictByKey")
    public Dictionary getDictByKey(@RequestParam("key")String key){
        return dictionaryService.getOneByKey(key);
    }
}
