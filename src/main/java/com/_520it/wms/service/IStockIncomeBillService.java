package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

public interface IStockIncomeBillService {
	void saveOrUpdate(StockIncomeBill bill);

	void delete(Long id);

	StockIncomeBill get(Long id);

	List<StockIncomeBill> listAll();

	PageResult query(QueryObject qo);

	void audit(Long auditId);
}
