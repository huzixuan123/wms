package com._520it.wms.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

/**
 *	入库单据
 */
@Setter
@Getter
public class StockIncomeBill extends BaseAudit{

	private String sn; //订单编号
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date vdate; //入库日期
	private BigDecimal totalAmount;//总金额
	private BigDecimal totalNumber;//总数量
	private Depot depot;//仓库
	
	private List<StockIncomeBillItem> items = new ArrayList<>(); //一个单据对应多条明细
	

}
