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
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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
    RedissonClient redissonClient;
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
    @Autowired
    ConsumerDictionaryService dictionaryService;

    final String MAIN_HASH_DATA = "MAIN_HASH_DATA_";


    @LcnTransaction
    @Transactional
    @Override
    public Message enterMainPage(@RequestBody Message message) throws BusinessException {
        Object dataMsg = message.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);


        String username = jsonObject.getString("username");
        String playerId = jsonObject.getString("playerId");
        Map<String, Object> data = new Hashtable<>();

        Player player = playerService.getPlayerByPlayerId(playerId);
        if (redisUtils.hasKey(MAIN_HASH_DATA+playerId)){
            Map rdata = redisUtils.hmget(MAIN_HASH_DATA+playerId);
            message.getData().setData(rdata);
            message.setDesc("主页数据汇总");
            message.setCode(ReturnStatus.SUCCESS.getStatus());
        }


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
        //用户商会级别
        data.put("level", level);
        //data.put("invest",invests);
        //商会折扣
        data.put("rate", rate);
        //修改密码扣除mt额度
        String taxStr = dictionaryService.getValByKey("player.change.tran.pwd.tax");
        Integer tax = Integer.parseInt(taxStr);
        data.put("tradePassResetFee",tax);

        redisUtils.hmset(MAIN_HASH_DATA+playerId,data);

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

    @Override
    public Message version(Message message){
        log.info("版本获取或设置");

        String json = JsonUtil.parseObjToJson(message.getData().getData());
        JSONObject jsonObject = JsonUtil.parseJsonToObj(json,JSONObject.class);
        String version = jsonObject.getString("version");

        RLock lock = redissonClient.getLock("version");

        if (lock.tryLock()) {
            lock.lock();
            log.info("取得锁成功！");
            if (StringUtils.isBlank(version)) {
                message.setDesc("获取版本成功");
                message.getData().setData(getAppVersion());
            } else {
                setAppVersion(version);
                lock.unlock();
                log.info("释放锁成功！");
                message.setDesc("设置版本成功");
                message.getData().setData("Success:" + getAppVersion());
            }
            return message;
        }
        message.setDesc("设置或获取版本不成功");
        message.getData().setData(null);
        return message;
    }

}
