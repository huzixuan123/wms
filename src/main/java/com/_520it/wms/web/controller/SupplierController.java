package com._520it.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com._520it.wms.domain.Supplier;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.util.JSONResult;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private ISupplierService supplierService;

	@RequiredPermission("供应商列表")
	@RequestMapping("/list")
	public String list(Model model,QueryObject qo) {
		model.addAttribute("pageResult",supplierService.query(qo));
		return "supplier/list";
	}

	@RequiredPermission("供应商添加或者编辑")
	@RequestMapping("/input")
	public String input(Model model,Long id) {
		if(id!=null){
			model.addAttribute("supplier", supplierService.get(id));
		}
		return "supplier/input";
	}
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public JSONResult saveOrUpdate(Supplier supplier) {
		supplierService.saveOrUpdate(supplier);
		return new JSONResult();
	}
	
	@RequiredPermission("供应商删除")
	@RequestMapping("/delete")
	@ResponseBody
	public JSONResult delete(Long id) {
		supplierService.delete(id);
		return new JSONResult();
	}
}
