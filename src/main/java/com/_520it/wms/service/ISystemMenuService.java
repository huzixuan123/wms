package com._520it.wms.service;

import java.util.List;
import java.util.Map;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

public interface ISystemMenuService {
	void saveOrUpdate(SystemMenu s);

	void delete(Long id);
	
	SystemMenu get(Long id);
	
	List<SystemMenu> listAll();

	PageResult query(QueryObject qo);

	List<Map> queryMenus(SystemMenu current);

	List<Map<String, Object>> queryMenuByParentSn(String parentSn);

	List<Map<String, Object>> queryMenuByParentSnAndEmployeeId(String parentSn,Long employeeId);
}
