package com._520it.wms.web.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.domain.SystemMenu;
import com._520it.wms.query.SystemMenuQueryObject;
import com._520it.wms.service.ISystemMenuService;
import com._520it.wms.util.JSONResult;
import com._520it.wms.util.UserContext;

@Controller
@RequestMapping("/systemMenu")
public class SystemMenuController {

	@Autowired
	private ISystemMenuService systemMenuService;

	@RequestMapping("/list")
	@RequiredPermission("菜单列表")
	public String list(Model model, @ModelAttribute("qo") SystemMenuQueryObject qo) {
		if (qo.getParentId() != null) {
			SystemMenu parent = systemMenuService.get(qo.getParentId());
			List<Map> menus = systemMenuService.queryMenus(parent);
			Collections.reverse(menus);
			model.addAttribute("menus", menus);
		}

		model.addAttribute("pageResult", systemMenuService.query(qo));
		return "systemMenu/list";
	}

	@RequestMapping("/input")
	public String input(Model model, Long id, Long parentId) {
		if (id != null) {
			model.addAttribute("systemMenu", systemMenuService.get(id));
		}
		if (parentId == null) {
			model.addAttribute("parentName", "根目录");
		} else {
			model.addAttribute("parentName", systemMenuService.get(parentId).getName());
			model.addAttribute("parentId", parentId);
		}
		return "systemMenu/input";
	}

	@RequestMapping("/saveOrUpdate")
	@RequiredPermission("菜单编辑或者修改")
	@ResponseBody
	public JSONResult saveOrUpdate(SystemMenu systemMenu, Long parentId) {
		if (parentId != null) {
			SystemMenu parent = new SystemMenu();
			parent.setId(parentId);
			systemMenu.setParent(parent);
		}
		systemMenuService.saveOrUpdate(systemMenu);
		return new JSONResult();
	}

	@RequestMapping("/delete")
	@RequiredPermission("菜单删除")
	@ResponseBody
	public JSONResult delete(Long id) {
		systemMenuService.delete(id);
		return new JSONResult();
	}

	@RequestMapping("/queryMenuByParentSn")
	@ResponseBody
	public List<Map<String, Object>> queryMenuByParentSn(String parentSn) {
		Employee employee = UserContext.getCurrentUser();
		List<Map<String, Object>> list = null;
		if (employee.isAdmin()) {
			//{id:21,pId:2,name:"部门列表",action:""}
			list = systemMenuService.queryMenuByParentSn(parentSn);
		} else {
			list = systemMenuService.queryMenuByParentSnAndEmployeeId(parentSn,employee.getId());
		}
		return list;
	}
}
