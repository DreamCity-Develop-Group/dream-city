package com.dream.city.base.model.vo;

import lombok.Data;

/**
 * @author Wvv
 * 提现任务获取参数类
 */
@Data
public class WithdrawParamVo {
	private String request_from;		//来源 ETH_WALLET
	private long request_time;			//请求时间：时间磋
	private String chain_id;			//主键ID ETH:60
	private String asset_id;			//合约地址
	private String status;				//默认取待提现的数据
	private Integer id;					//默认是0，也可以不传
	private Integer size;				//每次查询数据量
}
