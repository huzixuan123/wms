package com._520it.wms.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QrderBillQueryObject extends BaseOrderQueryObject {
	private Long supplierId=-1L;//供应商
}
