package com.dream.city.job;

import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.InvestStatus;
import com.dream.city.base.model.enu.OrderState;
import com.dream.city.base.model.enu.RuleKey;
import com.dream.city.base.model.mapper.CommerceRelationMapper;
import com.dream.city.base.model.mapper.EarnFalldownLogMapper;
import com.dream.city.base.model.mapper.SalesOrderMapper;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.*;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO 购买订单处理逻辑
 *
 * @author WVv
 */
@Slf4j
public class SalesOrderJob extends QuartzJobBean {

    @Autowired
    InvestRuleService ruleService;
    @Autowired
    SalesOrderService salesOrderService;
    @Autowired
    RedisUtils redisUtils;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PlayerAccountService playerAccountService;

    @Autowired
    private CommerceRelationService commerceRelationService;

    @Autowired
    private CommerceRelationMapper commerceRelationMapper;

    @Autowired
    private SalesOrderMapper salesOrderMapper;

    @Autowired
    private RelationTreeService  treeService;

    /**
     *TODO
     * 1、取出所有【在线玩家】【等待处理】订单集合
     * 1.1、取出总累计超时次数
     * 2、遍历所有订单，推送提示到玩家
     * 3、在2中，将已经超时的订单转接给【上上家】，生成转接记录，累计超时次数
     * 4、
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(new Date() + "    超时兑换交易处理");

        //TODO 取出所有的支付成功的交易
        List<SalesOrder> salesOrders = salesOrderService.getSalesOrdersByState(3);
        //超时几小时后
        int expireHours = 1;
        //超时或拒绝几次解除商事关系
        int rejectTimes = 3;

        salesOrders.forEach(salesOrder -> {
            String order_id = salesOrder.getOrderId();
            long curr = System.currentTimeMillis() / 1000;
            long updateDate = salesOrder.getUpdateTime().getTime() / 1000;
            //不到1小时
            if (curr - updateDate < expireHours*3600){
                log.info(new Date() + "  order_id = "+order_id+"  交易未超过1小时");
                return;
            }
            String buyer_id = salesOrder.getOrderPlayerBuyer();
            String seller_id = salesOrder.getOrderPlayerSeller();
            Player player = playerAccountService.getPlayerByPlayerId(buyer_id);
            //检测是否超时或拒绝超过2次
            int status = OrderState.REFUSE_EXPIRED_FINISHED.getStatus();

            //int sellerRejectTimes = salesOrderService.selectSalesSellerRejectTimes(buyer_id, seller_id, status);

            //检测是否还是商会兑换关系。已经>=3次超时或拒绝发货
            int currRejectTimes = 1;
            CommerceRelation commerceRelation = commerceRelationService.getCommerceRelationBySonId(buyer_id);

            if (commerceRelation != null){
                currRejectTimes = commerceRelation.getTreeLevel() ;

                //超时或拒绝次数加1
                commerceRelation.setTreeLevel(currRejectTimes + 1);
                commerceRelationMapper.updateByPrimaryKey(commerceRelation);
            }
            else {
                CommerceRelation relation = new CommerceRelation();
                relation.setCreateTime(new Date());
                relation.setTreeParentId(seller_id);
                relation.setTreePlayerId(buyer_id);
                relation.setTreeLevel(1);
                commerceRelationMapper.insertSelective(relation);
            }

            //订单置位2
            salesOrder.setOrderState(2);
            salesOrderMapper.updateSalesOrder(salesOrder);

            //由上级或平台发货
            finishOrderByParent(salesOrder,buyer_id,null);

            if (currRejectTimes > rejectTimes){

                return;
            }


            //1次不管，2次警告
            if (currRejectTimes == rejectTimes - 1){
                //推送到前台显示
                String desc = "您已错过["+player.getPlayerName()+"] 2次兑换请求，超过3次将强制解除商会，请慎重";
                messageService.pushJobMessage(seller_id, desc);
            }
            //3次也有情提示一下
            else if (currRejectTimes == rejectTimes){
                String desc = "您已错过["+player.getPlayerName()+"] 3次兑换请求，已制解除商会兑换关系，但收益分成等不受影响";
                messageService.pushJobMessage(seller_id, desc);
            }

        });


    }

    /**
     *
     * @param order
     * @param buyerId
     * @param sellerId
     * @param usdt
     * @param mt
     */
    private void finishOrderByParent(SalesOrder order, String buyerId, String sellerId){
        //完全引用SalesOrderServiceImpl-->buyMtFinish 中代码 。问题1，sales_order 中卖家可能与实际卖家不一致

        //找出上家
        RelationTree tree = treeService.getParent(buyerId);
        String parentId = tree.getTreePlayerId();

        PlayerAccount sellerAccount ;
        if (parentId.equalsIgnoreCase("system")){
            sellerAccount = playerAccountService.getPlayerAccount(tree.getTreePlayerId());
        }else {
            sellerAccount = playerAccountService.getPlayerAccount(parentId);
        }

        List<PlayerAccount> accounts = new ArrayList<>();

        PlayerAccount buyerAccount = playerAccountService.getPlayerAccount(buyerId);
        //增加买家MT额度
        buyerAccount.setAccMt(buyerAccount.getAccMt().add(order.getOrderAmount()));
        buyerAccount.setAccMtAvailable(buyerAccount.getAccMtAvailable().add(order.getOrderAmount()));
        //扣除买家USDT额度
        //小心负数
        BigDecimal amount = buyerAccount.getAccUsdt().subtract(order.getOrderPayAmount());
        if (amount.compareTo(BigDecimal.ZERO) <= 0){
            amount = BigDecimal.ZERO;
        }
        buyerAccount.setAccUsdt(amount);
        BigDecimal amount2 = buyerAccount.getAccUsdtFreeze().subtract(order.getOrderPayAmount());
        if (amount2.compareTo(BigDecimal.ZERO) <= 0){
            amount2 = BigDecimal.ZERO;
        }
        buyerAccount.setAccUsdtFreeze(amount2);

        accounts.add(buyerAccount);


        //增加卖家USDT额度
        sellerAccount.setAccUsdt(sellerAccount.getAccUsdt().add(order.getOrderPayAmount()));
        sellerAccount.setAccUsdtAvailable(sellerAccount.getAccUsdtAvailable().add(order.getOrderPayAmount()));
        //扣除卖家MT额度
        BigDecimal amount3 = sellerAccount.getAccMt().subtract(order.getOrderAmount());
        if (amount3.compareTo(BigDecimal.ZERO) <= 0){
            amount3 = BigDecimal.ZERO;
        }
        sellerAccount.setAccMt(amount3);
        BigDecimal amount4 = sellerAccount.getAccMtAvailable().subtract(order.getOrderAmount());
        if (amount4.compareTo(BigDecimal.ZERO) <= 0){
            amount4 = BigDecimal.ZERO;
        }
        sellerAccount.setAccMtAvailable(amount4);

        accounts.add(sellerAccount);

        playerAccountService.updatePlayerAccounts(accounts);
    }
}
