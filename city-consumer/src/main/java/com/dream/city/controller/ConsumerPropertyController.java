package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.service.ConsumerPropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 物业/投资项
 */
@Api(value = "物业/投资项", description = "物业/投资项")
@RestController
@RequestMapping("/consumer")
public class ConsumerPropertyController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerPropertyService investService;



    /**
     * 查询物业
     * @param msg
     * @return
     */
    @ApiOperation(value = "查询物业", notes = "查询物业", response = Message.class)
    @RequestMapping("/getProperty")
    public Message getProperty(@RequestBody Message msg){
        logger.info("查询物业", JSONObject.toJSONString(msg));

        CityInvest invest = DataUtils.getInvestFromMessage(msg);
        Result result = investService.getInvest(invest);
        msg.getData().setData(JSON.toJSONString(result.getData()));
        msg.setDesc(result.getMsg());
        return msg;
    }

    /**
     * 物业列表
     * @param msg
     * @return
     */
    @ApiOperation(value = "物业列表", notes = "物业列表", response = Message.class)
    @RequestMapping("/getPropertyLsit")
    public Message getPropertyLsit(@RequestBody Message msg){
        logger.info("物业列表", JSONObject.toJSONString(msg));

        CityInvest invest = DataUtils.getInvestFromMessage(msg);
        Result result = investService.getInvestLsit(invest);
        msg.getData().setData(JSON.toJSONString(result.getData()));
        msg.setDesc(result.getMsg());
        return msg;
    }

    /**
     * 新建物业
     * @param msg
     * @return
     */
    /*@RequestMapping("/")
    public Message insertInvest(@RequestBody Message msg){
        logger.info("用户取消下单", JSONObject.toJSONString(msg));



        msg.getData().setData(JSON.toJSONString(""));
        msg.setDesc("");
        return msg;
    }*/



    /**
     * 更新物业
     * @param msg
     * @return
     */
    /*@RequestMapping("/")
    public Message updateInvest(@RequestBody Message msg){
        logger.info("用户取消下单", JSONObject.toJSONString(msg));



        msg.getData().setData(JSON.toJSONString(""));
        msg.setDesc("");
        return msg;
    }*/





}
