package com.dream.city.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServerMessage {
    private String from;
    private String message;
    private String topic;
    private Date time = new Date();
}
