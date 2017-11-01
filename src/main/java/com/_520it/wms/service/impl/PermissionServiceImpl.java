package com._520it.wms.service.impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com._520it.wms.domain.Permission;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.mapper.PermissionMapper;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;

@Service
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private PermissionMapper permissionMapper;
	@Override
	public void save(Permission p) {
		permissionMapper.insert(p);
	}

	@Override
	public void delete(Long id) {
		permissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Permission> listAll() {
		List<Permission> list = permissionMapper.selectAll();
		return list;
	}

	@Override
	public PageResult query(QueryObject qo) {
		int totalCount = permissionMapper.queryForCount(qo);
		if (totalCount == 0) {
			return PageResult.empty(qo.getPageSize());
		}
		List<Permission> list = permissionMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), list, totalCount);
	}

	@Override
	public void reload() {
		List<String> exps = permissionMapper.selectAllExpression();
		//1.扫描所有带有@Controller或者@RestController的类
		Map<String, Object> beans = context.getBeansWithAnnotation(Controller.class);
		beans.putAll(context.getBeansWithAnnotation(RestController.class));
		//2.获取这些类中所有的方法
		for (Object bean : beans.values()) {
			Method[] methods = bean.getClass().getDeclaredMethods();
			for (Method method : methods) {
				//3.获取required注解中的值封装到permission对象中
				RequiredPermission rp = method.getAnnotation(RequiredPermission.class);
				//如果方法上有required注解才执行
				if (rp != null) {
					Permission p = new Permission();
					p.setName(rp.value());
					//4.获取权限定名和方法名组成权限表达式封装到permission对象中
					String exp = bean.getClass().getName() + ":" + method.getName();
					p.setExpression(exp);
					if (!exps.contains(exp)) {
						permissionMapper.insert(p);
					}
				}
				;
			}
		}
	}
}
