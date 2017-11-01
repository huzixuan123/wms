package com._520it.wms.query;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class PageResult {
	private int currentPage;
	private int pageSize;
	private List list;
	private int totalCount;
	private int totalPage;
	private int prevPage;
	private int nextPage;

	public PageResult(int currentPage, int pageSize, List list, int totalCount) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.list = list;
		this.totalCount = totalCount;
		this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		this.prevPage = currentPage - 1 > 0 ? currentPage - 1 : 1;
		this.nextPage = currentPage + 1 > totalPage ? totalPage : currentPage + 1;
	}

	public static  PageResult empty(int pageSize){
		return new PageResult(1, pageSize, new ArrayList<>(),0);
	}
}
