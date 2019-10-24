package com.dream.city.base.model.resp;

import lombok.Data;
import java.io.Serializable;
@Data
public class PlayerBiResp implements Serializable {

    private Long accId;

    /** 账户玩家 */
    private String playerId;

    private String playerName;

    private String bi;


    public void setAccId(Long accId){
        this.accId = accId;
    }
    public void setPlayerId(String playerId){
        this.playerId = playerId;
    }
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }
    public void setBi(String bi){
        this.bi = bi;
    }


    public Long setAccId(){
        return  this.accId;
    }
    public String setPlayerId(){
        return this.playerId;
    }
    public String setPlayerName(){
        return this.playerName;
    }
    public String setBi(){
        return this.bi;
    }

}
