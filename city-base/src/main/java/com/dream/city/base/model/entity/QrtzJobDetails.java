package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class QrtzJobDetails implements Serializable {

    private String schedName;

    private String jobName;

    private String jobGroup;

    private String description;

    private String jobClassName;

    private String isDurable;

    private String isNonconcurrent;

    private String isUpdateData;

    private String requestsRecovery;

    private byte[] jobData;

}