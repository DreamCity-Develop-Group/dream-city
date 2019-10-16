package com.dream.city.property.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.req.CityInvestReq;
import com.dream.city.base.model.resp.InvestResp;
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
    public Result<Integer> insertInvest(@RequestBody CityInvest invest) {
        logger.info("新建物业，{}", invest);
        boolean success = Boolean.FALSE;
        int i = investService.insertInvest(invest);
        String desc = "新建物业失败";
        if (i > 0){
            desc = "新建物业成功";
            success = Boolean.TRUE;
        }
        Result<Integer> result = new Result<>(success,desc,i);
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
        boolean success = Boolean.FALSE;
        CityInvest data = investService.getInvestByIdOrName(invest);
        String desc = "查询物业失败";
        if (data != null){
            desc = "查询物业成功";
            success = Boolean.TRUE;
        }
        Result<CityInvest> result = new Result<>(success,desc,data);
        return result;
    }

    /**
     * 更新物业
     * @param invest
     * @return
     */
    @RequestMapping("/updateInvest")
    public Result<Integer> updateInvest(@RequestBody CityInvest invest){
        logger.info("更新物业，{}", invest);
        boolean success = Boolean.FALSE;
        int i = investService.updateInvest(invest);
        String desc = "更新物业失败";
        if (i > 0){
            desc = "更新物业成功";
            success = Boolean.FALSE;
        }
        Result<Integer> result = new Result<>(success,desc,i);
        return result;
    }

    /**
     * 物业列表
     * @param invest
     * @return
     */
    @RequestMapping("/getInvestLsit")
    public Result<List<InvestResp>> getInvestLsit(@RequestBody CityInvestReq invest) {
        logger.info("物业列表，{}", invest);
        boolean success = Boolean.FALSE;
        String desc = "查询物业列表失败";
        List<InvestResp> data = null;
        try {
            data = investService.getInvestLsit(invest);
            desc = "查询物业列表成功";
            success = Boolean.TRUE;
        }catch (Exception e){
            logger.error("查询物业列表异常",e);
        }
        return new Result<>(success,desc,data);
    }















}
