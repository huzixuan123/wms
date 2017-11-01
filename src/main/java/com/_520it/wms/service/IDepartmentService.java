package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.Department;

public interface IDepartmentService {
	void saveOrUpdate(Department dept);

	void delete(Long id);

	Department get(Long id);

	List<Department> listAll();
}
