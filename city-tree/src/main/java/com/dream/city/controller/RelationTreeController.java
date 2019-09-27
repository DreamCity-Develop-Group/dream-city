package com.dream.city.controller;

import com.dream.city.base.model.CityGlobal;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.*;
import com.dream.city.service.PlayerAccountService;
import com.dream.city.service.RelationTreeService;
import com.dream.city.service.SalesOrderService;
import com.dream.city.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 *
 */
@RestController
@RequestMapping("/tree")
public class RelationTreeController {

    @Autowired
    private RelationTreeService relationTreeService;
    @Autowired
    private SalesOrderService salesOrderService;
    @Autowired
    private PlayerAccountService accountService;

    /**
     * 根据玩家ID查找关系
     *
     * @param playerId
     * @return
     */
    @RequestMapping("/get/tree")
    public Result get(@RequestParam("playerId") String playerId) {
        RelationTree tree = relationTreeService.getByPlayer(playerId);
        List<RelationTree> trees = relationTreeService.getTrees();

        return new Result(CityGlobal.Constant.TREE_RELATION_SUCCESS, 200, trees);
    }

    /**
     * 添加玩家关系
     *
     * @param parentId
     * @param playerId
     * @param invite
     * @return
     */
    @RequestMapping("/add/tree")
    public Result add(@RequestParam("parentId") String parentId, @RequestParam("playerId") String playerId, @RequestParam("invite") String invite) {
        Result result = relationTreeService.save(parentId, playerId, invite);

        return result;
    }

    /**
     * 从上级查找playerId以下n级
     *
     * @param level
     * @return
     */
    @RequestMapping("/find/Level")
    public Result findByLevel(@RequestParam("playerId") String playerId, @RequestParam("level") Integer level) {
        Map<Integer, List<RelationTree>> treeMap = new Hashtable<>();

        treeMap = relationTreeService.getLevelChildTreesMap(playerId,level);

        return new Result("success",200,treeMap);
    }

    /**
     *  查找商会关系的上级
     * @param playerId
     * @return
     */
    @RequestMapping("/find/parent")
    public Result findParent(@RequestParam("playerId")String playerId){
        return new Result("success",200,relationTreeService.getParent(playerId));
    }

    /**
     *获取作为卖家的订单数据
     *
     * @param playerId
     * @return
     */
    @RequestMapping("query/sales")
    public Result querySales(@RequestParam("playerId")String playerId){
        List<SalesOrder> orders = salesOrderService.selectSalesSellerOrder(playerId);
        return new Result(true,"获取订单成功",200,orders);
    }

    /**
     * 购买MT
     * @param playerId
     * @param amount
     * @return
     */
    @RequestMapping("buy/mt")
    public Result buyMt(@RequestParam("playerId")String playerId, @RequestParam("amount")BigDecimal amount){
        BigDecimal rate = salesOrderService.getUsdtToMtRate();
        Result ret = salesOrderService.buyMtCreate(amount,rate,playerId);
        return ret;
    }

    /**
     * 获取主页账户数据
     *
     * @param playerId
     * @return
     */
    @RequestMapping("/get/account")
    public PlayerAccount getPlayerAccount(@RequestParam("playerId")String playerId){

        PlayerAccount account = accountService.getPlayerAccount(playerId);

        return  account;
    }

    /**
     * 账户创建
     * @param playerId
     * @param address
     * @return
     */
    @RequestMapping("/account/create")
    public Result createAccount(@RequestParam("playerId")String playerId,@RequestParam("address")String address){
        accountService.createAccount(playerId,address);
        PlayerAccount account = accountService.getPlayerAccount(playerId);
        if (null != account ){
            return Result.result(true,"玩家账户开设成功",200,account);
        }

        return Result.result(false,"玩家账户开设失败",200,null);
    }


}
