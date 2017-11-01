package com._520it.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com._520it.wms.domain.Department;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.service.IDepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private IDepartmentService departmentService;

	@RequiredPermission("部门列表")
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("depts", departmentService.listAll());
		return "department/list";
	}

	@RequiredPermission("部门添加或者编辑")
	@RequestMapping("/input")
	public String input(Model model,Long id) {
		if(id!=null){
			model.addAttribute("department", departmentService.get(id));
		}
		return "department/input";
	}
	
	@RequestMapping("/saveOrUpdate")
	public String saveOrUpdate(Department dept) {
		departmentService.saveOrUpdate(dept);
		return "redirect:/department/list.do";
	}
	
	@RequiredPermission("部门删除")
	@RequestMapping("/delete")
	public String delete(Long id) {
		departmentService.delete(id);
		return "redirect:/department/list.do";
	}
}
