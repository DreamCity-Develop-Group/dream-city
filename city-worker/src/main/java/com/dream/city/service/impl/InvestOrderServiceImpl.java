package com.dream.city.service.impl;

import com.dream.city.base.model.entity.InvestOrder;
import com.dream.city.base.model.entity.InvestRule;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.enu.InvestStatus;
import com.dream.city.domain.mapper.InvestOrderMapper;
import com.dream.city.service.InvestOrderService;
import com.dream.city.service.InvestService;
import com.dream.city.service.PlayerLikesService;
import com.dream.city.service.RelationTreeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Wvv
 */
public class InvestOrderServiceImpl implements InvestOrderService {
    @Autowired
    InvestOrderMapper investOrderMapper;

    @Autowired
    PlayerLikesService playerLikesService;

    @Autowired
    RelationTreeService relationTreeService;

    @Autowired
    InvestService investService;

    /**
     * 按规则取得当天所有适合规则的记录
     *
     * @param investId
     * @param rules
     * @return
     */
    @Override
    public Map<String, List<InvestOrder>> getInvestOrdersByCurrentDay(Integer investId, List<InvestRule> rules) {
        int[] states = new int[]{1, 2, 3};
        List<InvestOrder> investOrders = investOrderMapper.getInvestOrdersByCurrentDay(investId, InvestStatus.SUBSCRIBED.getStatus());
        //final List<InvestOrder>[] investOrdersSucess = new List[]{new ArrayList<>()};
        final Map<String, List<InvestOrder>> investOrdersSucess = new Hashtable<>();
        long total = investOrders.size();
        //Map<InvestOrder>
        //根据权重排序，权重数值小的权限大，应该先计算
        rules.sort((r1, r2) -> (r1.getRuleLevel() - r2.getRuleLevel()));

        rules.forEach((rule) -> {
            switch (rule.getRuleOpt()) {
                case "OPT_RATE":
                    switch (rule.getRuleFlag()) {
                        case "ALL_ORDERS"://所有的40%
                            long all = Long.parseLong(total * rule.getRuleRate() + "");
                            List<InvestOrder> allOrders = getRandomOrders(investOrders, all);
                            investOrdersSucess.put(rule.getRuleFlag(), allOrders);
                            investOrders.removeAll(allOrders);
                            break;
                        case "FIRST_TIME"://第一次投资 20
                            long first = Long.parseLong(total * rule.getRuleRate() + "");
                            List<InvestOrder> firstOrders = getFirstTimeOrders(investOrders, first);
                            investOrdersSucess.put(rule.getRuleFlag(), firstOrders);
                            investOrders.removeAll(firstOrders);
                            break;
                        case "LIKES_GATHER"://点赞最多 20
                            long likes = Long.parseLong(total * rule.getRuleRate() + "");
                            List<InvestOrder> likesOrders = getLiksGatherOrders(investOrders, likes);
                            investOrdersSucess.put(rule.getRuleFlag(), likesOrders);
                            investOrders.removeAll(likesOrders);
                            break;
                        case "INVEST_LONG"://投资时间最长
                            long longs = Long.parseLong(total * rule.getRuleRate() + "");
                            List<InvestOrder> longsOrders = getInvestLongOrders(investOrders, longs);
                            investOrdersSucess.put(rule.getRuleFlag(), longsOrders);
                            investOrders.removeAll(longsOrders);
                            break;
                        case "ORDER_OTHERS"://其他
                            long other = Long.parseLong(total * rule.getRuleRate() + "");
                            List<InvestOrder> othersOrders = getOtherOrders(investOrders, other);
                            if (othersOrders != null) {
                                investOrdersSucess.put(rule.getRuleFlag(), othersOrders);
                                investOrders.removeAll(othersOrders);
                            }

                            break;
                        default:

                    }
                    break;
                case "OPT_TOP":
                    switch (rule.getRuleFlag()) {
                        case "TOP_MEMBERS":
                            long top = Long.parseLong(total * rule.getRuleRate() + "");
                            List<InvestOrder> topOrders = getTopMembersOrders(investOrders, top);
                            investOrdersSucess.put(rule.getRuleFlag(), topOrders);
                            investOrders.removeAll(topOrders);
                            break;
                        case "":
                            break;
                        default:
                    }
                    break;
                default:


            }
        });
        investOrders.forEach(order -> order.setOrderState(String.valueOf(InvestStatus.SUBSCRIBE)));
        investOrderMapper.setOrdersState(investOrders);
        return investOrdersSucess;
    }

    /**
     * 第一次投资
     *
     * @param investOrders
     * @param first
     * @return
     */
    private List<InvestOrder> getFirstTimeOrders(List<InvestOrder> investOrders, long first) {
        List<InvestOrder> firstOders = new ArrayList<>();
        //根据在投资记录中成功的订单是否唯一
        investOrders.forEach(order -> {
            if (isFirstTime(order.getOrderPayerId())) {
                firstOders.add(order);
            }
        });
        firstOders.sort((o1, o2) -> {
            return (int) (o1.getCreateTime().getTime() - o2.getCreateTime().getTime());
        });

        return firstOders.subList(0, (int) first);
    }

    /**
     * 判断是否第一次投资
     *
     * @param orderPayerId
     * @return
     */
    private boolean isFirstTime(String orderPayerId) {
        int[] states = new int[]{1, 2, 3};
        List<InvestOrder> orders = investOrderMapper.getSuccessInvestOrdersByPlayerId(orderPayerId, states);
        return orders.size() > 1;
    }

    /**
     * 收获点赞数最多的
     *
     * @param investOrders
     * @param likes
     * @return
     */
    private List<InvestOrder> getLiksGatherOrders(List<InvestOrder> investOrders, long likes) {
        List<InvestOrder> success = new ArrayList<>();
        //点赞数比结果数多
        investOrders.sort((o1, o2) -> {
            return playerLikesService.getLikesGetByPlayerId(o2.getOrderPayerId()) -
                    playerLikesService.getLikesGetByPlayerId(o1.getOrderPayerId());
        });
        return investOrders.subList(0, (int) likes);
    }

    private List<InvestOrder> getInvestLongOrders(List<InvestOrder> investOrders, long longs) {

        return null;
    }

    private List<InvestOrder> getOtherOrders(List<InvestOrder> investOrders, long other) {
        if (other == 0) {
            return null;
        }

        return investOrders.subList(0, (int) other);
    }

    /**
     * 设置 批量订单状态
     *
     * @param orders
     */
    @Override
    public void setOrderState(List<InvestOrder> orders, InvestStatus status) {
        orders.forEach((order) -> {
            order.setOrderState(String.valueOf(status.getStatus()));
        });
        investOrderMapper.setOrdersState(orders);
    }

    /**
     * 设置 单个订单状态
     *
     * @param order
     */
    @Override
    public void updateOrderState(InvestOrder order, InvestStatus status) {
        order.setOrderState(String.valueOf(status.getStatus()));
        investOrderMapper.updateOrder(order);
    }

    /**
     * 获取随机
     *
     * @param orders
     * @param required
     * @return
     */
    private List<InvestOrder> getRandomOrders(List<InvestOrder> orders, long required) {
        List<InvestOrder> investOrdersSucess = new ArrayList<>();
        AtomicLong success = new AtomicLong();

        while (success.get() < required) {
            for (InvestOrder order : orders) {
                int rand = new Random().nextInt(9999);
                if (rand % 2 == 1 && !investOrdersSucess.contains(order)) {
                    investOrdersSucess.add(order);
                    success.getAndIncrement();
                    if (success.get() >= required) {
                        break;
                    }
                }
            }
        }

        return investOrdersSucess;
    }

    private List<InvestOrder> getTopMembersOrders(List<InvestOrder> orders, long top) {
        orders.sort(((o2, o1) -> {
            return relationTreeService.getMembersIncrement(
                    o2.getOrderPayerId(), investService.getEndTimeAt(o2.getOrderInvestId())
            ) - relationTreeService.getMembersIncrement(
                    o1.getOrderPayerId(), investService.getEndTimeAt(o1.getOrderInvestId())
            );
        }));

        return orders.subList(0, (int) top);
    }
}
