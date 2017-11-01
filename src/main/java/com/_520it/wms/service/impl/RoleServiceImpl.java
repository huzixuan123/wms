package com._520it.wms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.domain.Role;
import com._520it.wms.mapper.RoleMapper;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	public void saveOrUpdate(Role r,Long[] permissionIds,Long[] menuIds) {
		if (r.getId() != null) {
			//修改时,先删除原来的数据
			roleMapper.deleteRelationWithPermission(r.getId());
			roleMapper.deleteRelationWithMenu(r.getId());
			roleMapper.update(r);
		} else {
			roleMapper.insert(r);
		}
		//添加超级管理员时ids为Null
		if(permissionIds!=null){
			//维护中间表关系
			for (Long permissionId : permissionIds) {
				roleMapper.insertRelationWithPermission(r.getId(),permissionId);
			}
		}
		if(menuIds!=null){
			for (Long menuId : menuIds) {
				roleMapper.insertRelationWithMenu(r.getId(), menuId);
			}
		}
	}

	public void delete(Long id) {
		//先删除中间表
		roleMapper.deleteRelationWithPermission(id);
		roleMapper.deleteRelationWithMenu(id);
		roleMapper.deleteByPrimaryKey(id);
	}

	public Role get(Long id) {
		Role role = roleMapper.get(id);
		return role;
	}
	public List<Role> listAll() {
		List<Role> list = roleMapper.selectAll();
		return list;
	}

	public PageResult query(QueryObject qo) {
		int totalCount = roleMapper.queryForCount(qo);
		if (totalCount == 0) {
			return PageResult.empty(qo.getPageSize());
		}
		List<Role> list = roleMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), list, totalCount);
	}


}
