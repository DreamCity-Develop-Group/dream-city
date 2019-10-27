package com.dream.city.controller;

import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Result;
import com.dream.city.service.routes.TestLcnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/consumer")
@RestController
public class TestLcnController {

    @Autowired
    TestLcnService testLcnService;




    @RequestMapping("/setplayertree/{playerId}/{invite}/{pass}")
    public Result testPlayerTree(@PathVariable("playerId")String playerId,
                                 @PathVariable("invite")String invite,
                                 @PathVariable("pass")String pass)throws BusinessException{
        try {
            return testLcnService.setPlayerTree(playerId,invite,pass);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            return Result.result(false,e.getMessage(),555,null);
        }
    }
}
