package com.dream.city.controller;

import com.dream.city.base.model.entity.User;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxn on 2016/2/24.
 */
@Api(value = "API - Swagger", description = "Swagger示例")
@RestController
@RequestMapping("/consumer")
public class SwaggerController {
    private static Logger logger = LoggerFactory.getLogger(SwaggerController.class);


    @ApiOperation(value = "查询车辆接口", httpMethod = "POST", notes = "此接口描述xxxxxxxxxxxxx<br/>xxxxxxx<br>值得庆幸的是这儿支持html标签<hr/>", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vno", value = "车牌", required = false,
                    dataType = "string", paramType = "query", defaultValue = "辽A12345"),
            @ApiImplicitParam(name = "page", value = "page", required = false,
                    dataType = "Integer", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "count", value = "count", required = false,
                    dataType = "Integer", paramType = "query", defaultValue = "10")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )


    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map findVehicles(@RequestParam(value = "vno", required = false) String vno,
                            @RequestParam(value = "page", required = false) Integer page,
                            @RequestParam(value = "count", required = false) Integer count)
            throws Exception {

        logger.info("http://localhost:8501/api/v1/vehicles");
        logger.info("## {},{},{}", vno, page, count);
        logger.info("## 请求时间：{}", new Date());

        Map map = new HashMap();
        map.put("vno", vno);
        map.put("page", page);
        return map;
    }


    @ApiOperation(value = "根据车牌查询车辆", httpMethod = "POST", notes = "这种类型的查询是精确查询,其结果只有一条数据", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vno", value = "车牌", required = false,
                    dataType = "string", paramType = "path", defaultValue = "辽A12345")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @RequestMapping(value = "/vno={vno}", method = RequestMethod.GET)
    public Map getVno(@PathVariable(value = "vno") String vno)
            throws Exception {

        logger.info("http://localhost:8501/api/v1/vehicles/vno={}", vno);
        logger.info("## 请求时间：{}", new Date());

        Map map = new HashMap();
        map.put("vno", vno);

        return map;
    }

    @ApiOperation(value = "车辆位置查询接口", httpMethod = "POST", notes = "根据车牌查询车辆位置信息", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vno", value = "车牌", required = false,
                    dataType = "string", paramType = "path", defaultValue = "辽A12345")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @RequestMapping(value = "vno={vno}/location", method = RequestMethod.GET)
    public Map getLocation(@PathVariable(value = "vno") String vno)
            throws Exception {
        logger.info("getLocation");
        logger.info("## 请求时间：{}", new Date());

        Map map = new HashMap();
        map.put("vno", vno);

        return map;
    }


    @ApiOperation(value = "根据车辆id查询", httpMethod = "POST", notes = "精确查询,最常规的方式,支持POST和GET方式", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = false,
                    dataType = "string", paramType = "path", defaultValue = "12344444")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @RequestMapping(value = "{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> getById(@PathVariable(value = "id") String id)
            throws Exception {

        logger.info("http://localhost:8501/api/v1/vehicles/{}", id);
        logger.info("## 请求时间：{}", new Date());

        Map map = new HashMap();
        map.put("{RequestMethod.GET,RequestMethod.POST}", id);

        return map;
    }

    @ApiOperation(value = "根据车辆id查询", httpMethod = "POST", notes = "精确查询,最常规的方式,支持POST和GET方式", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = false,
                    dataType = "string", paramType = "path", defaultValue = "12344444")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @RequestMapping(value = "{id}", method = {RequestMethod.DELETE})
    public Map delById(@PathVariable(value = "id") String id)
            throws Exception {

        logger.info("http://localhost:8501/api/v1/vehicles/{}", id);
        logger.info("## 请求时间：{}", new Date());

        Map map = new HashMap();
        map.put("RequestMethod.DELETE", id);
        return map;
    }


    @ApiOperation(value = "网点挂靠", httpMethod = "POST", notes = "嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @RequestMapping(value = "change_rentalshop", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Map changeRentalShop(@RequestBody User parameter)
            throws Exception {

        logger.info("http://localhost:8501/api/v1/vehicles/change_rentalshop | {}", parameter);
        logger.info("## 请求时间：{}", new Date());
        Map map = new HashMap();
        map.put("网点挂靠", new Date());

        return map;
    }
}
