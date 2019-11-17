package com.dream.city.base.model.vo;

import java.math.BigDecimal;

import lombok.Data;


@Data
public class DepositParamVo {
	private String requestFrom;
	private long requestTime;
	private String chainId;
	private String symbol;//充值的币种名称
	private String assetId;
	private String depositId;//充值订单唯一标示
	private String txId;
	private String fromWalletAddress;
	private String toWalletAddress;
	private BigDecimal amount;
	private String memo;
	private String confirm;
	private String status;
	private String blockHeight;
	private long confirmTime;
	private String checksum;
}
