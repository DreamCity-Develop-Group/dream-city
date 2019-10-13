package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class QrtzCronTriggers implements Serializable {

    private String schedName;

    private String triggerName;

    private String triggerGroup;

    private String cronExpression;

    private String timeZoneId;
}