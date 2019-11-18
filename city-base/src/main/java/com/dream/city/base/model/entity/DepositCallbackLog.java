package com.dream.city.base.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
public class DepositCallbackLog implements Serializable {
	private Integer id;
	private String depositId;//充值订单唯一标示
	private String requestFrom;
	private String chainId;
	private String symbol;//充值的币种名称
	private String assetId;
	private String txId;
	private String fromAddress;
	private String toAddress;
	private BigDecimal amount;
	private String confirm;
	private String status;
	private String blockHeight;
	private String checksum;
	private long confirmTime;
	private long requestTime;
	private String DepDesc;
	private Date createTime;

}
