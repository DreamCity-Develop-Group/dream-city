package com.dream.city.service.impl;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.entity.SalesOrder;
import com.dream.city.base.model.enu.InvestStatus;
import com.dream.city.base.model.enu.OrderState;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.mapper.RelationTreeMapper;
import com.dream.city.base.model.mapper.PlayerAccountMapper;
import com.dream.city.base.model.mapper.SalesOrderMapper;
import com.dream.city.base.utils.KeyGenerator;
import com.dream.city.service.PlayerAccountService;
import com.dream.city.service.PlayerService;
import com.dream.city.service.RelationTreeService;
import com.dream.city.service.SalesOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class SalesOrderServiceImpl implements SalesOrderService {
    @Autowired
    private SalesOrderMapper salesOrderMapper;


    @Autowired
    private RelationTreeService  treeService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerAccountService playerAccountService;

    @Override
    public List<SalesOrder> selectSalesOrder(String playerId) {
        return salesOrderMapper.selectSalesSellerOrder(playerId);
    }

    @Override
    public List<SalesOrder> selectSalesOrderUnSend(String playerId) {
        return salesOrderMapper.selectPlayerSalesOrdersByState(playerId,OrderState.PAY.getStatus());
    }

    @Override
    public List<SalesOrder> selectSalesOrderOvertime(String playerId) {
        return salesOrderMapper.selectPlayerSalesOrdersByState(playerId,OrderState.EXPIRED.getStatus());
    }

    @Override
    public SalesOrder getSalesOrder(Long id) {
        return salesOrderMapper.selectSalesOrderByPrimaryKey(id);
    }

    @Override
    public SalesOrder getSalesOrder(String orderId) {
        return salesOrderMapper.getSalesOrderByOrderId(orderId);
    }

    @Override
    public List<SalesOrder> selectSalesSellerOrder(String playerId) {

        return salesOrderMapper.selectSalesSellerOrder(playerId);
    }

    @Override
    public List<SalesOrder> selectSalesBuyerOrder(String playerId) {
        return salesOrderMapper.selectSalesBuyerOrder(playerId);
    }

    @Override
    public SalesOrder getBuyerNoPayOrder(String playerId) {
        List<SalesOrder> orders = salesOrderMapper.selectPlayerSalesOrdersByState(playerId,OrderState.CREATE.getStatus());
        if (orders.size()>0){
            return orders.get(0);
        }
        return null;
    }

    /**
     * 创建支付订单
     *
     * * 3、冻结购买的玩家的账户相应的额度，减可用额度
     *      * 4、如果上家自动发货，扣除上家MT,增加上家USDT收入，扣除购买玩家的总额度和冻结额度
     *      * 5、如果上家未自动发货，不作处理，等待玩家处理或者任务中心调度
     *
     * @param buyAmount
     * @param rate
     * @param playerId
     * @return
     */
    @Override
    public Result buyMtCreate(BigDecimal buyAmount, BigDecimal rate, String playerId) {
        //判断是否有足够的支付USDT:剩余额度
        PlayerAccount playerAccount = playerAccountService.getPlayerAccount(playerId);
        if (playerAccount.getAccUsdtAvailable().compareTo(buyAmount.multiply(new BigDecimal(0.1))) < 0) {
            return new Result(false, "USDT剩余可用额度不足", ReturnStatus.NOT_ENOUGH.getStatus());
        }
        //找出上家
        RelationTree tree = treeService.getTreeByPlayerId(playerId);
        String parentId = tree.getTreeParentId();

        //生成订单
        SalesOrder order = new SalesOrder();
        String orderId = KeyGenerator.generateOrderID();
        order.setId(0);
        order.setOrderId(orderId);
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        order.setOrderId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        order.setOrderBuyType("MT");
        order.setOrderPayType("USDT");

        //支付usdt额度
        BigDecimal usdtPay = buyAmount.multiply(rate);
        order.setOrderPayAmount(usdtPay);
        order.setOrderPlayerBuyer(playerId);
        order.setOrderPlayerSeller(parentId);
        order.setOrderState(OrderState.CREATE.getStatus());
        order.setOrderAmount(buyAmount);
        salesOrderMapper.createSalesOrder(order);

        //todo 如果上家设置了自动发货并且有足够额度，直接购买成功
        if (StringUtils.isNotBlank(tree.getSendAuto()) && tree.getSendAuto().equals("1")) {
            //上级额度不足
            PlayerAccount account = playerAccountService.getPlayerAccount(parentId);
            if (account.getAccMtAvailable().compareTo(buyAmount) < 0) {
                /*return Result.result(
                        false,
                        ReturnStatus.NOT_ENOUGH_PARENT.getDesc(),
                        ReturnStatus.NOT_ENOUGH_PARENT.getStatus(),
                        null
                );*/
                log.info("上级玩家额度不足");
            } else {

                /**
                 *TODO 锁定买家卖家数据，但是不更新订单状态
                 */
                List<PlayerAccount> accounts = new ArrayList<>();
                //增加买家MT额度

                //冻结买家USDT额度
                playerAccount.setAccUsdtAvailable(playerAccount.getAccUsdtAvailable().subtract(usdtPay));
                playerAccount.setAccUsdtFreeze(playerAccount.getAccUsdtFreeze().add(usdtPay));
                accounts.add(playerAccount);
                //////////////////////////////////////////////////////////////////////////////////////

                PlayerAccount playerAccount1 = playerAccountService.getPlayerAccount(parentId);
                //增加卖家USDT额度
                //playerAccount.setAccUsdt(playerAccount.getAccUsdt().add(usdtPay));
                //playerAccount.setAccUsdtAvailable(playerAccount.getAccUsdtAvailable().add(usdtPay));
                //冻结卖家MT额度
                playerAccount1.setAccMtAvailable(playerAccount1.getAccMtAvailable().subtract(buyAmount));
                playerAccount1.setAccMtFreeze(playerAccount1.getAccMtFreeze().add(buyAmount));
                accounts.add(playerAccount1);


                playerAccountService.updatePlayerAccounts(accounts);
            }

        }

        //todo 由任务管理去完成相应的业务逻辑


        return new Result(true, "下单成功", ReturnStatus.SUCCESS.getStatus(), order);
    }

    /**
     * 验证支付密码并完成订单
     * 1、验证密码
     * 2、修改订单状态
     * 3、扣除相应额度
     * @param playerId
     * @param pass
     * @return
     */
    @Override
    public Result buyMtFinish(String playerId, String pass) {
        Player player = playerService.getPlayer(playerId);
        if (player != null) {
            if (StringUtils.isBlank(pass)) {
                return Result.result(false, ReturnStatus.ERROR_PASS.getDesc(), ReturnStatus.ERROR_PASS.getStatus());
            }
            if (StringUtils.isBlank(player.getPlayerTradePass())) {
                return Result.result(false, ReturnStatus.NOTSET_PASS.getDesc(), ReturnStatus.NOTSET_PASS.getStatus());
            }
            if (!pass.equals(player.getPlayerTradePass())) {
                return Result.result(false,
                        ReturnStatus.ERROR_PASS.getDesc(),
                        ReturnStatus.ERROR_PASS.getStatus());
            }
        }
        //找出订单，更新状态
        SalesOrder order = this.getBuyerNoPayOrder(playerId);
        if (order != null && OrderState.PAY.equals(order.getOrderState())) {
            //找出上家
            RelationTree tree = treeService.getTreeByPlayerId(playerId);
            String parentId = tree.getTreeParentId();


            List<PlayerAccount> accounts = new ArrayList<>();

            //增加买家MT额度
            PlayerAccount playerAccount1 = playerAccountService.getPlayerAccount(playerId);
            playerAccount1.setAccMt(playerAccount1.getAccMt().add(order.getOrderAmount()));
            playerAccount1.setAccMtAvailable(playerAccount1.getAccMtAvailable().add(order.getOrderAmount()));
            //扣除买家USDT额度
            playerAccount1.setAccUsdt(playerAccount1.getAccUsdt().subtract(order.getOrderPayAmount()));
            playerAccount1.setAccUsdtFreeze(playerAccount1.getAccUsdtFreeze().subtract(order.getOrderPayAmount()));

            accounts.add(playerAccount1);

            PlayerAccount playerAccount = playerAccountService.getPlayerAccount(parentId);
            //增加卖家USDT额度
            playerAccount.setAccUsdt(playerAccount.getAccUsdt().add(order.getOrderPayAmount()));
            playerAccount.setAccUsdtAvailable(playerAccount.getAccUsdtAvailable().add(order.getOrderPayAmount()));
            //扣除卖家USDT额度
            playerAccount.setAccMt(playerAccount.getAccMt().subtract(order.getOrderAmount()));
            playerAccount.setAccMtFreeze(playerAccount.getAccMtFreeze().subtract(order.getOrderAmount()));

            accounts.add(playerAccount);

            playerAccountService.updatePlayerAccounts(accounts);


            //改变订单状态
            order.setOrderState(OrderState.FINISHED.getStatus());
            order.setUpdateTime(Timestamp.valueOf(new SimpleDateFormat("yMd Hms").format(new Date())));
            salesOrderMapper.updateSalesOrder(order);

            //TODO 订单支付成功
            return new Result(true, "订单已支付成功", 200);
        } else {
            return new Result(false, "订单已经支付或被取消", 500);
        }
    }

    @Override
    public BigDecimal getUsdtToMtRate() {
        return new BigDecimal(1.0);
    }

    @Override
    public Result sendOrderMt(List<SalesOrder> orders) {
        List<PlayerAccount> accounts = new ArrayList<>();
        for (SalesOrder order : orders) {
            PlayerAccount account = playerAccountService.getPlayerAccount(order.getOrderPlayerBuyer());
            account.setAccMt(account.getAccMt().add(order.getOrderAmount()));
            account.setAccMtAvailable(account.getAccUsdtAvailable().add(order.getOrderAmount()));
            accounts.add(account);
        }
        salesOrderMapper.sendOrderMt(orders);
        playerAccountService.updateBuyerAccount(accounts);
        return new Result(true, "发货成功", 200);
    }


}
