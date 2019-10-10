package com.dream.city.base.model.enu;

/**
 * 审核状态(待审核wait，审核中verifying，pass审核通过，notpass审核不通过)
 */
public enum VerifyStatus {

    WAIT("WAIT","待审核"),
    VERIFYING("VERIFYING","审核中"),
    PASS("PASS","审核通过"),
    NOTPASS("NOTPASS","审核不通过");

    private String code;
    private String desc;

    VerifyStatus(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


}
