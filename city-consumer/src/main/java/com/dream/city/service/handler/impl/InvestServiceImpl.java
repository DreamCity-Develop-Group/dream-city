package com.dream.city.service.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.req.CityInvestReq;
import com.dream.city.base.model.resp.InvestResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.consumer.ConsumerInvestService;
import com.dream.city.service.consumer.ConsumerOrderService;
import com.dream.city.service.consumer.ConsumerPropertyService;
import com.dream.city.service.consumer.ConsumerTradeVerifyService;
import com.dream.city.service.handler.InvestService;
import com.dream.city.service.handler.OrderService;
import com.dream.city.service.handler.PropertyService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Wvv
 * @program: dream-city
 * @File: InvestServiceImpl
 * @description: 投资服务类
 * @create: 2019/10/2019/10/27 22:51:06 [星期日]
 **/
@Service
public class InvestServiceImpl implements InvestService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderService orderService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    RedisUtils redisUtils;

    final String INVERST_HASH_DATA = "INVERST_HASH_DATA_";

    /**
     * 预约投资
     *
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message playerInvest(Message msg) throws BusinessException {
        logger.info("预约投资", JSONObject.toJSONString(msg));
        return orderService.playerInvest(msg);
    }


    /**
     * 投资
     * @param msg
     * @return
     */
    /*
    @LcnTransaction
    @Transactional
    @Override
    public Message playerInvesting(Message msg){
        logger.info("投资", JSONObject.toJSONString(msg));
        return orderHandleService.playerInvest(msg);
    }*/


    /**
     * 根据投资id查询订单
     *
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message getInvest(Message msg) throws BusinessException {
        logger.info("查询订单", JSONObject.toJSONString(msg));
        return orderService.getPlayerInvestOrderById(msg);
    }


    /**
     * 投资列表
     *
     * @param msg
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Message getInvestList(Message msg) throws BusinessException {
        logger.info("投资列表", JSONObject.toJSONString(msg));
        String json = JsonUtil.parseObjToJson(msg.getData().getData());
        JSONObject jsonObject = JsonUtil.parseJsonToObj(json,JSONObject.class);
        String playerId = jsonObject.getString("playerId");
        Map<String, Object> dataResult = new Hashtable<>();

        Map<String, Object> data = new Hashtable<>();
        if (redisUtils.hasKey(INVERST_HASH_DATA+playerId)){
            Map rdata = redisUtils.hmget(INVERST_HASH_DATA+playerId);
            List list = (List)rdata.get("investList");
            if (list.size()>0){
                msg.getData().setData(rdata);
                msg.setDesc("取出投资数据成功");
                msg.setCode(ReturnStatus.SUCCESS.getStatus());
                return msg;
            }else{
                redisUtils.del(INVERST_HASH_DATA+playerId);
            }
        }

        CityInvestReq invest = DataUtils.getInvestFromMessage(msg);

        if (StringUtils.isNotBlank(invest.getFriendId())) {
            dataResult.put("playerId", invest.getFriendId());
            invest.setPlayerId(invest.getFriendId());
        } else {
            dataResult.put("playerId", invest.getPlayerId());
            invest.setPlayerId(invest.getPlayerId());
        }

        Result<List<Map<String, Object>>> result = propertyService.getPropertyLsit(invest);

        dataResult.put("investList", result.getData());

        redisUtils.hmset(INVERST_HASH_DATA+playerId,dataResult);

        msg.getData().setData(dataResult);
        msg.setDesc(result.getMsg());
        msg.setCode(result.getSuccess() ? ReturnStatus.SUCCESS.getStatus() : ReturnStatus.ERROR.getStatus());
        return msg;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result<Integer> insertInvest(CityInvest invest) {
        return null;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result<CityInvest> getInvestByIdOrName(CityInvest invest) {
        return null;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result<Integer> updateInvest(CityInvest invest) {
        return null;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result<List<InvestResp>> getInvestLsit(CityInvestReq invest) {
        return null;
    }


    /**
     * 投资列表
     * @param msg
     * @return
     */
    /*
    @LcnTransaction
    @Transactional
    @Override
    public Message getInvestList(Message msg){
        logger.info("查询投资列表", JSONObject.toJSONString(msg));
        return orderHandleService.getPlayerInvestOrders(msg);
    }*/


    /**
     * 用户取消下单
     * @param msg
     * @return
     */
    /*
    @LcnTransaction
    @Transactional
    @Override
    public Message orderCancel(Message msg){
        logger.info("用户取消下单", JSONObject.toJSONString(msg));

        //订单状态修改

        //用户账户金额返回 解冻 退款

        //用户账户流水

        msg.getData().setData(JSON.toJSONString(""));
        msg.setDesc("");
        return msg;
    }*/

}
