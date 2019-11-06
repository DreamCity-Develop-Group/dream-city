package com.dream.city.controller;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.entity.SalesOrder;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/sales")
@Slf4j
public class SalesOrderController {

    @Autowired
    private SalesOrderService salesOrderService;
    @Autowired
    private PlayerAccountService accountService;
    @Autowired
    private PlayerAccountService playerAccountService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private RelationTreeService treeService;
    @Autowired
    InvestRuleService ruleService;


    /**
     * 获取订单
     *
     * @param playerId
     * @return
     */
    @RequestMapping("/get/salesOrder")
    public Result getSalesOrder(@RequestParam("playerId") String playerId, @RequestParam("page") Integer page) {
        Integer size = 10;

        Integer start = 0;
        if (page == 0 || page == 1) {
            page = 1;
            size = 20;
        } else {
            start = page * size;
            size = 10;
        }

        List<SalesOrder> orders = salesOrderService.selectSalesOrder(playerId, start, size);

        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List orderList = new ArrayList();
        orders.forEach(order -> {
            if (order.getOrderPlayerBuyer().equals(playerId)) {
                log.info("自己的购买订单:[" + order.getOrderPlayerBuyer() + "]");
            } else {
                Player player = playerService.getPlayerByPlayerId(order.getOrderPlayerBuyer());
                Map data = new HashMap();
                data.put("date", formats.format(order.getCreateTime() == null ? new Date() : order.getCreateTime()));
                data.put("player", player.getPlayerNick());
                data.put("amount", order.getOrderAmount());
                data.put("pay", order.getOrderPayAmount());
                data.put("state", order.getOrderState());
                data.put("orderId", order.getOrderId());
                orderList.add(data);
            }
        });
        return new Result("success", ReturnStatus.SUCCESS.getStatus(), orderList);
    }

    /**
     * 获取订单数量-未处理的请求
     *
     * @param playerId
     * @return
     */
    @RequestMapping("/get/sales/num")
    public Result getSalesNum(@RequestParam("playerId") String playerId) {
        List<SalesOrder> orders = salesOrderService.selectSalesOrderUnSend(playerId);
        if (orders.size() > 0) {
            return Result.result(true, "成功",ReturnStatus.SUCCESS.getStatus(),orders.size());
        }
        return Result.result(false, ReturnStatus.FAILED.getStatus());
    }

    /**
     * 获取订单数量-未处理的请求
     *
     * @param playerId
     * @return
     */
    @RequestMapping("/get/sales/overtime")
    public Result getSalesOvertime(@RequestParam("playerId") String playerId) {
        List<SalesOrder> orders = salesOrderService.selectSalesOrderOvertime(playerId);
        if (orders.size() > 0) {
            return Result.result(true, "成功",ReturnStatus.SUCCESS.getStatus(), orders.size());
        }
        return Result.result(false, "失败",ReturnStatus.FAILED.getStatus());
    }

    /**
     * 获取订单数量-未处理的请求
     *
     * @param playerId
     * @return
     */
    @RequestMapping("/get/sales/rate")
    public Result getSalesRate(@RequestParam("playerId") String playerId) {
        BigDecimal ruleRate = salesOrderService.getUsdtToMtRate(playerId);
        return Result.result(true, "兑换比率", ReturnStatus.SUCCESS.getStatus(), ruleRate);
    }

    /**
     * 购买MT==>创建订单
     *
     * @param buyAmount
     * @param playerId
     * @return
     */
    @RequestMapping("/player/buy/mt")
    public Result playerBuyMtCreate(@RequestParam("amount") BigDecimal buyAmount, @RequestParam("playerId") String playerId) {
        if (StringUtils.isBlank(playerId)) {
            return Result.result(false,"参数错误", ReturnStatus.INVALID.getStatus());
        }
        if (buyAmount.compareTo(new BigDecimal(0.00)) < 0) {
            return Result.result(false,"购买额度不能小于0", ReturnStatus.INVALID.getStatus());
        }
        //支付比率 USDT 和 MT
        BigDecimal rate = salesOrderService.getUsdtToMtRate(playerId);

        Map<String, String> data = new HashMap<>();

        //如果额度不足
        PlayerAccount playerAccount = playerAccountService.getPlayerAccount(playerId);
        BigDecimal payUsdt = buyAmount.multiply(rate);
        if (playerAccount.getAccUsdtAvailable().compareTo(payUsdt) < 0) {
            return Result.result(false, ReturnStatus.NOT_ENOUGH.getDesc(), ReturnStatus.NOT_ENOUGH.getStatus(), data);
        }
        //获取未完成的订单
        SalesOrder salesOrder = salesOrderService.getBuyerNoPayOrder(playerId);
        if (salesOrder != null) {
            Result result = Result.result(false,ReturnStatus.NOT_FINISHED.getDesc(),ReturnStatus.NOT_FINISHED.getStatus(),data);
            return result;
        }
        //创建订单
        return salesOrderService.buyMtCreate(buyAmount, rate, playerId);

    }

    /**
     * 验证支付密码 ==》完成订单
     *
     * @param confirmPass
     * @param playerId
     * @return
     */
    @RequestMapping("/player/check/pass")
    public Result playerBuyMtFinish(@RequestParam("playerId") String playerId, @RequestParam("confirmPass") String confirmPass) {
        //找出待支付订单
        SalesOrder salesOrder = salesOrderService.getBuyerNoPayOrder(playerId);
        if (salesOrder != null) {
            //验证支付密码
            Result result = accountService.checkPayPass(playerId, confirmPass);
            //完成支付
            if (result.getSuccess()) {
                return salesOrderService.buyMtFinish(playerId, confirmPass);
            } else {
                return result;
            }
        } else {
            Result result = Result.result(
                    false,
                    ReturnStatus.INVALID.getDesc()+":没有订单",
                    ReturnStatus.INVALID.getStatus()
            );
            return result;
        }

    }


    /**
     * 卖家发货,单条或者多条，遍历货单ID
     */
    @RequestMapping("/player/seller/send")
    public Result playerSellerSend(@RequestParam("playerId") String playerId, @RequestParam("ordersId") String ordersId) {
        if (StringUtils.isBlank(playerId) || null == ordersId || StringUtils.isBlank(ordersId)) {
            return new Result(false, "参数错误", ReturnStatus.INVALID.getStatus());
        }
        List<SalesOrder> salesOrders = new ArrayList<>();
        BigDecimal amountMt = new BigDecimal(0.00);
        BigDecimal availbleMt = accountService.getPlayerAccountMTAvailble(playerId);
        String[] orders = ordersId.split("_");
        int size = orders.length;
        int i = 0;
        Map<String,Boolean> ordersStatus = new HashMap<>();
        for (String order : orders) {
            SalesOrder salesOrder = salesOrderService.getSalesOrder(order);
            BigDecimal amount = salesOrder.getOrderAmount();
            //遍历核计订单总额度
            amountMt = amountMt.add(amount);
            //额度检测
            if (availbleMt.compareTo(amountMt) < 0) {
                ordersStatus.put(order,Boolean.FALSE);
                //break;
            } else {
                i++;
                salesOrders.add(salesOrder);
                ordersStatus.put(order,Boolean.TRUE);
            }
        }


        //额度检测
        if (i == 0) {
            return Result.result(false, "可用MT额度不足，请及时备货", ReturnStatus.NOT_ENOUGH.getStatus());
        }
        //修改订单状态和修改玩家账户额度
        Result result = salesOrderService.sendOrderMt(salesOrders);
        int ret = size - i;
        String message = ret == 0 ? "已经发货成功订单[" + i + "]个" : "已经发货成功订单[" + i + "]个，请尽快充值补额，将剩余[" + ret + "]个订单发货！";
        if (result.getSuccess()) {
            return Result.result(
                    true,
                    message,
                    ReturnStatus.SUCCESS.getStatus(),
                    ordersStatus);
        } else {
            return Result.result(false, "发货失败", ReturnStatus.NOT_ENOUGH.getStatus());
        }
    }

    /**
     * 取得当前玩家的所有MT购买请求:玩家是卖家
     *
     * @param playerId
     * @return
     */
    @RequestMapping("/get/buyer/order")
    public Result getBuyerOrder(@RequestParam("playerId") String playerId) {
        if (StringUtils.isBlank(playerId)) {
            return new Result("参数错误", ReturnStatus.INVALID.getStatus());
        }
        List<SalesOrder> orders = salesOrderService.selectSalesSellerOrder(playerId);

        return new Result("success", ReturnStatus.SUCCESS.getStatus(), orders);
    }

    /**
     * 取得当前玩家的所有MT购买请求:玩家是买家
     *
     * @param playerId
     * @return
     */
    @RequestMapping("/get/seller/order")
    public Result getSellerOrder(@RequestParam("playerId") String playerId) {
        if (StringUtils.isBlank(playerId)) {
            return new Result("参数错误", ReturnStatus.INVALID.getStatus());
        }
        List<SalesOrder> orders = salesOrderService.selectSalesBuyerOrder(playerId);

        return new Result("sucess", ReturnStatus.SUCCESS.getStatus(), orders);
    }



}
