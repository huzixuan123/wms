package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.Product;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

public interface IProductService {
	void saveOrUpdate(Product p);

	void delete(Long id);

	Product get(Long id);

	List<Product> listAll();

	PageResult query(QueryObject qo);
}
