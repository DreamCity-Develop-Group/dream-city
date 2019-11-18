
package com.dream.city.base.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
//@TableName("ac_withdraw_task_record")
@Data
public class WithdrawTaskRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@TableId(value="id",type=IdType.AUTO)
	private Long id; 					// 自增ID
	private String taskId; 				// 提现任务ID:"BTC-20160101-1"
	private String chainId; 			// 主链ID
	private String assetId; 			// 币种ID
	private String coinType; 			// 币种名称
	private String userId; 				//提现用户ID
	private String userName; 			//提现用户ID
	private String toWalletAddress; 	//提现到账地址
	private BigDecimal amount; 			// 提现数量
	private BigDecimal transferCharge; 	// 提现数量
	private String memo; 				// 默认备注用户ID
	private String tbStatus; 			// 4：待审核；0：待处理；1：失败；2：成功
	private String auditTime; 			// 审核完成时间
	private String checksum; 			// 校验码
	private String createTime; 			// 创建日期

	public WithdrawTaskRecord(){
		super();
	}

	public WithdrawTaskRecord(Long id,String taskId, String chainId, String assetId, String coinType,
							  String userId,String userName,String toWalletAddress,
							  BigDecimal amount,BigDecimal transferCharge, String memo,
							  String checksum,String createTime) {
		this.id = id;
		this.taskId = taskId;
		this.chainId = chainId;
		this.assetId = assetId;
		this.coinType = coinType;
		this.userId = userId;
		this.userName = userName;
		this.toWalletAddress = toWalletAddress;
		this.amount = amount;
		this.transferCharge = transferCharge;
		this.memo = memo;
		this.tbStatus = "0";
		this.auditTime = createTime;
		this.checksum = checksum;
		this.createTime = createTime;
	}
	
	
}

