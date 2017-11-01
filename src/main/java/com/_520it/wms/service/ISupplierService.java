package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.Supplier;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

public interface ISupplierService {
	void saveOrUpdate(Supplier s);

	void delete(Long id);

	Supplier get(Long id);

	List<Supplier> listAll();

	PageResult query(QueryObject qo);
}
