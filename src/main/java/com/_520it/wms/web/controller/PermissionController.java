package com._520it.wms.web.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.mapper.PermissionMapper;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IPermissionService;
import com._520it.wms.util.JSONResult;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private IPermissionService permissionService;

	@RequiredPermission("权限列表")
	@RequestMapping("/list")
	public String list(Model model,QueryObject qo) {
		model.addAttribute("qo", qo);
		model.addAttribute("pageResult", permissionService.query(qo));
		return "permission/list";
	}
	
	@RequiredPermission("权限删除")
	@RequestMapping("/delete")
	@ResponseBody
	public JSONResult delete(Long id) {
		permissionService.delete(id);
		return new JSONResult();
	}
	
	/**
	 * 加载权限
	 */
	@RequiredPermission("权限加载")
	@RequestMapping("/reload")
	@ResponseBody
	public JSONResult reload(){
		permissionService.reload();
		return new JSONResult();
	}
}
