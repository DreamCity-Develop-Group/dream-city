package com.dream.city.controller;

import com.dream.city.service.ConsumerCityUserService;
import com.dream.city.service.ConsumerMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wvv
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerUserController {

    @Autowired
    ConsumerCityUserService consumerCityUserService;

    @Autowired
    ConsumerMessageService messageService;


}
