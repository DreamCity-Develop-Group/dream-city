package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Wvv
 */
@Data
public class Notice  implements Serializable {
    private Integer noticeId;

    private String title;

    private String noticeContent;

    private Integer noticeState;

    private Timestamp createTime;

    private Timestamp sendTime;

}
