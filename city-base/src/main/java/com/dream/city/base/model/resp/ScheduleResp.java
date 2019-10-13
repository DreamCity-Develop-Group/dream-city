package com.dream.city.base.model.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScheduleResp implements Serializable {

    /**
     * trigger
     */
    private String triggerName;
    private String triggerGroup;
    private String jobStatus;
    private String triggerType;
    private Integer priority;
    private Long prevFireTime;
    private Long nextFireTime;
    private Long startTime;
    private Long endTime;
    private String triggerDescr;

    /**
     * job
     */
    private String jobId;
    private String jobName;
    private String jobGroupName;
    private String jobClazz;
    private String jobDescr;

    /**
     * cron
     */
    private String jobTime;


}
