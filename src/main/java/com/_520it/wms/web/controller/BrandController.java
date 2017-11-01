package com._520it.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com._520it.wms.domain.Brand;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.util.JSONResult;

@Controller
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	private IBrandService brandService;

	@RequiredPermission("品牌列表")
	@RequestMapping("/list")
	public String list(Model model,QueryObject qo) {
		model.addAttribute("pageResult",brandService.query(qo));
		return "brand/list";
	}

	@RequiredPermission("品牌添加或者编辑")
	@RequestMapping("/input")
	public String input(Model model,Long id) {
		if(id!=null){
			model.addAttribute("brand", brandService.get(id));
		}
		return "brand/input";
	}
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public JSONResult saveOrUpdate(Brand brand) {
		brandService.saveOrUpdate(brand);
		return new JSONResult();
	}
	
	@RequiredPermission("品牌删除")
	@RequestMapping("/delete")
	@ResponseBody
	public JSONResult delete(Long id) {
		brandService.delete(id);
		return new JSONResult();
	}
}
