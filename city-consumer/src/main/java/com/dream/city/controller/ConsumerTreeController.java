package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.service.handler.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wvv
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerTreeController {
    @Autowired
    TreeService treeService;


    /**
     * 添加玩家商会关系
     *
     * @param msg
     * @return
     */
    @RequestMapping("/tree/add")
    public Message treeAdd(@RequestBody Message msg) {
        try {
            return treeService.treeAdd(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }

    /**
     * 添加经营许可
     *
     * @param msg
     * @return
     */
    @RequestMapping("/tree/join")
    public Message Join(@RequestBody Message msg) {
        try {
            return treeService.Join(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }

    /**
     * 获取商会成员
     *
     * @param msg
     * @return
     */
    @RequestMapping("/tree/getMembers")
    public Message getMembers(@RequestBody Message msg) {
        try {
            return treeService.getMembers(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }

    /**
     * 获取订单
     *
     * @param msg
     * @return
     */
    @RequestMapping("/tree/getSalesOrder")
    public Message getSalesOrder(@RequestBody Message msg) {
        try {
            return treeService.getSalesOrder(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }

    /**
     * 设置自动发货，并备货
     *
     * @param msg
     * @return
     */
    @RequestMapping("/tree/setAutoSend")
    public Message setAutoSend(@RequestBody Message msg) {
        try {
            return treeService.setAutoSend(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }

    }

    /**
     * 订单发货
     *
     * @param msg
     * @return
     */
    @RequestMapping(value = "/tree/sendOrder", method = RequestMethod.POST)
    public Message sendOrder(@RequestBody Message msg) {
        try {
            return treeService.sendOrder(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }

    /**
     * 拒绝订单发货
     *
     * @param msg
     * @return
     */
    @RequestMapping(value = "/tree/refuseOrder", method = RequestMethod.POST)
    public Message refuseSendOrder(@RequestBody Message msg) {
        try {
            return treeService.refuseSendOrder(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }


    /**
     * 订单创建
     *
     * @param msg
     * @return
     */
    @RequestMapping("/tree/createOrder")
    public Message createOrder(@RequestBody Message msg) {
        try {
            return treeService.createOrder(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            return msg;
        }
    }


    /**
     * 验证订单支付密码
     *
     * @param msg
     * @return
     */
    @RequestMapping("/tree/check/orderPass")
    public Message checkOrderPass(@RequestBody Message msg) {
        try {
            return treeService.checkOrderPass(msg);
        }catch (Exception e){
            //throw new BusinessException("setplayertree");
            msg.getData().setCode(ReturnStatus.FAILED.getStatus());
            msg.setDesc(e.getMessage());
            return msg;
        }
    }


}
