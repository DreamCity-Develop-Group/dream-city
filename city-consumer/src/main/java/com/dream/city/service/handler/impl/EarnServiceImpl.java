package com.dream.city.service.handler.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerEarning;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.service.consumer.ConsumerEarningService;
import com.dream.city.service.handler.EarnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wvv
 * @program: dream-city
 * @File: EarnServiceImpl
 * @description: 收益实现类
 * @create: 2019/10/2019/10/27 22:13:11 [星期日]
 **/
@Service
@Transactional(rollbackFor = BusinessException.class)
public class EarnServiceImpl implements EarnService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConsumerEarningService earningService;

    /**
     * 投资提取
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message extract(Message msg) throws BusinessException {
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
    @LcnTransaction
    @Transactional
    @Override
    public Message getEarning(Message msg) throws BusinessException{
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
    @LcnTransaction
    @Transactional
    @Override
    public Message getEarningList(Message msg) throws BusinessException{
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
    @LcnTransaction
    @Transactional
    @Override
    public Message deleteEarningById(Message msg) throws BusinessException{
        PlayerEarning earning = DataUtils.getEarningFromJsonReq(msg);
        Result result = earningService.deleteEarningById(earning.getEarnId());

        Map<String,Object> t = new HashMap<>();
        t.put("desc",result.getSuccess());
        msg.getData().setData(t);
        msg.setDesc(result.getMsg());
        return msg;
    }


    /**
     * 新增提现规则
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message insertEarning(Message msg) throws BusinessException{
        PlayerEarning earning = DataUtils.getEarningFromJsonReq(msg);
        Result result = earningService.insertEarning(earning);
        Map<String,Object> t = new HashMap<>();
        t.put("desc",result.getSuccess());
        msg.getData().setData(t);
        msg.setDesc(result.getMsg());
        return msg;
    }

    /**
     * 更新提现规则
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message updateEarningById(Message msg) throws BusinessException{
        PlayerEarning earning = DataUtils.getEarningFromJsonReq(msg);
        Result result = earningService.updateEarningById(earning);
        Map<String,Object> t = new HashMap<>();
        t.put("desc",result.getSuccess());
        msg.getData().setData(t);
        msg.setDesc(result.getMsg());
        return msg;
    }
}
