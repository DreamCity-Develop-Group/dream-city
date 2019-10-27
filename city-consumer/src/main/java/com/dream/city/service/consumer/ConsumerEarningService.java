package com.dream.city.service.consumer;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerEarning;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 提现规则设置
 */
@FeignClient(value = "city-trade")
@RequestMapping("/earning")
public interface ConsumerEarningService {

    /**
     * 删除提现规则
     * @param earnId
     * @return
     */
    @RequestMapping("/deleteEarningById")
    Result deleteEarningById(@RequestParam Integer earnId);

    /**
     * 新增提现规则
     * @param record
     * @return
     */
    @RequestMapping("/insertEarning")
    Result insertEarning(@RequestBody PlayerEarning record);

    /**
     * 查询提现规则
     * @param earnId
     * @return
     */
    @RequestMapping("/getEarning")
    Result getEarning(@RequestParam Integer earnId);

    /**
     * 查询提现规则列表
     * @param record
     * @return
     */
    @RequestMapping("/getEarningList")
    Result getEarningList(@RequestBody PlayerEarning record);

    /**
     * 更新提现规则
     * @param record
     * @return
     */
    @RequestMapping("/updateEarningById")
    Result updateEarningById(@RequestBody PlayerEarning record);

    /**
     * 投资提取
     *
     * @param playerId
     * @return
     */
    @RequestMapping("/extract")
    Result<Map<String,Object>> extract(@RequestParam("playerId")String playerId, @RequestParam("inType")Integer inType);

}
