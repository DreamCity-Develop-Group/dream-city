package com.dream.city.controller;

import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.Notice;
import com.dream.city.base.model.entity.Player;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.base.model.entity.RelationTree;
import com.dream.city.base.utils.JsonUtil;
import com.dream.city.base.utils.RedisUtils;
import com.dream.city.service.ConsumerGameSettingService;
import com.dream.city.service.ConsumerPlayerAccountService;
import com.dream.city.service.ConsumerPlayerService;
import com.dream.city.service.ConsumerTreeService;
import com.dream.city.service.impl.ConsumerNoticeServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Api(value = "API-Consumer Main Default Page",description = "主页数据接口")
@RestController
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



    @ApiOperation(value = "主页数据接口Player",notes = "此接口描述xxxxxxxxxxxxx<br/>xxxxxxx<br>值得庆幸的是这儿支持html标签<hr/>", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "player",value = "玩家",
                    required = true,dataType = "Player",
                    paramType = "body")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @RequestMapping(value = "/default",method = RequestMethod.POST)
    public Message enterMainPage(@RequestBody Message message){
        Object userData = message.getData().getData();
        Player player;
        Message ret = new Message();
        ret.setSource(message.getTarget());
        ret.setTarget(message.getSource());
        ret.setCreatetime(String.valueOf(System.currentTimeMillis()));
        MessageData msgData =  new MessageData(message.getData().getType(),message.getData().getModel());

        Map user = JsonUtil.parseJsonToObj(JsonUtil.parseObjToJson(userData), Map.class);
        String name =  user.get("username").toString();
        Result result = playerService.getPlayerByAccount(name);
        String json = JsonUtil.parseObjToJson(result.getData());
        player = JsonUtil.parseJsonToObj(json,Player.class);
        //个人信息
        Map<String,Object> profile = new HashMap<>();
        profile.put("nick",player.getPlayerNick());
        profile.put("level",player.getPlayerLevel());

        //取出公告缓存
        //公告
        List<Notice> notices = new ArrayList<>();
        notices = noticeService.getCacheNotices();

        //账户
        PlayerAccount playerAccount = playerAccountService.getPlayerAccount(player.getPlayerId());

        Map<String,Object> account = new HashMap<>();
        account.put("usdt",playerAccount.getAccUsdt());
        account.put("mt",playerAccount.getAccMt());

        Map<String,Object> data = new Hashtable<>();

        //消息数量 显示为小红点
        Integer messages = 0;

        //是否已经加入商会
        boolean commerce = treeService.getTree(player.getPlayerId()).getData() == null;

        //公告
        data.put("notices",notices);
        //我的信息Player
        data.put("profile",profile);
        //我的资产
        data.put("account",account);
        //我的通知信息messages
        data.put("messages",messages);
        //商会准入
        data.put("commerce",commerce);

        msgData.setData(data);
        ret.setData(msgData);
        return ret;
    }

}
