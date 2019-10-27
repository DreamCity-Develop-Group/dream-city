package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.service.handler.EarnService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提现规则
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerEarningController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EarnService earnService;


    /**
     * 投资提取
     * @param msg
     * @return
     */
    @ApiOperation(value = "投资提取", httpMethod = "POST", notes = "t入参playerId,inType", response = Message.class)
    @RequestMapping("/extract")
    public Message extract(@RequestBody Message msg){
        try {
            return earnService.extract(msg);
        }catch (Exception e){
            return msg;
        }
    }


    /**
     * 查询提现规则
     * @param msg
     * @return
     */
    @ApiOperation(value = "查询提现规则", httpMethod = "POST", notes = "t入参username", response = Message.class)
    @RequestMapping("/getEarning")
    public Message getEarning(@RequestBody Message msg){
        try {
            return earnService.getEarning(msg);
        }catch (Exception e){
            return msg;
        }
    }

    /**
     * 查询提现规则列表
     * @param msg
     * @return
     */
    @ApiOperation(value = "查询提现规则列表", httpMethod = "POST", notes = "t入参username", response = Message.class)
    @RequestMapping("/getEarningList")
    public Message getEarningList(@RequestBody Message msg){
        try {
            return earnService.getEarningList(msg);
        }catch (Exception e){
            return msg;
        }
    }


    /**
     * 删除提现规则
     * @param msg
     * @return
     */
    /*@ApiOperation(value = "删除提现规则", httpMethod = "POST", notes = "t入参username", response = Message.class)
    @RequestMapping("/deleteEarningById")
    public Message deleteEarningById(@RequestBody Message msg){
        try {
            return earnService.deleteEarningById(msg);
        }catch (Exception e){
            return msg;
        }
    }*/


    /**
     * 新增提现规则
     * @param msg
     * @return
     */
    /*@ApiOperation(value = "新增提现规则", httpMethod = "POST", notes = "t入参username", response = Message.class)
    @RequestMapping("/insertEarning")
    public Message insertEarning(@RequestBody Message msg){
        try {
            return earnService.insertEarning(msg);
        }catch (Exception e){
            return msg;
        }
    }*/

    /**
     * 更新提现规则
     * @param msg
     * @return
     */
    /*@ApiOperation(value = "更新提现规则", httpMethod = "POST", notes = "t入参username", response = Message.class)
    @RequestMapping("/updateEarningById")
    public Message updateEarningById(@RequestBody Message msg){
        try {
            return earnService.updateEarningById(msg);
        }catch (Exception e){
            return msg;
        }
    }*/




}
