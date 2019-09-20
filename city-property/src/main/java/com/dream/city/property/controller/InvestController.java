package com.dream.city.property.controller;

import com.dream.city.base.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 投资项
 */
@RestController
@RequestMapping("/property")
public class InvestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());




    /**
     * 新建物业
     * @param jsonReq
     * @return
     */
    @RequestMapping("/insertInvest")
    public Result insertInvest(@RequestBody String jsonReq) {
        logger.info("新建物业，{}", jsonReq);
        Result<Integer> result = new Result<>();

        return result;
    }

    /**
     * 查询物业
     * @param jsonReq
     * @return
     */
    @RequestMapping("/getInvest")
    public Result getInvest(@RequestBody String jsonReq) {
        logger.info("点赞，{}", jsonReq);
        Result<Integer> result = new Result<>();

        return result;
    }

    /**
     * 更新物业
     * @param jsonReq
     * @return
     */
    @RequestMapping("/updateInvest")
    public Result updateInvest(@RequestBody String jsonReq){
        logger.info("更新物业，{}", jsonReq);
        Result<Integer> result = new Result<>();

        return result;
    }

    /**
     * 物业列表
     * @param jsonReq
     * @return
     */
    @RequestMapping("/getInvestLsit")
    public Result getInvestLsit(@RequestBody String jsonReq) {
        logger.info("物业列表，{}", jsonReq);
        Result<Integer> result = new Result<>();

        return result;
    }















}
