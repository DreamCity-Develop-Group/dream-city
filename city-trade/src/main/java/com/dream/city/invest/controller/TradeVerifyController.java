package com.dream.city.invest.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.TradeVerify;
import com.dream.city.invest.service.TradeVerifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/trade")
public class TradeVerifyController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TradeVerifyService verifyService;

    /**
     * 新增审核
     * @param record
     * @return
     */
    @RequestMapping("/insertTradeVerify")
    public Result insertTradeVerify(@RequestBody TradeVerify record){
        logger.info("新增审核记录，{}", record);
        boolean success = Boolean.FALSE;
        String desc = "新增投资记录失败";
        Integer resultDate = 0;
        try {
            resultDate = verifyService.insertTradeVerify(record);
            desc = "新增审核成功";
            success = Boolean.TRUE;
        }catch (Exception e){
            desc = "新增审核异常";
            logger.error(desc,e);
        }
        return new Result<Integer>(success,desc,resultDate);
    }

    /**
     * 审核
     * @param record
     * @return
     */
    @RequestMapping("/updateTradeVerify")
    public Result updateTradeVerify(@RequestBody TradeVerify record){
        logger.info("审核，{}", record);
        boolean success = Boolean.FALSE;
        String desc = "审核失败";
        Integer resultDate = 0;
        try {
            resultDate = verifyService.updateTradeVerify(record);
            desc = "审核成功";
            success = Boolean.TRUE;
        }catch (Exception e){
            desc = "审核异常";
            logger.error(desc,e);
        }
        return new Result<Integer>(success,desc,resultDate);
    }

    /**
     * 根据id获取审核记录
     * @param verifyId
     * @return
     */
    @RequestMapping("/getTradeVerifyBiId/{verifyId}")
    public Result getTradeVerifyBiId(@PathVariable Integer verifyId){
        logger.info("根据id获取审核记录，{}", verifyId);
        boolean success = Boolean.FALSE;
        String desc = "根据id获取审核记录失败";
        TradeVerify resultDate = null;
        try {
            resultDate = verifyService.getTradeVerifyBiId(verifyId);
            desc = "根据id获取审核记录成功";
            success = Boolean.TRUE;
        }catch (Exception e){
            desc = "根据id获取审核记录异常";
            logger.error(desc,e);
        }
        return new Result<TradeVerify>(success,desc,resultDate);
    }

    /**
     * 获取审核列
     * @param record
     * @return
     */
    @RequestMapping("/getTradeVerifyList")
    public Result getTradeVerifyList(@RequestBody TradeVerify record){
        logger.info("获取审核列表，{}", record);
        boolean success = Boolean.FALSE;
        String desc = "新增投资记录失败";
        List<TradeVerify> resultDate = null;
        try {
            verifyService.getTradeVerifyList(record);
            desc = "获取审核列成功";
            success = Boolean.TRUE;
        }catch (Exception e){
            desc = "获取审核列异常";
            logger.error(desc,e);
        }
        return new Result<List<TradeVerify>>(success,desc,resultDate);
    }



}
