package com.dream.city.property.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.property.service.InvestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 物业
 */
@RestController
@RequestMapping("/property")
public class PropertyController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InvestService investService;


    /**
     * 新建物业
     * @param invest
     * @return
     */
    @RequestMapping("/insertInvest")
    public Result insertInvest(@RequestBody CityInvest invest) {
        logger.info("新建物业，{}", invest);

        int i = investService.insertInvest(invest);
        String desc = "新建物业失败";
        if (i > 0){
            desc = "新建物业成功";
        }
        Result<Integer> result = new Result<>(Boolean.TRUE,desc,i);
        return result;
    }

    /**
     * 查询物业
     * @param invest
     * @return
     */
    @RequestMapping("/getInvestByIdOrName")
    public Result<CityInvest> getInvestByIdOrName(@RequestBody CityInvest invest) {
        logger.info("查询物业，{}", invest);

        CityInvest data = investService.getInvestByIdOrName(invest);
        String desc = "查询物业失败";
        if (data != null){
            desc = "查询物业成功";
        }
        Result<CityInvest> result = new Result<>(Boolean.TRUE,desc,data);
        return result;
    }

    /**
     * 更新物业
     * @param invest
     * @return
     */
    @RequestMapping("/updateInvest")
    public Result updateInvest(@RequestBody CityInvest invest){
        logger.info("更新物业，{}", invest);

        int i = investService.updateInvest(invest);
        String desc = "更新物业失败";
        if (i > 0){
            desc = "更新物业成功";
        }
        Result<Integer> result = new Result<>(Boolean.TRUE,desc,i);
        return result;
    }

    /**
     * 物业列表
     * @param invest
     * @return
     */
    @RequestMapping("/getInvestLsit")
    public Result getInvestLsit(@RequestBody CityInvest invest) {
        logger.info("物业列表，{}", invest);

        String desc = "查询物业列表失败";
        List<CityInvest> data = null;
        try {
            data = investService.getInvestLsit(invest);
            desc = "查询物业列表成功";
        }catch (Exception e){
            logger.error("查询物业列表异常",e);
        }
        Result<List<CityInvest>> result = new Result<>(Boolean.TRUE,desc,data);
        return result;
    }















}
