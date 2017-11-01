package com._520it.wms.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com._520it.wms.domain.Role;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.service.IRoleService;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.util.JSONResult;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPermissionService permissionService;
	@Autowired
	private ISystemMenuService systemMenuService;

	@RequiredPermission("角色列表")
	@RequestMapping("/list")
	public String list(Model model,QueryObject qo) {
		model.addAttribute("qo", qo);
		model.addAttribute("pageResult", roleService.query(qo));
		return "role/list";
	}

	@RequiredPermission("角色添加或者编辑")
	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("role", roleService.get(id));
		}
		model.addAttribute("permissions", permissionService.listAll());
		model.addAttribute("menus", systemMenuService.listAll());
		return "role/input";
	}
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public JSONResult saveOrUpdate(Role r,Long[] permissionIds,Long[] menuIds) {
		roleService.saveOrUpdate(r,permissionIds,menuIds);
		return new JSONResult();
	}

	@RequiredPermission("角色删除")
	@RequestMapping("/delete")
	@ResponseBody
	public JSONResult delete(Long id) {
		roleService.delete(id);
		return new JSONResult();
	}
}
