package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerEarning;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.service.consumer.ConsumerEarningService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 提现规则
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerEarningController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerEarningService earningService;


    /**
     * 投资提取
     * @param msg
     * @return
     */
    @ApiOperation(value = "投资提取", httpMethod = "POST", notes = "t入参playerId,inType", response = Message.class)
    @RequestMapping("/extract")
    public Message extract(@RequestBody Message msg){
        Object dataMsg = msg.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);
        String playerId = jsonObject.getString("playerId");
        Integer inType = Integer.parseInt(jsonObject.getString("inType"));

        Result<Map<String,Object>> result = earningService.extract(playerId,inType);
        msg.setCode(result.getCode());
        msg.setDesc(result.getMsg());
        msg.getData().setData(result.getData());
        return msg;
    }


    /**
     * 查询提现规则
     * @param msg
     * @return
     */
    @ApiOperation(value = "查询提现规则", httpMethod = "POST", notes = "t入参username", response = Message.class)
    @RequestMapping("/getEarning")
    public Message getEarning(@RequestBody Message msg){
        PlayerEarning earning = DataUtils.getEarningFromJsonReq(msg);
        Result result = earningService.getEarning(earning.getEarnId());
        msg.getData().setData(JSON.toJSONString(result.getData()));
        msg.setDesc(result.getMsg());
        return msg;
    }

    /**
     * 查询提现规则列表
     * @param msg
     * @return
     */
    @ApiOperation(value = "查询提现规则列表", httpMethod = "POST", notes = "t入参username", response = Message.class)
    @RequestMapping("/getEarningList")
    public Message getEarningList(@RequestBody Message msg){
        PlayerEarning earning = DataUtils.getEarningFromJsonReq(msg);
        Result result = earningService.getEarningList(earning);
        msg.getData().setData(JSON.toJSONString(result.getData()));
        msg.setDesc(result.getMsg());
        return msg;
    }


    /**
     * 删除提现规则
     * @param msg
     * @return
     */
    /*@ApiOperation(value = "删除提现规则", httpMethod = "POST", notes = "t入参username", response = Message.class)
    @RequestMapping("/deleteEarningById")
    public Message deleteEarningById(@RequestBody Message msg){
        PlayerEarning earning = DataUtils.getEarningFromJsonReq(msg);
        Result result = earningService.deleteEarningById(earning.getEarnId());

        Map<String,Object> t = new HashMap<>();
        t.put("desc",result.getSuccess());
        msg.getData().setData(t);
        msg.setDesc(result.getMsg());
        return msg;
    }*/


    /**
     * 新增提现规则
     * @param msg
     * @return
     */
    /*@ApiOperation(value = "新增提现规则", httpMethod = "POST", notes = "t入参username", response = Message.class)
    @RequestMapping("/insertEarning")
    public Message insertEarning(@RequestBody Message msg){
        PlayerEarning earning = DataUtils.getEarningFromJsonReq(msg);
        Result result = earningService.insertEarning(earning);
        Map<String,Object> t = new HashMap<>();
        t.put("desc",result.getSuccess());
        msg.getData().setData(t);
        msg.setDesc(result.getMsg());
        return msg;
    }*/

    /**
     * 更新提现规则
     * @param msg
     * @return
     */
    /*@ApiOperation(value = "更新提现规则", httpMethod = "POST", notes = "t入参username", response = Message.class)
    @RequestMapping("/updateEarningById")
    public Message updateEarningById(@RequestBody Message msg){
        PlayerEarning earning = DataUtils.getEarningFromJsonReq(msg);
        Result result = earningService.updateEarningById(earning);
        Map<String,Object> t = new HashMap<>();
        t.put("desc",result.getSuccess());
        msg.getData().setData(t);
        msg.setDesc(result.getMsg());
        return msg;
    }*/




}
