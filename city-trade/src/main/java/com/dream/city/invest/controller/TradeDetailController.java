package com.dream.city.invest.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.TradeDetail;
import com.dream.city.base.model.req.PlayerTradeReq;
import com.dream.city.base.model.resp.PlayerTradeResp;
import com.dream.city.invest.service.TradeDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 玩家交易明细
 */
@RestController
@RequestMapping("/trade")
public class TradeDetailController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TradeDetailService detailService;


    /**
     * 新增交易明细
     * @param record
     * @return
     */
    @RequestMapping("/insertTradeDetail")
    public Result<Integer> insertTradeDetail(@RequestBody TradeDetail record){
        logger.info("新增交易明细，{}", record);
        boolean success = Boolean.FALSE;
        String desc = "新增交易明细失败";
        int resultDate = 0;
        try {
            resultDate = detailService.insert(record);
            desc = "新增交易明细成功";
            success = Boolean.TRUE;
        }catch (Exception e){
            desc = "新增交易明细异常";
            logger.error(desc,e);
        }
        return new Result<>(success,desc,resultDate);
    }


    /**
     * 根据id获取交易明细
     * @param id
     * @return
     */
    @RequestMapping("/getTradeDetailById/{id}")
    public Result<TradeDetail> getTradeDetailById(@PathVariable("id") Integer id){
        logger.info("根据id获取交易明细，id：{}", id);
        boolean success = Boolean.FALSE;
        String desc = "根据id获取交易明细成功";
        TradeDetail resultDate = null;
        try {
            resultDate = detailService.getById(id);
            success = Boolean.TRUE;
        }catch (Exception e){
            desc = "根据Id获取交易明细异常";
            logger.error(desc,e);
        }
        return new Result<>(success,desc,resultDate);
    }



    /**
     * 获取交易明细列表
     * @param record
     * @return
     */
    @RequestMapping("/getTradeDetailList")
    public Result<List<PlayerTradeResp>> getTradeDetailList(@RequestBody PlayerTradeReq record){
        logger.info("获取交易明细列表，{}", record);
        boolean success = Boolean.FALSE;
        String desc = "获取交易明细失败";
        List<PlayerTradeResp> resultDate = null;
        try {
            resultDate = detailService.getList(record);
            success = Boolean.TRUE;
            desc = "获取交易明细列表成功";
        }catch (Exception e){
            desc = "获取交易明细列表异常";
            logger.error(desc,e);
        }
        return new Result<>(success,desc,resultDate);
    }


}
