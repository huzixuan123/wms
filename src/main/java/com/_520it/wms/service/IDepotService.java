package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.Depot;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

public interface IDepotService {
	void saveOrUpdate(Depot depot);

	void delete(Long id);

	Depot get(Long id);

	List<Depot> listAll();

	PageResult query(QueryObject qo);
}
