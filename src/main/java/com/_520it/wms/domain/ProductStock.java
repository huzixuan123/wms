package com._520it.wms.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 *	货品库存
 */
@Setter
@Getter
public class ProductStock extends BaseDomain{
	private BigDecimal price; //库存价格(进价)
	private BigDecimal storeNumber;//库存数量
	private BigDecimal amount; //库存总金额
	private Product product;//货品
	private Depot depot;//仓库
}
