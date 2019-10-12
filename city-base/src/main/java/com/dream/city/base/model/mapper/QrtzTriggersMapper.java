package com.dream.city.base.model.mapper;

import com.dream.city.base.model.entity.QrtzTriggers;
import com.dream.city.base.model.req.ScheduleReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QrtzTriggersMapper {

    Integer insert(ScheduleReq record);

    Integer update(ScheduleReq record);

    Integer delete(ScheduleReq record);


    QrtzTriggers getOne(ScheduleReq record);

    List<QrtzTriggers> getList(ScheduleReq record);

}