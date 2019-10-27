package com.dream.city.service.consumer;

import com.dream.city.base.model.Result;
import com.dream.city.service.consumer.impl.FallBackCityUser;
import com.dream.city.service.consumer.impl.SalesServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Wvv
 */
@FeignClient(value = "city-tree", fallback = SalesServiceImpl.class)
//@RequestMapping("/sales")
public interface ConsumerSalesService {

    @RequestMapping("/sales/get/sales/num")
    Result getSalesNum(@RequestParam("playerId")String playerId);

    @RequestMapping("/sales/get/sales/overtime")
    Result getSalesNumOverTime(@RequestParam("playerId")String playerId);

    @RequestMapping("/sales/get/sales/rate")
    Result getUsdtToMtRate(@RequestParam("playerId")String playerId);
}
