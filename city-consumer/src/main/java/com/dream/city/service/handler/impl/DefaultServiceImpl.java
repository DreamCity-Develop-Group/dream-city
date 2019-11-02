package com.dream.city.service.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Notice;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.consumer.*;
import com.dream.city.service.handler.DefaultService;
import com.dream.city.service.handler.NoticeService;
import com.dream.city.service.handler.PusherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
@Transactional
public class DefaultServiceImpl implements DefaultService {
    @Autowired
    ConsumerPlayerService playerService;
    @Autowired
    ConsumerPlayerAccountService playerAccountService;
    @Autowired
    ConsumerGameSettingService gameSettingService;
    @Autowired
    ConsumerTreeService treeService;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    NoticeService noticeService;
    @Autowired
    PusherService pusherService;
    @Autowired
    ConsumerPropertyService propertyService;
    @Autowired
    ConsumerSalesService salesService;
    @Autowired
    ConsumerPlayerBlockChainService playerBlockChainService;
    @Autowired
    ConsumerMessageService messageService;


    @LcnTransaction
    @Transactional
    @Override
    public Message enterMainPage(@RequestBody Message message) throws BusinessException {
        Object dataMsg = message.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);


        String username = jsonObject.getString("username");
        String playerId = jsonObject.getString("playerId");

        Player player = playerService.getPlayerByPlayerId(playerId);


        RelationTree tree = treeService.getRelationTree(player.getPlayerId());
        //个人信息
        Map<String, Object> profile = new HashMap<>();

        int level = 0;
        //是否已经加入商会
        int commerce = 0;
        if (tree != null) {
            level = tree.getTreeLevel() == null ? 0 : tree.getTreeLevel();
            Result allowed = treeService.getInvestAllowed(player.getPlayerId());

            commerce = 1;
            if (allowed.getSuccess()) {
                log.info("已经获得投资许可，设置交易密码");
                commerce = 2;
            }
        }
        //昵称
        profile.put("nick", player.getPlayerNick());
        //邀请码
        profile.put("invite",player.getPlayerInvite());
        //isAutoSend 是否设置自动发货
        profile.put("isAutoSend",false);
        if (tree != null) {
            profile.put("level", tree.getTreeLevel() == null ? 0 : tree.getTreeLevel());
            if("1".equals(tree.getSendAuto())){
                profile.put("isAutoSend",true);
            }
        } else {
            profile.put("level", 0);
        }

        //取出公告缓存
        //公告
        List<Notice> notices = new ArrayList<>();
        notices = noticeService.getCacheNotices();

        //账户
        PlayerAccount playerAccount = playerAccountService.getPlayerAccount(player.getPlayerId());

        Map<String, Object> account = new HashMap<>();
        if (StringUtils.isBlank(player.getPlayerTradePass())) {
            account.put("isHasTradePassword", Boolean.FALSE);
        } else {
            account.put("isHasTradePassword", Boolean.TRUE);
        }
        if (playerAccount != null) {
            account.put("usdt", playerAccount.getAccUsdtAvailable());
            account.put("mt", playerAccount.getAccMtAvailable());
            account.put("address", playerAccount.getAccAddr());
        } else {
            account.put("usdt", new BigDecimal(0));
            account.put("mt", new BigDecimal(0));
            account.put("address", "");
            Result accRet = playerBlockChainService.createBlockChainAccount(username);
            playerAccountService.createAccount(playerId, accRet.getData().toString());
        }
        //InvestOrder investOrder = propertyService.getInvests(player.getPlayerId());
        Result result1 = salesService.getUsdtToMtRate(player.getPlayerId());
        BigDecimal rate = new BigDecimal(result1.getData().toString()).setScale(3);

        Map<String, Object> data = new Hashtable<>();

        //消息数量 显示为小红点
        Result<Integer> integerResult = messageService.getUnReadCount(playerId);
        boolean messages = false;
        if (integerResult != null) {
            int count = integerResult.getData();
            messages = count > 0 ? true : false;
        }
        //公告
        data.put("notices", notices);
        //我的信息Player
        data.put("profile", profile);
        //我的资产
        data.put("account", account);
        //我的通知信息messages
        data.put("messages", messages);
        //商会准入
        data.put("commerce", commerce);
        data.put("level", level);
        //data.put("invest",invests);
        data.put("rate", rate);


        message.getData().setData(data);
        message.setDesc("主页数据汇总");
        message.setCode(ReturnStatus.SUCCESS.getStatus());

        //弹出收到兑换请求提示窗口
        pusherService.receive(player, 1);
        //弹出收到兑换请求错过提示窗口
        pusherService.receive(player, 2);
        return message;
    }

    @Override
    public void setAppVersion(String version) {
        redisUtils.set("APP_VERSION",version);
    }

    @Override
    public String getAppVersion() {
        return redisUtils.getStr("APP_VERSION");
    }

}
