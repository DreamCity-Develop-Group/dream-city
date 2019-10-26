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
    public Result<Map<String,Object>> extract(@RequestParam("playerId")String playerId,@RequestParam("inType")Integer inType){
        int i = 0;
        int code = ReturnStatus.FAILED.getStatus();
        boolean b = Boolean.FALSE;
        String msg = "";
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("investId","");
            result.put("inType", inType);
            result.put("investMoney",0);
            result.put("extractable",0);
            result.put("extract",0);
            result.put("incomeLeft",0);
            result.put("totalTax",0);
            result.put("personTax",0);
            result.put("enterpriseTax",0);
            result.put("quotaTax",0);
            result.put("state",ReturnStatus.INVEST_MANAGEMENT.getCode());
            result.put("openState", "Y");

            PlayerEarningResp earningResp = earningService.getPlayerEarningByPlayerId(playerId,inType);
            if (earningResp != null){
                result.put("investId",earningResp.getEarnInvestId());
                result.put("extractable",earningResp.getEarnCurrent());
                result.put("personTax",earningResp.getEarnPersonalTax());
                result.put("enterpriseTax",earningResp.getEarnEnterpriseTax());
                result.put("quotaTax",earningResp.getEarnQuotaTax());

                InvestOrder order = orderService.getOrderByPlayerIdInvestId(playerId,earningResp.getEarnInvestId());
                result.put("investMoney",order.getOrderAmount());

                if (earningResp.getEarnMax().compareTo(earningResp.getEarnCurrent()) >= 0 && earningResp.getIsWithdrew()==2){
                    // 修改账户
                    PlayerAccount account = accountService.getPlayerAccount(playerId);
                    if (account != null){
                        BigDecimal taxTotal = earningResp.getEarnEnterpriseTax().add(earningResp.getEarnPersonalTax()).add(earningResp.getEarnQuotaTax());
                        BigDecimal extract = earningResp.getEarnCurrent().setScale( 0, BigDecimal.ROUND_DOWN );
                        BigDecimal incomeLeft = earningResp.getEarnCurrent().subtract(extract);
                        //BigDecimal earnTotal = extract.subtract(taxTotal);

                        if (earningResp.getEarnMax().compareTo(earningResp.getWithdrewTotal().add(extract)) < 0){
                            result.put("state",ReturnStatus.INVEST_SUBSCRIBE.getCode());
                            msg = "已达到最大提取限额";
                            return Result.result(b,msg, code,result);
                        }

                        account.setAccUsdt(account.getAccUsdt().add(extract));
                        account.setAccUsdtAvailable(account.getAccUsdtAvailable().add(extract));
                        // 添加累计收益记录
                        account.setAccIncome(account.getAccIncome().add(extract));
                        i = accountService.updatePlayerAccountById(account);


                        // 改变earn表记录状态为3
                        if (i > 0) {
                            PlayerEarning updateEarningReq = new PlayerEarning();
                            updateEarningReq.setEarnId(earningResp.getEarnId());
                            updateEarningReq.setIsWithdrew(3);
                            updateEarningReq.setWithdrewTotal(earningResp.getWithdrewTotal().add(extract));
                            updateEarningReq.setWithdrewTimes(earningResp.getWithdrewTimes()+1);
                            updateEarningReq.setEarnCurrent(BigDecimal.ZERO);
                            i = earningService.updateEarningById(updateEarningReq);
                        }else {
                            msg = "添加累计收益记录失败";
                            return Result.result(b,msg, code,result);
                        }

                        // 交易记录
                        PlayerTrade trade = null;
                        if (i > 0) {
                            //提取入账
                            PlayerTrade insertTradeReq = new PlayerTrade();
                            insertTradeReq.setTradeAccId(account.getAccId());
                            insertTradeReq.setTradeOrderId(order.getOrderId());
                            insertTradeReq.setTradePlayerId(playerId);
                            insertTradeReq.setTradeStatus(TradeStatus.IN.getCode());
                            insertTradeReq.setInOutStatus(AmountDynType.IN.getCode());
                            insertTradeReq.setTradeAmount(extract);
                            insertTradeReq.setTradeType(TradeType.INVEST_EARNINGS.getCode());
                            insertTradeReq.setTradeDesc("提取已入账");
                            trade = tradeService.insertPlayerTrade(insertTradeReq);

                            //提取扣税
                            if (trade != null) {
                                insertTradeReq.setTradeId(trade.getTradeId() + 1);
                            }
                            insertTradeReq.setTradeStatus(TradeStatus.OUT.getCode());
                            insertTradeReq.setInOutStatus(AmountDynType.OUT.getCode());
                            insertTradeReq.setTradeAmount(taxTotal);
                            insertTradeReq.setTradeType(TradeType.INVEST_TAX.getCode());
                            insertTradeReq.setTradeDesc("提取扣税");
                            trade = tradeService.insertPlayerTrade(insertTradeReq);
                        }else {
                            msg = "改变earn表记录状态为3失败";
                        }
                        // 交易流水
                        TradeDetail tradeDetailResp = null;
                        if (trade != null) {
                            //提取金额流水
                            TradeDetail tradeDetailReq = new TradeDetail();
                            tradeDetailReq.setTradeId(trade.getTradeId());
                            tradeDetailReq.setPlayerId(playerId);
                            tradeDetailReq.setOrderId(order.getOrderId());
                            tradeDetailReq.setTradeAmount(extract);
                            tradeDetailReq.setTradeDetailType(TradeDetailType.USDT_EARNINGS.getCode());
                            tradeDetailReq.setDescr("投资提取,提取金额：" + extract +"USDT");
                            tradeDetailResp = tradeDetailService.insert(tradeDetailReq);

                            //扣税流水
                            if (earningResp.getEarnPersonalTax().compareTo(BigDecimal.ZERO) > 0){
                                if (tradeDetailResp != null) {
                                    tradeDetailReq.setId(tradeDetailResp.getId()+1);
                                }
                                tradeDetailReq.setTradeAmount(earningResp.getEarnPersonalTax());
                                tradeDetailReq.setTradeDetailType(TradeDetailType.MT_INVEST_PERSONAL_TAX.getCode());
                                tradeDetailReq.setDescr("投资提取,个人所得税:" + earningResp.getEarnPersonalTax() +"MT");
                                tradeDetailResp = tradeDetailService.insert(tradeDetailReq);
                            }

                            if (earningResp.getEarnEnterpriseTax().compareTo(BigDecimal.ZERO) > 0){
                                if (tradeDetailResp != null) {
                                    tradeDetailReq.setId(tradeDetailResp.getId()+1);
                                }
                                tradeDetailReq.setTradeAmount(earningResp.getEarnEnterpriseTax());
                                tradeDetailReq.setTradeDetailType(TradeDetailType.MT_INVEST_ENTERPRISE_TAX.getCode());
                                tradeDetailReq.setDescr("投资提取,企业所得税：" + earningResp.getEarnEnterpriseTax() +"MT");
                                tradeDetailResp = tradeDetailService.insert(tradeDetailReq);
                            }

                            if (earningResp.getEarnQuotaTax().compareTo(BigDecimal.ZERO) > 0){
                                if (tradeDetailResp != null) {
                                    tradeDetailReq.setId(tradeDetailResp.getId()+1);
                                }
                                tradeDetailReq.setTradeAmount(earningResp.getEarnQuotaTax());
                                tradeDetailReq.setTradeDetailType(TradeDetailType.MT_INVEST_QUOTA_TAX.getCode());
                                tradeDetailReq.setDescr("投资提取,定额税：" + earningResp.getEarnQuotaTax() +"MT");
                                tradeDetailService.insert(tradeDetailReq);
                            }
                        }else {
                            msg = "添加交易记录失败";
                        }

                        // 添加  账户 记录
                        if (i > 0){
                            PlayerAccountLog accountLog = new PlayerAccountLog();
                            accountLog.setAddress(account.getAccAddr());
                            accountLog.setAmountMt(taxTotal);
                            accountLog.setAmountUsdt(extract);
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
                            result.put("extract",extract);
                            result.put("incomeLeft",incomeLeft);
                            result.put("totalTax",taxTotal);
                            result.put("state",ReturnStatus.INVEST_MANAGEMENT.getCode());
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
