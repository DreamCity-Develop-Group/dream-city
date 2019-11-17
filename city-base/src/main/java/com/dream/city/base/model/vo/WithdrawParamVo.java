package com.dream.city.base.model.vo;

import lombok.Data;

@Data
public class WithdrawParamVo {
	private String requestFrom;
	private long requestTime;
	private String chainId;
	private String assetId;
	private String status;
	private Long id;
	private Integer size;
}
