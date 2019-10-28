package com.dream.city.service.handler.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dream.city.base.exception.BusinessException;
import com.dream.city.base.model.Message;
import com.dream.city.base.model.MessageData;
import com.dream.city.base.model.Result;
import com.dream.city.service.consumer.ConsumerWorkerService;
import com.dream.city.service.handler.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wvv
 * @program: dream-city
 * @File: WorkerServiceImpl
 * @description: 后台工作服务类
 * @create: 2019/10/2019/10/27 23:36:30 [星期日]
 **/
@Slf4j
@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    ConsumerWorkerService workerService;

    @LcnTransaction
    @Transactional
    @Override
    public Message createWorker(Message message) throws BusinessException {
        Message ret;
        String task = message.getData().getType();
        Result result = workerService.createWorker(message);

        MessageData data = new MessageData(message.getData().getType(), message.getData().getModel(), result);
        ret = new Message(
                "city-worker",
                "city-comm",
                data,
                "创建任务[" + task + "]失败",
                String.valueOf(System.currentTimeMillis())
        );

        if (result.getSuccess()) {
            log.info("创建任务成功");
            ret = new Message(
                    "city-worker",
                    "city-comm",
                    data,
                    "创建任务[" + task + "]成功",
                    String.valueOf(System.currentTimeMillis())
            );
        }
        log.info("创建任务失败");


        return ret;
    }
}
