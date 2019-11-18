package com.dream.city.base.model.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Wvv
 */
@Data
public class NoticeResp implements Serializable {

    private Integer noticeId;

    private String title;

    private String noticeContent;

    private Integer noticeState;

    private String sendTime;

    private String createTime;

}
