package com.dream.city.base.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Wvv
 * 充值接口数据
 */
@Data
public class DepositParamVo {
	private String request_from;  	//来源 ETH_WALLET
	private long request_time;	 	//请求时间：时间磋
	private String chain_id;		//主键ID ETH:60
	private String symbol;		 	//充值的币种名称
	private String asset_id;		//合约地址
	private String deposit_id;		//充值订单唯一标示
	private String tx_id;			//交易HASH
	private String to;				//到账地址
	private String from;			//默认是空 “”
	private BigDecimal amount;		//充值额度
	private String memo;			//备注，默认是用户ID
	private String confirm;			//确认数 默认2个
	private String status;			// success：2 coming: 1 fail: 0
	private String block_height;	//区块高度
	private long confirm_time;		//确认时间
	private String checksum;		//验签，默认不用
	
	/*public static void main(String args[]){
		DepositParamVo p = new DepositParamVo();
		p.setAmount(BigDecimal.valueOf(100));
		p.setRequest_from("usdt_wallet");
		p.setChain_id("0");
		p.setAsset_id("31");
		p.setSymbol("USDT");
		p.setDeposit_id(""+System.currentTimeMillis());
		p.setRequest_time(System.currentTimeMillis());
		p.setTx_id("txId_"+System.currentTimeMillis());
		p.setTo("1MbCoXtE7LvEzDpPwFn1b2xhEgeQcezsCB");
		p.setMemo("");
		p.setConfirm("1");
		p.setStatus("2");
		p.setBlock_height("574000");
		
		System.out.println(JSONHelper.toJson(p));
	}*/
}


