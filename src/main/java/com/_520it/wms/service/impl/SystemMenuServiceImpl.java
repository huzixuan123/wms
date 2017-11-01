package com._520it.wms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.mapper.SystemMenuMapper;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.ISystemMenuService;

@Service
public class SystemMenuServiceImpl implements ISystemMenuService {

	@Autowired
	private SystemMenuMapper systemMenuMapper;

	public void saveOrUpdate(SystemMenu systemMenu) {
		if (systemMenu.getId() != null) {
			systemMenuMapper.updateByPrimaryKey(systemMenu);
		} else {
			systemMenuMapper.insert(systemMenu);
		}
	}

	public void delete(Long id) {
		systemMenuMapper.deleteByPrimaryKey(id);
	}

	public SystemMenu get(Long id) {
		SystemMenu s = systemMenuMapper.selectByPrimaryKey(id);
		return s;
	}
	public List<SystemMenu> listAll() {
		List<SystemMenu> list = systemMenuMapper.selectAll();
		return list;
	}

	public PageResult query(QueryObject qo) {
		int totalCount = systemMenuMapper.queryForCount(qo);
		if (totalCount == 0) {
			return PageResult.empty(qo.getPageSize());
		}
		List<SystemMenu> list = systemMenuMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), list, totalCount);
	}

	@Override
	public List<Map> queryMenus(SystemMenu current) {
		//如果当前菜单不为空,就查找上一级节点存放到集合中
		List<Map> list = new ArrayList<>();
		while(current!=null){
			Map map = new HashMap<>();
			map.put("id", current.getId());
			map.put("name", current.getName());
			list.add(map);
			current = current.getParent();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> queryMenuByParentSn(String parentSn) {
		List<Map<String, Object>> list = systemMenuMapper.queryMenuByParentSn(parentSn);
		return list;
	}

	@Override
	public List<Map<String, Object>> queryMenuByParentSnAndEmployeeId(String parentSn, Long employeeId) {
		List<Map<String, Object>> list = systemMenuMapper.queryMenuByParentSnAndEmployeeId(parentSn,employeeId);
		return list;
	}
	

}
