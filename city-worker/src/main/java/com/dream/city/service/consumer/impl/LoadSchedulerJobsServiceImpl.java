package com.dream.city.service.consumer.impl;

import com.dream.city.service.LoadSchedulerJobsService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 *
 * @author Wvv
 */
@Component
@Order(value = 1)
public class LoadSchedulerJobsServiceImpl implements LoadSchedulerJobsService, ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
