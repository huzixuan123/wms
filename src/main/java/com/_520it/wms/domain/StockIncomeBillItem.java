package com._520it.wms.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 *	入库单据明细
 */
@Setter
@Getter
public class StockIncomeBillItem extends BaseDomain{
	private BigDecimal costPrice; //商品进价
	private BigDecimal number; //入库数量
	private BigDecimal amount;//入库总价
	private String remark;//备注
	private Product product;//商品
	private Long billId;//关联单据
}
