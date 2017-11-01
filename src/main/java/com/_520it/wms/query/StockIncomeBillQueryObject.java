package com._520it.wms.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockIncomeBillQueryObject extends BaseOrderQueryObject {
	private Long depotId=-1L;//仓库
}
