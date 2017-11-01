package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.Department;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.query.QueryObject;

public interface IProductStockService {
	//入库操作
	void stockInCome(StockIncomeBill old);
	//出库操作
	void stockOutCome(StockOutcomeBill old);
	List<ProductStock> query(QueryObject qo);
}
