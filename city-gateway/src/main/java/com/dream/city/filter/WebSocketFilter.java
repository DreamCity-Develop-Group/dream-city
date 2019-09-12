package com.dream.city.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

/**
 * @author Wvv
 */
@Component
@Slf4j
public class WebSocketFilter extends ZuulFilter {
    @Autowired
    RedisTemplate redisTemplate;

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

        String accessToken = request.getHeader("Authorization").substring(7);
        String accessMethod = request.getHeader("method");
        String authType = request.getAuthType();
        if (StringUtils.isEmpty(authType) ){
            HashSet<String> set = new HashSet<>();
            set.add("login");set.add("reg");set.add("getValiCode");
            if (set.contains(accessMethod)){
                log.info("Access method is Ok! ");
                return null;
            }
        }

        if (null == accessToken || StringUtils.isEmpty(accessToken)){
            log.warn("Access token is Empty!");

            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            return null;
        }else{//"token_"+username
            String username = request.getHeader("username");
            Object token = redisTemplate.opsForValue().get("token_"+username);
            if (null != token && String.valueOf(token).equals(accessToken)){
                log.info("Access token is Ok! ");
                return null;
            }
            log.warn("Access token is Wrong!");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(500);
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
