package com._520it.wms.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 采购订单
 */
@Setter
@Getter
@ToString
public class OrderBill extends BaseDomain{

	public static final int STATE_NOMAL = 0; //待审核
	public static final int STATE_AUDIT = 1; //已审核
	private String sn; //订单编号
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date vdate; //采购日期
	private int status = STATE_NOMAL; //审核状态
	private BigDecimal totalAmount; //总计金额
	private BigDecimal totalNumber; //总计数量
	private Date auditTime; //审核日期
	private Date inputTime; //录入日期
	private Employee inputUser; //录入人(需要录入人的姓名,必须有)
	private Employee auditor; //审核人(需要审核人的姓名,可以没有)
	private Supplier supplier; //供应商 (需要供应商的姓名)

	private List<OrderBillItem> items = new ArrayList<>(); //一个单据对应多条明细
	
	public String getDisplayStatus(){
		return this.status==STATE_NOMAL?"待审核":"已审核";
	}
}
