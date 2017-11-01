package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.Brand;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

public interface IBrandService {
	void saveOrUpdate(Brand b);

	void delete(Long id);

	Brand get(Long id);

	List<Brand> listAll();

	PageResult query(QueryObject qo);
}
