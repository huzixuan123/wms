package com._520it.wms.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *采购订单明细
 */
@Setter
@Getter
@ToString
public class OrderBillItem extends BaseDomain{
	private BigDecimal costPrice; //采购价格
	private BigDecimal number; //采购数量
	private BigDecimal amount; //金额小计
	private String remark; //备注
	private Product product; //采购的货品
	private Long billId; //关联采购订单(不需要根据明细查订单,一般根据订单查明细所有Id就够了)
}
