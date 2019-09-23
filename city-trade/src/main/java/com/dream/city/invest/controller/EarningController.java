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
 * 提现规则
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
     * 删除提现规则
     * @param earnId
     * @return
     */
    @RequestMapping("/deleteEarningById")
    public Result deleteEarningById(@RequestParam Integer earnId) {
        logger.info("游戏音效设置,earnId:{}", earnId);

        int i = 0;
        boolean b = Boolean.TRUE;
        String descr = "删除提现规则成功";
        try {
            i = earningService.deleteEarningById(earnId);
        }catch (Exception e){
            b = Boolean.FALSE;
            descr = "删除提现规则失败";
            logger.error("删除提现规则异常",e);
        }
        Result result = new Result(b,descr,i);
        return result;
    }

    /**
     * 新增提现规则
     * @param record
     * @return
     */
    @RequestMapping("/insertEarning")
    public Result insertEarning(@RequestBody PlayerEarning record){
        logger.info("新增提现规则:{}", record);

        int i = 0;
        boolean b = Boolean.TRUE;
        String descr = "新增提现规则成功";
        try {
            i = earningService.insertEarning(record);
        }catch (Exception e){
            b = Boolean.TRUE;
            descr = "新增提现规则失败";
            logger.error("新增提现规则异常",e);
        }

        Result result = new Result(b,descr,i);
        return result;
    }

    /**
     * 查询提现规则
     * @param earnId
     * @return
     */
    @RequestMapping("/getEarning")
    public Result getEarning(@RequestParam Integer earnId){
        logger.info("查询提现规则,earnId:{}", earnId);

        PlayerEarning earning = null;
        boolean b = Boolean.TRUE;
        String descr = "查询提现规则成功";

        try {
            earning = earningService.getEarning(earnId);
        }catch (Exception e){
            b = Boolean.FALSE;
            descr = "查询提现规则失败";
            logger.error("查询提现规则异常",e);
        }

        Result result = new Result(b, descr,earning);
        return result;
    }

    /**
     * 查询提现规则列表
     * @param record
     * @return
     */
    @RequestMapping("/getEarningList")
    public Result getEarningList(@RequestBody PlayerEarning record) {
        logger.info("查询提现规则列表:{}", record);

        List<PlayerEarning> earnings = null;
        boolean b = Boolean.TRUE;
        String descr = "查询提现规则列表成功";

        try {
            earnings = earningService.getEarningList(record);
        } catch (Exception e) {
            b = Boolean.FALSE;
            descr = "查询提现规则列表失败";
            logger.error("查询提现规则列表异常", e);
        }

        Result result = new Result(b, descr, earnings);
        return result;
    }


    /**
     * 更新提现规则
     * @param record
     * @return
     */
    @RequestMapping("/updateEarningById")
    public Result updateEarningById(@RequestBody PlayerEarning record) {
        logger.info("更新提现规则:{}", record);

        int i = 0;
        boolean b = Boolean.TRUE;
        String descr = "更新提现规则成功";
        try {
            i = earningService.insertEarning(record);
        }catch (Exception e){
            descr = "更新提现规则失败";
            b = Boolean.FALSE;
        }

        Result result = new Result(b, descr,i);
        return result;
    }


}
