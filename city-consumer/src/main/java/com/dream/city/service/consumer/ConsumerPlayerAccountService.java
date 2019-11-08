package com.dream.city.service.consumer;

import com.dream.city.base.model.Result;
import com.dream.city.base.model.entity.PlayerAccount;
import com.dream.city.service.impl.FallBackCityUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Wvv
 */
@FeignClient(value = "city-tree", fallback = FallBackCityUser.class)
public interface ConsumerPlayerAccountService {

	/**
	 * 用户账户添加
	 */
	@RequestMapping("/tree/account/create")
	Result createAccount(@RequestParam("playerId") String playerId,@RequestParam("address")String address);


    /*@RequestMapping("/tree/get/{username}")
    String createAccount(@PathVariable("username") String username);*/

	/**
	 * 获取玩家账户
	 * @param playerId
	 * @return
	 */
	@RequestMapping("/tree/get/account")
	PlayerAccount getPlayerAccount(@RequestParam("playerId")String playerId);

    @RequestMapping("/tree/create/account")
    Result createBlockChainAccount(String username);
}
