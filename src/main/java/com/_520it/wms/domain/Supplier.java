package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

/**
 *	供应商
 */
@Setter
@Getter
public class Supplier extends BaseDomain{
	private String name;
	private String phone;
	private String address;
}
