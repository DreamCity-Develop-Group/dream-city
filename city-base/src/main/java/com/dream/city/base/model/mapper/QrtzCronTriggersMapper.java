package com.dream.city.base.model.mapper;

import com.dream.city.base.model.entity.QrtzCronTriggers;
import com.dream.city.base.model.req.ScheduleReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QrtzCronTriggersMapper {

    Integer delete(ScheduleReq record);

    Integer insert(ScheduleReq record);

    Integer update(ScheduleReq record);

    QrtzCronTriggers getOne(ScheduleReq record);

    List<QrtzCronTriggers> getList(ScheduleReq record);


}