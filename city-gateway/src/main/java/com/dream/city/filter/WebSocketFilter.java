package com.dream.city.filter;

import com.dream.city.base.model.enu.ReturnStatus;
import com.dream.city.base.utils.RedisUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

import com.dream.city.base.utils.RedisKeys;

/**
 * @author Wvv
 */
@Component
@Slf4j
public class WebSocketFilter extends ZuulFilter {
    @Autowired
    RedisUtils redisUtils;


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext context = RequestContext.getCurrentContext();

        HttpServletRequest request = context.getRequest();


        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        String auth = request.getHeader("Authorization");
        String accessToken = auth==null?null:auth.substring(7);
        String accessMethod = request.getHeader("method");
        String authType = request.getAuthType();
        if (StringUtils.isEmpty(authType) ){
            HashSet<String> set = new HashSet<>();
            set.add("login");
            set.add("codeLogin");
            set.add("reg");
            set.add("getCode");
            set.add("jobPush");
            set.add("createWorker");
            set.add("pwforget");
            set.add("exit");
            if (set.contains(accessMethod)){
                log.info("Access method is Ok! ");
                return null;
            }
        }

        if (null == accessToken || StringUtils.isEmpty(accessToken)){
            log.warn("Access token is Empty!");

            context.setSendZuulResponse(false);
            context.setResponseStatusCode(ReturnStatus.GATEWAY_TOKEN_ERROR.getStatus());
            return null;
        }else{//RedisKeys.LOGIN_USER_TOKEN+username
            String username = request.getHeader("username");
            log.info("Username:"+username);
            String key = RedisKeys.LOGIN_USER_TOKEN+username;
            log.error("auth Key:"+key);
            String redisKey = RedisKeys.LOGIN_USER_TOKEN + username;
            String token =  redisUtils.get(redisKey).get();

            if (org.apache.commons.lang.StringUtils.isNotBlank(token) && token.equals(accessToken)){
                log.info("Access token is Ok! ");
                return null;
            }
            log.warn("Access token is Wrong!");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(ReturnStatus.GATEWAY_TOKEN_ERROR.getStatus());
            return null;
        }

        /*
        String upgradeHeader = request.getHeader("Upgrade");
        if (null == upgradeHeader){
            upgradeHeader = request.getHeader("upgrade");
        }
        if (null != upgradeHeader && "websocket".equalsIgnoreCase(upgradeHeader)){
            context.addZuulRequestHeader("connection","Upgrade");
        }

        return null;

         */
    }
}
