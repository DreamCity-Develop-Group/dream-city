package com.dream.city.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Wvv
 */
@Component
@Slf4j
public class WebSocketFilter extends ZuulFilter {

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

        Object accessToken = request.getParameter("accessToken");

        if (null == accessToken){
            log.warn("Acess token is Empty!");

            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);

            return null;
        }
        log.info("Access token is Ok! ");

        /*
        String upgradeHeader = request.getHeader("Upgrade");
        if (null == upgradeHeader){
            upgradeHeader = request.getHeader("upgrade");
        }
        if (null != upgradeHeader && "websocket".equalsIgnoreCase(upgradeHeader)){
            context.addZuulRequestHeader("connection","Upgrade");
        }
         */
        return null;
    }
}
