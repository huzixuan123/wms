package com._520it.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com._520it.wms.domain.Depot;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepotService;
import com._520it.wms.util.JSONResult;

@Controller
@RequestMapping("/depot")
public class DepotController {

	@Autowired
	private IDepotService depotService;

	@RequiredPermission("仓库列表")
	@RequestMapping("/list")
	public String list(Model model,QueryObject qo) {
		model.addAttribute("pageResult",depotService.query(qo));
		return "depot/list";
	}

	@RequiredPermission("仓库添加或者编辑")
	@RequestMapping("/input")
	public String input(Model model,Long id) {
		if(id!=null){
			model.addAttribute("depot", depotService.get(id));
		}
		return "depot/input";
	}
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public JSONResult saveOrUpdate(Depot depot) {
		depotService.saveOrUpdate(depot);
		return new JSONResult();
	}
	
	@RequiredPermission("仓库删除")
	@RequestMapping("/delete")
	@ResponseBody
	public JSONResult delete(Long id) {
		depotService.delete(id);
		return new JSONResult();
	}
}
