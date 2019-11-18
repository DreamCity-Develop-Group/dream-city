package com.dream.city.base.model.vo;

import lombok.Data;

@Data
public class WithdrawFeedbackParamVo {
	private String request_from;
	private String task_id;
	private String status;
	private String confirm;
	private String block_height;
	private String confirm_time;
	private String tx_id;
	private String checksum;
}
