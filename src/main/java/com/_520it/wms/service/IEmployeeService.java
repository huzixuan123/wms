package com._520it.wms.service;

import java.util.List;

import com._520it.wms.domain.Employee;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;

public interface IEmployeeService {
	void saveOrUpdate(Employee e,Long[] ids);

	void delete(Long id);

	Employee get(Long id);

	List<Employee> listAll();

	PageResult query(QueryObject qo);

	void checkLogin(String username, String password);

	void batchDelete(Long[] ids);
	
}
