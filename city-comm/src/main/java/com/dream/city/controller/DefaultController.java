package com.dream.city.controller;

import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.service.DefaultService;
import com.dream.city.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Wvv
 */
@RestController
@RequestMapping("/comm")
public class DefaultController {
    @Autowired
    IndexService indexService;

    @RequestMapping("/index")
    public List<InvestRule> getRules(){
        return indexService.selectRules();
    }


}
