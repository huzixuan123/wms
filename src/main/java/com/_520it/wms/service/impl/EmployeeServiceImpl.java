package com._520it.wms.service.impl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com._520it.wms.domain.Employee;
import com._520it.wms.mapper.EmployeeMapper;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.util.MD5;
import com._520it.wms.util.UserContext;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	public void saveOrUpdate(Employee e,Long[] roleIds) {
		if (e.getId() != null) {
			//修改角色信息之前先删除中间表
			employeeMapper.deleteRelationWithRole(e.getId());
			employeeMapper.updateByPrimaryKey(e);
		} else {
			e.setPassword(MD5.encode(e.getPassword()));
			employeeMapper.insert(e);
		}
		if(roleIds!=null){
			for (Long roleId : roleIds) {
				//维护中间表关系
				employeeMapper.insertRelationWithRole(e.getId(),roleId);
			}
		}
	}

	public void delete(Long id) {
		//先删除中间表
		employeeMapper.deleteRelationWithRole(id);
		employeeMapper.deleteByPrimaryKey(id);
	}

	public Employee get(Long id) {
		Employee e = employeeMapper.selectByPrimaryKey(id);
		return e;
	}

	public List<Employee> listAll() {
		List<Employee> list = employeeMapper.selectAll();
		return list;
	}

	public PageResult query(QueryObject qo) {
		int totalCount = employeeMapper.queryForCount(qo);
		if (totalCount == 0) {
			return PageResult.empty(qo.getPageSize());
		}
		List<Employee> list = employeeMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), list, totalCount);
	}

	public void checkLogin(String username, String password) {
		Employee e = employeeMapper.selectEmployeeByUsernameAndPassword(username,MD5.encode(password));
		if(e==null){
			throw new RuntimeException("账号或者密码错误");
		}
		
		//将登陆信息存储到session中
		UserContext.setCurrentUser(e);
		//将该用户权限表达式存储到session中
		List<String> exps = employeeMapper.selectExpressionByEmployeeId(e.getId());
		UserContext.setPermissionExpressions(exps);
	}

	@Override
	public void batchDelete(Long[] ids) {
		employeeMapper.batchDelete(ids);
	}
}
