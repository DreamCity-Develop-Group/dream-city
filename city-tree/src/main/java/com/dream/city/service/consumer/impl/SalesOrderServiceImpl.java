package com.dream.city.service.consumer.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.entity.SalesOrder;
import com.dream.city.base.model.enu.OrderState;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.model.mapper.SalesOrderMapper;
import com.dream.city.base.utils.KeyGenerator;
import com.dream.city.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    InvestRuleService ruleService;

    @LcnTransaction
    @Transactional
    @Override
    public List<SalesOrder> selectSalesOrder(String playerId,Integer start,Integer size)  throws BusinessException {
        return salesOrderMapper.selectSalesSellerOrder(playerId,start,size);
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<SalesOrder> selectSalesOrderUnSend(String playerId)  throws BusinessException{
        return salesOrderMapper.selectPlayerSalesOrdersByState(playerId,OrderState.PAY.getStatus());
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<SalesOrder> selectSalesOrderOvertime(String playerId)  throws BusinessException{
        return salesOrderMapper.selectPlayerSalesOrdersByState(playerId,OrderState.EXPIRED.getStatus());
    }

    @LcnTransaction
    @Transactional
    @Override
    public SalesOrder getSalesOrder(Long id) throws BusinessException {
        return salesOrderMapper.selectSalesOrderByPrimaryKey(id);
    }

    @LcnTransaction
    @Transactional
    @Override
    public SalesOrder getSalesOrder(String orderId) throws BusinessException {
        return salesOrderMapper.getSalesOrderByOrderId(orderId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<SalesOrder> selectSalesSellerOrder(String playerId) throws BusinessException {

        return salesOrderMapper.selectSalesSellerOrders(playerId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public List<SalesOrder> selectSalesBuyerOrder(String playerId) throws BusinessException {
        return salesOrderMapper.selectSalesBuyerOrder(playerId);
    }

    @LcnTransaction
    @Transactional
    @Override
    public SalesOrder getBuyerNoPayOrder(String playerId) throws BusinessException {
        List<SalesOrder> orders = salesOrderMapper.selectPlayerSalesOrdersByState(playerId,OrderState.PAID.getStatus());
        if (orders.size()>0){
            return orders.get(0);
        }
        return null;
    }

    /**
     * 创建支付订单
     *
     *      * 3、冻结购买的玩家的账户相应的额度，减可用额度
     *      * 4、如果上家自动发货，扣除上家MT,增加上家USDT收入，扣除购买玩家的总额度和冻结额度
     *      * 5、如果上家未自动发货，不作处理，等待玩家处理或者任务中心调度
     *
     * @param buyAmount
     * @param rate
     * @param playerId
     * @return
     */
    @LcnTransaction
    @Transactional
    @Override
    public Result buyMtCreate(BigDecimal buyAmount, BigDecimal rate, String playerId)  throws BusinessException{
        //判断是否有足够的支付USDT:剩余额度
        //TODO 买家
        PlayerAccount buyerAccount = playerAccountService.getPlayerAccount(playerId);
        if (buyerAccount.getAccUsdtAvailable().compareTo(buyAmount.multiply(new BigDecimal(0.1))) < 0) {
            return new Result(false, "USDT剩余可用额度不足", ReturnStatus.NOT_ENOUGH.getStatus());
        }
        //支付usdt额度
        BigDecimal usdtPay = buyAmount.multiply(rate);

        //TODO 锁定买家的USDT
        buyerAccount.setAccUsdtAvailable(buyerAccount.getAccUsdtAvailable().subtract(usdtPay));
        buyerAccount.setAccUsdtFreeze(buyerAccount.getAccUsdtFreeze().add(usdtPay));
        int buyerAccountUp = playerAccountService.updatePlayerAccount(buyerAccount);
        if (buyerAccountUp>0){
            //找出上家的playerId=>parentId
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
            order.setOrderPayAmount(usdtPay);
            order.setOrderPlayerBuyer(playerId);
            order.setOrderPlayerSeller(parentId);
            order.setOrderState(OrderState.CREATE.getStatus());
            order.setOrderAmount(buyAmount);
            //改变订单状态==》[TODO 订单新建状态]
            int insertSalesOrder = salesOrderMapper.createSalesOrder(order);

            if (insertSalesOrder>0){
                //TODO 自动发货
                if (StringUtils.isNotBlank(tree.getSendAuto()) && "1".equals(tree.getSendAuto())){
                    //TODO 卖家
                    PlayerAccount sellerAccount = playerAccountService.getPlayerAccount(parentId);
                    if (sellerAccount.getAccMtAvailable().compareTo(buyAmount)<0){
                        //TODO 关闭卖家自动发货功能
                        tree.setSendAuto("0");
                        treeService.closeAutoSend(tree);
                        log.info("卖家["+buyerAccount.getAccPlayerId()+"]由于MT备货额度不足被关闭自动发货功能！");
                    }else{
                        //锁定卖家MT
                        sellerAccount.setAccMtAvailable(sellerAccount.getAccMtAvailable().subtract(buyAmount));
                        sellerAccount.setAccMtFreeze(sellerAccount.getAccMtFreeze().add(buyAmount));
                        int lock = playerAccountService.updatePlayerAccount(sellerAccount);
                        if (lock>0){
                            //改变订单状态==》[TODO 订单待支付]
                            order.setOrderState(OrderState.PAID.getStatus());
                            int finish = salesOrderMapper.updateSalesOrder(order);

                            if (finish>0){
                                //更新买家数据
                                List<PlayerAccount> accounts = new ArrayList<>();
                                buyerAccount.setAccMt(buyerAccount.getAccMt().add(buyAmount));
                                buyerAccount.setAccMtAvailable(buyerAccount.getAccMtAvailable().add(buyAmount));

                                buyerAccount.setAccUsdt(buyerAccount.getAccUsdt().subtract(usdtPay));
                                buyerAccount.setAccUsdtFreeze(buyerAccount.getAccUsdtFreeze().subtract(usdtPay));
                                accounts.add(buyerAccount);

                                //更新卖家数据
                                sellerAccount.setAccUsdt(sellerAccount.getAccUsdt().add(usdtPay));
                                sellerAccount.setAccUsdtAvailable(sellerAccount.getAccUsdtAvailable().add(usdtPay));

                                sellerAccount.setAccMt(sellerAccount.getAccMt().subtract(buyAmount));
                                sellerAccount.setAccMtFreeze(sellerAccount.getAccMtFreeze().subtract(buyAmount));
                                accounts.add(sellerAccount);

                                playerAccountService.updatePlayerAccounts(accounts);

                                return new Result(true, "下单成功", ReturnStatus.SUCCESS.getStatus(), order);
                            }
                            //return new Result(true, "下单成功", ReturnStatus.SUCCESS.getStatus(), order);
                        }
                    }
                }else{
                    order.setOrderState(OrderState.PAID.getStatus());
                    int finish = salesOrderMapper.updateSalesOrder(order);
                    return Result.result(false,"提交购买订单成功",ReturnStatus.SUCCESS.getStatus());
                }

                return Result.result(false,"购买失败",ReturnStatus.ERROR.getStatus());
            }

            return Result.result(false,"购买失败",ReturnStatus.FAILED.getStatus());
        }else{
            return Result.result(false,"购买失败",ReturnStatus.ERROR.getStatus());
        }
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
    @LcnTransaction
    @Transactional
    @Override
    public Result buyMtFinish(String playerId, String pass) throws BusinessException {
        Player player = playerService.getPlayer(playerId);
        if (player != null) {
            //没有密码或密码为空
            if (StringUtils.isBlank(pass)) {
                return Result.result(false, ReturnStatus.ERROR_PASS.getDesc(), ReturnStatus.ERROR_PASS.getStatus());
            }
            //未设置密码
            if (StringUtils.isBlank(player.getPlayerTradePass())) {
                return Result.result(false, ReturnStatus.NOTSET_PASS.getDesc(), ReturnStatus.NOTSET_PASS.getStatus());
            }
            //密码不符合
            if (!pass.equals(player.getPlayerTradePass())) {
                return Result.result(false,
                        ReturnStatus.ERROR_PASS.getDesc(),
                        ReturnStatus.ERROR_PASS.getStatus());
            }
        }
        //找出订单，更新状态
        SalesOrder order = this.getBuyerNoPayOrder(playerId);
        if (order != null && OrderState.PAID.getStatus()==Integer.valueOf(order.getOrderState())) {
            //找出上家
            RelationTree tree = treeService.getTreeByPlayerId(playerId);
            String parentId = tree.getTreeParentId();

            if (StringUtils.isNotBlank(tree.getSendAuto()) && tree.getSendAuto().equals("1")){
                //改变订单状态==》[TODO 完成状态]
                order.setOrderState(OrderState.FINISHED.getStatus());
                //order.setUpdateTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date())));
                order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                int up = salesOrderMapper.updateSalesOrder(order);
                if (up>0){
                    List<PlayerAccount> accounts = new ArrayList<>();


                    PlayerAccount buyerAccount = playerAccountService.getPlayerAccount(playerId);
                    //增加买家MT额度
                    buyerAccount.setAccMt(buyerAccount.getAccMt().add(order.getOrderAmount()));
                    buyerAccount.setAccMtAvailable(buyerAccount.getAccMtAvailable().add(order.getOrderAmount()));
                    //扣除买家USDT额度
                    buyerAccount.setAccUsdt(buyerAccount.getAccUsdt().subtract(order.getOrderPayAmount()));
                    buyerAccount.setAccUsdtFreeze(buyerAccount.getAccUsdtFreeze().subtract(order.getOrderPayAmount()));

                    accounts.add(buyerAccount);

                    PlayerAccount sellerAccount = playerAccountService.getPlayerAccount(parentId);
                    //增加卖家USDT额度
                    sellerAccount.setAccUsdt(sellerAccount.getAccUsdt().add(order.getOrderPayAmount()));
                    sellerAccount.setAccUsdtAvailable(sellerAccount.getAccUsdtAvailable().add(order.getOrderPayAmount()));
                    //扣除卖家USDT额度
                    sellerAccount.setAccMt(sellerAccount.getAccMt().subtract(order.getOrderAmount()));
                    sellerAccount.setAccMtFreeze(sellerAccount.getAccMtFreeze().subtract(order.getOrderAmount()));

                    accounts.add(sellerAccount);

                    playerAccountService.updatePlayerAccounts(accounts);

                    //TODO 订单支付成功
                    return Result.result(true, "订单已支付成功", ReturnStatus.SUCCESS.getStatus());
                }
            }else{
                //TODO 订单支付成功,等发货
                //改变订单状态 =>[TODO 已支付]
                order.setOrderState(OrderState.PAY.getStatus());
                //order.setUpdateTime(Timestamp.valueOf(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date())));
                order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                int up = salesOrderMapper.updateSalesOrder(order);
                return Result.result(true, "订单已支付成功", ReturnStatus.SUCCESS.getStatus());
            }
        } else {
            return Result.result(false, "订单已经支付或被取消", ReturnStatus.ERROR.getStatus());
        }
        return Result.result(false, "验证失败", ReturnStatus.SET_FAILED.getStatus());
    }

    @LcnTransaction
    @Transactional
    @Override
    public BigDecimal getUsdtToMtRate(String playerId) throws BusinessException {
        //规则比率
        Integer level = 0;
        RelationTree relationTree = treeService.getTreeByPlayerId(playerId);
        if (relationTree != null) {
            level = relationTree.getTreeLevel();
        }
        BigDecimal ruleRate = ruleService.getLevelRuleRate(playerId, level);
        return ruleRate;
    }

    @LcnTransaction
    @Transactional
    @Override
    public Result sendOrderMt(List<SalesOrder> orders) throws BusinessException {
        List<PlayerAccount> accounts = new ArrayList<>();
        for (SalesOrder order : orders) {
            PlayerAccount account = playerAccountService.getPlayerAccount(order.getOrderPlayerBuyer());
            account.setAccMt(account.getAccMt().add(order.getOrderAmount()));
            account.setAccMtAvailable(account.getAccUsdtAvailable().add(order.getOrderAmount()));
            accounts.add(account);
            //设置订单状态
            order.setOrderState(OrderState.SHIPPED.getStatus());
        }
        //更新订单状态
        salesOrderMapper.sendOrderMt(orders);
        //更新账户数据
        playerAccountService.updatePlayerAccounts(accounts);
        return new Result(true, "发货成功", ReturnStatus.SUCCESS.getStatus());
    }


}
