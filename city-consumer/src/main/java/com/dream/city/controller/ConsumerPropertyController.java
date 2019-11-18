package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.service.handler.InvestService;
import com.dream.city.service.handler.PropertyService;
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
 * @author Wvv
 */
@Api(value = "物业/投资项", description = "物业/投资项")
@RestController
@RequestMapping("/consumer")
public class ConsumerPropertyController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PropertyService propertyService;
    @Autowired
    InvestService investService;



    /**
     * 查询物业
     * @param msg
     * @return
     */
    @ApiOperation(value = "查询物业", httpMethod = "POST", notes = "查询物业", response = Message.class)
    @RequestMapping("/getProperty")
    public Message getProperty(@RequestBody Message msg){
        try {
            return propertyService.getProperty(msg);
        }catch (Exception e){
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }

    /**
     * 物业列表
     * @param msg
     * @return
     */
    @ApiOperation(value = "物业列表", httpMethod = "POST", notes = "物业列表", response = Message.class)
    @RequestMapping("/getPropertyLsit")
    public Message getPropertyLsit(@RequestBody Message msg){
        try {
            return investService.getInvestList(msg);
        }catch (Exception e){
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }

    /**
     * 新建物业
     * @param msg
     * @return
     */
    /*@RequestMapping("/")
    public Message insertInvest(@RequestBody Message msg){
        try {
            return propertyService.getProperty(msg);
        }catch (Exception e){
            return msg;
        }
    }*/



    /**
     * 更新物业
     * @param msg
     * @return
     */
    /*@RequestMapping("/")
    public Message updateInvest(@RequestBody Message msg){
        try {
            return propertyService.getProperty(msg);
        }catch (Exception e){
            return msg;
        }
    }*/





}
