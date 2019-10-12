package com.dream.city.base.model.mapper;

import com.dream.city.base.model.entity.QrtzJobDetails;
import com.dream.city.base.model.req.ScheduleReq;
import com.dream.city.base.model.resp.ScheduleResp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QrtzJobDetailsMapper {

    Integer insert(ScheduleReq record);

    Integer update(ScheduleReq record);

    Integer delete(ScheduleReq record);

    QrtzJobDetails getOne(ScheduleReq record);

    List<QrtzJobDetails> getList(ScheduleReq record);


    ScheduleResp getJobByJobNameJobGroup(ScheduleReq record);

    List<ScheduleResp> getJobTriggerCronList(ScheduleReq record);

}