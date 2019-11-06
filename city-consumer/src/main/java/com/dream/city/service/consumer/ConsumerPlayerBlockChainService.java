package com.dream.city.service.consumer;

import com.dream.city.base.model.Result;
import com.dream.city.service.impl.FallBackCityUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Wvv
 */
@FeignClient(value = "city-wallet", fallback = FallBackCityUser.class)
public interface ConsumerPlayerBlockChainService {

    /**
     * 用户区块账户添加
     */
    @RequestMapping("/chain/create/account")
    Result createBlockChainAccount(@RequestParam("username") String username);
}
