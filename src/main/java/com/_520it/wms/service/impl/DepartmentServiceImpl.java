package com._520it.wms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.domain.Department;
import com._520it.wms.mapper.DepartmentMapper;
import com._520it.wms.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	
	public void saveOrUpdate(Department dept) {
		if(dept.getId()!=null){
			departmentMapper.updateByPrimaryKey(dept);
		}else{
			departmentMapper.insert(dept);
		}
	}

	public void delete(Long id) {
		departmentMapper.deleteByPrimaryKey(id);
	}

	public Department get(Long id) {
		Department dept = departmentMapper.selectByPrimaryKey(id);
		return dept;
	}

	public List<Department> listAll() {
		List<Department> list = departmentMapper.selectAll();
		return list;
	}

}
