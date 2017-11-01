package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.Permission;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

public interface IPermissionService {
	void save(Permission p);

	void delete(Long id);

	List<Permission> listAll();

	PageResult query(QueryObject qo);

	void reload();
}
