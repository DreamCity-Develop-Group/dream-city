package com.dream.city.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.*;
import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.*;
import com.dream.city.service.impl.ConsumerNoticeServiceImpl;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;

@Api(value = "API-Consumer Main Default Page", tags = "主页数据接口")
@RestController
@Slf4j
@RequestMapping("/consumer/main")
public class DefaultController {

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
    ConsumerNoticeServiceImpl noticeService;
    @Autowired
    PusherService pusherService;
    @Autowired
    ConsumerPropertyService propertyService;
    @Autowired
    ConsumerSalesService salesService;
    @Autowired
    ConsumerPlayerBlockChainService playerBlockChainService;
    



    @ApiOperation(value = "主页数据接口Player", httpMethod = "POST", notes = "此接口描述xxxxxxxxxxxxx<br/>xxxxxxx<br>值得庆幸的是这儿支持html标签<hr/>", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "player", value = "玩家",
                    required = true, dataType = "Player",
                    paramType = "body")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @RequestMapping(value = "/default", method = RequestMethod.POST)
    public Message enterMainPage(@RequestBody Message message) {
        Object dataMsg = message.getData().getData();
        JSONObject jsonObject = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(dataMsg), JSONObject.class);


        String username = jsonObject.getString("username");
        String playerId = jsonObject.getString("playerId");

        Player player = playerService.getPlayerByPlayerId(playerId);


        RelationTree tree = treeService.getRelationTree(player.getPlayerId());
        int level = 0;
        //是否已经加入商会
        int commerce = 0;
        if (tree != null){
            level = tree.getTreeLevel()==null?0:tree.getTreeLevel();
            Result allowed = treeService.getInvestAllowed(player.getPlayerId());

            commerce = 1;
            if (allowed.getSuccess()) {
                log.info("已经获得投资许可，设置交易密码");
                commerce = 2;
            }
        }
        //个人信息
        Map<String, Object> profile = new HashMap<>();
        profile.put("nick", player.getPlayerNick());
        if(tree != null){
            profile.put("level", tree.getTreeLevel() == null ? 0 : tree.getTreeLevel());
        }else{
            profile.put("level", 0 );
        }

        //取出公告缓存
        //公告
        List<Notice> notices = new ArrayList<>();
        notices = noticeService.getCacheNotices();

        //账户
        PlayerAccount playerAccount = playerAccountService.getPlayerAccount(player.getPlayerId());

        Map<String, Object> account = new HashMap<>();
        if (StringUtils.isBlank(player.getPlayerTradePass())){
            account.put("isHasTradePassword",Boolean.FALSE);
        }else {
            account.put("isHasTradePassword",Boolean.TRUE);
        }
        if(playerAccount != null) {
            account.put("usdt", playerAccount.getAccUsdtAvailable());
            account.put("mt", playerAccount.getAccMtAvailable());
            account.put("address", playerAccount.getAccAddr());
        }else{
            account.put("usdt", new BigDecimal(0));
            account.put("mt", new BigDecimal(0));
            account.put("address", "");
            Result accRet = playerBlockChainService.createBlockChainAccount(username);
            playerAccountService.createAccount(playerId,accRet.getData().toString());
        }
        //InvestOrder investOrder = propertyService.getInvests(player.getPlayerId());
        Result result1 = salesService.getUsdtToMtRate(player.getPlayerId());
        BigDecimal rate = new BigDecimal(result1.getData().toString()).setScale(3);

        Map<String, Object> data = new Hashtable<>();

        //消息数量 显示为小红点
        boolean messages = true;
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
        data.put("rate",rate);


        message.getData().setData(data);
        message.setDesc("主页数据汇总");
        message.setCode(ReturnStatus.SUCCESS.getStatus());

        //弹出收到兑换请求提示窗口
        pusherService.receive(player,1);
        //弹出收到兑换请求错过提示窗口
        pusherService.receive(player,2);
        return message;
    }

}
