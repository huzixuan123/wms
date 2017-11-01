package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *	出库库单据
 */
@Setter
@Getter
public class StockOutcomeBill extends BaseAudit{
	private String sn; //订单编号
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date vdate; //出库日期
	private BigDecimal totalAmount;//总金额
	private BigDecimal totalNumber;//总数量
	private Depot depot;//仓库
	private Client client;//客户
	
	private List<StockOutcomeBillItem> items = new ArrayList<>(); //一个单据对应多条明细
}
