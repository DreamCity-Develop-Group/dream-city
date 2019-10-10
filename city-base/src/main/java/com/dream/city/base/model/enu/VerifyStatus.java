package com.dream.city.base.model.enu;

/**
 * 审核状态(待审核wait，审核中verifying，pass审核通过，notpass审核不通过)
 */
public enum VerifyStatus {

    WAIT("待审核"),
    VERIFYING("审核中"),
    PASS("审核通过"),
    NOTPASS("审核不通过");

    private String desc;

    VerifyStatus(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }


}
