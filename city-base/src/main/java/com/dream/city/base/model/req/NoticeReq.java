package com.dream.city.base.model.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Wvv
 */
@Data
public class NoticeReq implements Serializable {

    private Integer noticeId;

    private String title;
    private String noticeContent;

    private Integer noticeState;

    private String sendTime;

    //查询几天内的
    private Integer dayParam;

}
