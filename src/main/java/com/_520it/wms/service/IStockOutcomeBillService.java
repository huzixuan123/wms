package com._520it.wms.service;

import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IStockOutcomeBillService {
	void saveOrUpdate(StockOutcomeBill bill);

	void delete(Long id);

	StockOutcomeBill get(Long id);

	List<StockOutcomeBill> listAll();

	PageResult query(QueryObject qo);

	void audit(Long auditId);
}
