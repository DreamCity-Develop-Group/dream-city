package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ScheduleReq implements Serializable {

    private String jobId;
    private String jobName;
    private String jobGroupName;
    private Class jobClass;
    private String jobClazz;

    private String jobTime;

    private String jobStatus;
    private String triggerName;
    private String triggerGroup;

    private Boolean startNow;
    private Date startAt;
    private String jsonParameter;


    private String descr;
    private String jobDescription;
    private String triggerDescription;

    private String editType;



}
