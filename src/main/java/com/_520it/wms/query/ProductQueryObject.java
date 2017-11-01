package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductQueryObject extends QueryObject {
	private String keyword;
	private Long brandId=-1L;
	
	//keyword为null,"","  "都返回null
	public String getKeyword() {
		return super.str2null(keyword);
	}
}
