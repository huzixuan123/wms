package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryObject {
	private int currentPage = 1;
	private int pageSize = 5;

	public int getStartIndex() {
		return (currentPage - 1) * pageSize;
	}

	public String str2null(String str) {
		return isEmpty(str) ? null : str;
	}

	public boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}
}
