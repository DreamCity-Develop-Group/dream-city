package com.dream.city.invest.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerEarning;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.invest.service.EarningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 玩家提现收入
 */
@RestController
@RequestMapping("/earning")
public class EarningController {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EarningService earningService;
    @Autowired
    RedisUtils redisUtils;


    /**
     * 删除玩家提现收入
     * @param earnId
     * @return
     */
    @RequestMapping("/deleteEarningById")
    public Result deleteEarningById(@RequestParam Integer earnId) {
        logger.info("删除玩家提现收入,earnId:{}", earnId);

        int i = 0;
        boolean b = Boolean.TRUE;
        String descr = "删除玩家提现收入成功";
        try {
            i = earningService.deleteEarningById(earnId);
        }catch (Exception e){
            b = Boolean.FALSE;
            descr = "删除玩家提现收入失败";
            logger.error("删除玩家提现收入异常",e);
        }
        Result result = new Result(b,descr,i);
        return result;
    }

    /**
     * 新增玩家提现收入
     * @param record
     * @return
     */
    @RequestMapping("/insertEarning")
    public Result insertEarning(@RequestBody PlayerEarning record){
        logger.info("新增玩家提现收入:{}", record);

        int i = 0;
        boolean b = Boolean.TRUE;
        String descr = "新增玩家提现收入成功";
        try {
            i = earningService.insertEarning(record);
        }catch (Exception e){
            b = Boolean.FALSE;
            descr = "新增玩家提现收入失败";
            logger.error("新增玩家提现收入异常",e);
        }

        Result result = new Result(b,descr,i);
        return result;
    }

    /**
     * 查询玩家提现收入
     * @param earnId
     * @return
     */
    @RequestMapping("/getEarning")
    public Result getEarning(@RequestParam Integer earnId){
        logger.info("查询玩家提现收入,earnId:{}", earnId);

        PlayerEarning earning = null;
        boolean b = Boolean.TRUE;
        String descr = "查询玩家提现收入成功";

        try {
            earning = earningService.getEarning(earnId);
        }catch (Exception e){
            b = Boolean.FALSE;
            descr = "查询玩家提现收入失败";
            logger.error("查询玩家提现收入异常",e);
        }

        Result result = new Result(b, descr,earning);
        return result;
    }

    /**
     * 查询玩家提现收入列表
     * @param record
     * @return
     */
    @RequestMapping("/getEarningList")
    public Result getEarningList(@RequestBody PlayerEarning record) {
        logger.info("查询玩家提现收入列表:{}", record);

        List<PlayerEarning> earnings = null;
        boolean b = Boolean.TRUE;
        String descr = "查询玩家提现收入列表成功";

        try {
            earnings = earningService.getEarningList(record);
        } catch (Exception e) {
            b = Boolean.FALSE;
            descr = "查询玩家提现收入列表失败";
            logger.error("查询玩家提现收入列表异常", e);
        }

        Result result = new Result(b, descr, earnings);
        return result;
    }


    /**
     * 更新玩家提现收入
     * @param record
     * @return
     */
    @RequestMapping("/updateEarningById")
    public Result updateEarningById(@RequestBody PlayerEarning record) {
        logger.info("更新玩家提现收入:{}", record);

        int i = 0;
        boolean b = Boolean.TRUE;
        String descr = "更新玩家提现收入成功";
        try {
            i = earningService.insertEarning(record);
        }catch (Exception e){
            descr = "更新玩家提现收入失败";
            b = Boolean.FALSE;
        }

        Result result = new Result(b, descr,i);
        return result;
    }


}
