package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

public interface IOrderBillService {
	void saveOrUpdate(OrderBill bill);

	void delete(Long id);

	OrderBill get(Long id);

	List<OrderBill> listAll();

	PageResult query(QueryObject qo);

	void audit(Long auditId);
}
