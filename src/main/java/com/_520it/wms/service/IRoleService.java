package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.Role;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

public interface IRoleService {
	void saveOrUpdate(Role r,Long[] permissionIds,Long[] menuIds);

	void delete(Long id);
	
	Role get(Long id);
	
	List<Role> listAll();

	PageResult query(QueryObject qo);
	
}
