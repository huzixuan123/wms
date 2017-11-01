package com._520it.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.query.EmployeeQueryObject;
import com._520it.wms.service.IDepartmentService;
import com._520it.wms.service.IEmployeeService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.util.JSONResult;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IRoleService roleService;

	@RequiredPermission("员工列表")
	@RequestMapping("/list")
	public String list(Model model,EmployeeQueryObject qo) {
		model.addAttribute("qo", qo);
		model.addAttribute("pageResult", employeeService.query(qo));
		model.addAttribute("depts", departmentService.listAll());
		System.out.println("-wfwafewfwfsdfsfdsfsdfsdfsdf");
		return "employee/list";
	}

	@RequiredPermission("员工添加或者编辑")
	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("employee", employeeService.get(id));
		}
		model.addAttribute("depts", departmentService.listAll());
		model.addAttribute("roles", roleService.listAll());
		return "employee/input";
	}
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public JSONResult saveOrUpdate(Employee e,Long[] roleIds) {
		employeeService.saveOrUpdate(e,roleIds);
		return new JSONResult();
	}

	@RequiredPermission("员工删除")
	@RequestMapping("/delete")
	@ResponseBody
	public JSONResult delete(Long id) {
		employeeService.delete(id);
		return new JSONResult();
	}
	
	@RequiredPermission("员工批量删除")
	@RequestMapping("/batchDelete")
	@ResponseBody
	public JSONResult batchDelete(Long[] ids) {
		employeeService.batchDelete(ids);
		return new JSONResult();
	}
}
