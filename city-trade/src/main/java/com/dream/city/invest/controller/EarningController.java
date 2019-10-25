package com.dream.city.invest.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.*;
import com.dream.city.base.model.resp.InvestOrderResp;
import com.dream.city.base.model.resp.PlayerEarningResp;
import com.dream.city.base.utils.DataUtils;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.invest.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 玩家提现收入
 */
@RestController
@RequestMapping("/earning")
public class EarningController {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EarningService earningService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PlayerTradeService tradeService;
    @Autowired
    private TradeDetailService tradeDetailService;
    @Autowired
    private OrderService orderService;
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


    /**
     * 投资提取
     *
     * @param playerId
     * @return
     */
    @RequestMapping("/extract")
    public Result<Map<String,Object>> extract(@RequestParam("playerId")String playerId,@RequestParam("investId")Integer investId){
        int i = 0;
        int code = 0;
        boolean b = Boolean.FALSE;
        String msg = "";
        Map<String,Object> result = new HashMap<>();
        try {
            InvestOrder order = orderService.getOrderByPlayerIdInvestId(playerId,investId);
            if (order != null){
                PlayerEarningResp earningResp = earningService.getPlayerEarningByPlayerId(playerId,investId);
                if (null != earningResp && earningResp.getEarnMax().compareTo(earningResp.getEarnCurrent()) >= 0 && earningResp.getIsWithdrew()==2){
                    // 修改账户
                    PlayerAccount account = accountService.getPlayerAccount(playerId);
                    if (account != null){
                        BigDecimal taxTotal = earningResp.getEarnEnterpriseTax().add(earningResp.getEarnPersonalTax()).add(earningResp.getEarnQuotaTax());
                        BigDecimal extract = earningResp.getEarnCurrent().setScale( 0, BigDecimal.ROUND_DOWN );
                        BigDecimal incomeLeft = earningResp.getEarnCurrent().subtract(extract);
                        BigDecimal earnTotal = extract.subtract(taxTotal);

                        account.setAccUsdt(account.getAccUsdt().add(earnTotal));
                        account.setAccUsdtAvailable(account.getAccUsdtAvailable().add(earnTotal));
                        // 添加累计收益记录
                        account.setAccIncome(account.getAccIncome().add(taxTotal));
                        i = accountService.updatePlayerAccountById(account);


                        // 改变earn表记录状态为3
                        if (i > 0) {
                            PlayerEarning updateEarningReq = new PlayerEarning();
                            updateEarningReq.setEarnId(earningResp.getEarnId());
                            updateEarningReq.setIsWithdrew(3);
                            updateEarningReq.setEarnCurrent(BigDecimal.ZERO);
                            i = earningService.updateEarningById(updateEarningReq);
                        }else {
                            msg = "添加累计收益记录失败";
                        }

                        // 交易记录
                        PlayerTrade trade = null;
                        if (i > 0) {
                            PlayerTrade insertTradeReq = new PlayerTrade();
                            insertTradeReq.setTradeAccId(account.getAccId());
                            insertTradeReq.setTradeOrderId(order.getOrderId());
                            insertTradeReq.setTradePlayerId(playerId);
                            insertTradeReq.setTradeStatus(TradeStatus.IN.getCode());
                            insertTradeReq.setInOutStatus(AmountDynType.IN.getCode());
                            insertTradeReq.setTradeAmount(earnTotal);
                            insertTradeReq.setTradeType(TradeType.INVEST_EARNINGS.getCode());
                            insertTradeReq.setTradeDesc("提取已入账");
                            trade = tradeService.insertPlayerTrade(insertTradeReq);
                        }else {
                            msg = "改变earn表记录状态为3失败";
                        }
                        // 交易流水
                        if (trade != null) {
                            TradeDetail tradeDetail = new TradeDetail();
                            tradeDetail.setTradeId(trade.getTradeId());
                            tradeDetail.setPlayerId(playerId);
                            tradeDetail.setOrderId(order.getOrderId());
                            tradeDetail.setTradeAmount(earnTotal);
                            tradeDetail.setTradeDetailType(TradeDetailType.USDT_EARNINGS.getCode());
                            tradeDetail.setDescr("投资提取,提取金额：" + extract
                                    + ",个人所得税:" + earningResp.getEarnPersonalTax()
                                    + ",企业所得税：" + earningResp.getEarnEnterpriseTax()
                                    + ",定额税：" + earningResp.getEarnQuotaTax());
                            i = tradeDetailService.insert(tradeDetail);
                        }else {
                            msg = "添加交易记录失败";
                        }

                        // 添加  账户 记录
                        if (i > 0){
                            PlayerAccountLog accountLog = new PlayerAccountLog();
                            accountLog.setAddress(account.getAccAddr());
                            accountLog.setAmountMt(BigDecimal.ZERO);
                            accountLog.setAmountUsdt(earnTotal);
                            accountLog.setPlayerId(playerId);
                            accountLog.setType(1);
                            accountLog.setDesc("收入账户多余的额度");
                            accountLog.setCreateTime(new Date());
                            i = accountService.addAccountLog(accountLog);
                        }else {
                            msg = "添加交易流水失败";
                        }

                        if (i > 0) {
                            b = Boolean.TRUE;
                            code = ReturnStatus.SUCCESS.getStatus();
                            msg="提取成功";
                            result.put("investId",investId);
                            result.put("investMoney",order.getOrderAmount());
                            result.put("extractable",earningResp.getEarnCurrent());
                            result.put("extract",earnTotal);
                            result.put("incomeLeft",incomeLeft);
                            result.put("personTax",earningResp.getEarnPersonalTax());
                            result.put("enterpriseTax",earningResp.getEarnEnterpriseTax());
                            result.put("quotaTax",earningResp.getEarnQuotaTax());
                            result.put("state",ReturnStatus.INVEST_SUBSCRIBE.getCode());
                            result.put("inType", earningResp.getInType());
                            result.put("openState", "N");

                            //

                        }else {
                            msg = "添加账户记录失败";
                        }
                    }else {
                        msg = "没找到用户账户";
                    }
                }else {
                    msg = "没找到投资记录";
                }
            }else {
                msg = "收益未满，暂不可提取";
                code = ReturnStatus.NOT_FINISHED.getStatus();
            }
        }catch (Exception e){
            logger.error("投资提取异常", e);
        }

        return Result.result(b,msg, code,result);
    }




}
