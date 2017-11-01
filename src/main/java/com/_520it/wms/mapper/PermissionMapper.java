package com._520it.wms.mapper;

import com._520it.wms.domain.Permission;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface PermissionMapper {

	int deleteByPrimaryKey(Long id);

	int insert(Permission record);

	List<Permission> selectAll();

	List<Permission> queryForList(QueryObject qo);

	int queryForCount(QueryObject qo);
	
	
	/**
	 * 查询所有的权限表达式
	 */
	List<String> selectAllExpression();
}