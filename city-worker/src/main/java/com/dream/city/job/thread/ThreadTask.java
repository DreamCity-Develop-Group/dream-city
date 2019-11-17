package com.dream.city.job.thread;

import java.util.List;

import com.dream.city.base.model.entity.CityInvest;
import com.dream.city.service.InvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ThreadTask {

	@Autowired
	private InvestService investService;;
	/**
	 * 
	 */
	@Async("taskExecutor")
	public void profitGrant(CityInvest cityInvest){
		investService.profitGrant(cityInvest);
	}
	
}
