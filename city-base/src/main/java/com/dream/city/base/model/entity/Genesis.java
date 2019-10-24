package com.dream.city.base.model.entity;


import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;


/**
 * @author Administrator
 */
@Data
public class Genesis {

    private String playerId;
    private String playerName;
    private String accAddr;
    private BigDecimal accMt;
    private Long mtQuantum;
    private String superior;
    private Date createTime;


    public void setCreateTime(Date createTime){ this.createTime = createTime;}

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setAccAddr(String accAddr) {
        this.accAddr = accAddr;
    }

    public void setAccMt(BigDecimal accMt) {
        this.accMt = accMt;
    }

    public void setMtQuantum(Long mtQuantum) {
        this.mtQuantum = mtQuantum;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }


    public String getPlayerId() {
        return playerId;
    }

    public Date getCreateTime() { return createTime; }

    public String getPlayerName() {
        return playerName;
    }

    public String getAccAddr() {
        return accAddr;
    }

    public BigDecimal getAccMt() {
        return accMt;
    }

    public Long getMtQuantum() {
        return mtQuantum;
    }

    public String getSuperior() { return superior; }
}
