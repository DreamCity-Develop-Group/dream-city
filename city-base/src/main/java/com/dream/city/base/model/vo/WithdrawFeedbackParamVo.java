package com.dream.city.base.model.vo;

import lombok.Data;

/**
 * @author Wvv
 *
 * 提现回调接口数据
 */
@Data
public class WithdrawFeedbackParamVo {
	private String request_from;	//来源 ETH_WALLET
	private String task_id;			//提现任务/记录 ID：根据此ID更新状态
	private String status;  		//complate :1  fail: 2
	private String confirm;			//确认数 默认2个
	private String block_height;	//区块高度
	private String confirm_time;	//确认时间
	private String tx_id;			//交易HASH：例子: 到账地址-哈希值（会重复）
	private String checksum;		//验签，默认不用
}
