package com.dream.city.property.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.req.CityInvestReq;
import com.dream.city.base.model.resp.InvestResp;
import com.dream.city.property.service.InvestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
     *
     * @param invest
     * @return
     */
    @RequestMapping("/insertInvest")
    public Result<Integer> insertInvest(@RequestBody CityInvest invest) {
        logger.info("新建物业，{}", invest);
        boolean success = Boolean.FALSE;
        int i = investService.insertInvest(invest);
        String desc = "新建物业失败";
        if (i > 0) {
            desc = "新建物业成功";
            success = Boolean.TRUE;
        }
        return Result.result(success, desc,success?ReturnStatus.SUCCESS.getStatus():ReturnStatus.FAILED.getStatus(), i);
    }

    /**
     * 查询物业
     *
     * @param invest
     * @return
     */
    @RequestMapping("/getInvest")
    public Result<InvestResp> getInvest(@RequestBody CityInvestReq invest) {
        logger.info("查询物业，{}", invest);
        boolean success = Boolean.FALSE;
        InvestResp data = investService.getInvest(invest);
        String desc = "查询物业失败";
        if (data != null) {
            desc = "查询物业成功";
            success = Boolean.TRUE;
        }
        return Result.result(success, desc,success?ReturnStatus.SUCCESS.getStatus():ReturnStatus.FAILED.getStatus(), data);
    }

    /**
     * 更新物业
     *
     * @param invest
     * @return
     */
    @RequestMapping("/updateInvest")
    public Result<Integer> updateInvest(@RequestBody CityInvest invest) {
        logger.info("更新物业，{}", invest);
        boolean success = Boolean.FALSE;
        int i = investService.updateInvest(invest);
        String desc = "更新物业失败";
        if (i > 0) {
            desc = "更新物业成功";
            success = Boolean.TRUE;
        }
        return Result.result(success, desc,success?ReturnStatus.SUCCESS.getStatus():ReturnStatus.FAILED.getStatus(), i);
    }

    /**
     * 物业列表
     *
     * @param invest
     * @return
     */
    @RequestMapping("/getInvestLsit")
    public Result<List<InvestResp>> getInvestLsit(@RequestBody CityInvestReq invest) {
        logger.info("物业列表，{}", invest);

        List<InvestResp> data = investService.getInvestLsit(invest);
        if (data.size() > 0) {
            return Result.result(true, "查询物业列表成功", ReturnStatus.SUCCESS.getStatus(), data);
        } else {
            return Result.result(false, "查询物业列表失败", ReturnStatus.FAILED.getStatus(), data);
        }
    }


    /**
     * 物业列表
     *
     * @param invest
     * @return
     */
    @RequestMapping("/getPropertyLsit")
    public Result<List<InvestResp>> getPropertyLsit(@RequestBody CityInvestReq invest) {
        logger.info("物业列表，{}", invest);
        List<InvestResp> data = investService.getInvestLsit(invest);
        if (data.size() > 0) {
            return Result.result(true, "查询物业列表成功", ReturnStatus.SUCCESS.getStatus(), data);
        } else {
            return Result.result(false, "查询物业列表失败", ReturnStatus.FAILED.getStatus(), null);
        }
    }


}
