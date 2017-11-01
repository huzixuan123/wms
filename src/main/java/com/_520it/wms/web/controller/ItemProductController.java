package com._520it.wms.web.controller;


import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IProductService;

@Controller
@RequestMapping("/itemProduct")
public class ItemProductController {

	@Autowired
	private IProductService productService;
	@Autowired
	private IBrandService brandService;
	@Autowired
	private ServletContext context;

	@RequestMapping("/list")
	public String list(Model model,@ModelAttribute("qo")ProductQueryObject qo) {
		model.addAttribute("pageResult", productService.query(qo));
		model.addAttribute("brands", brandService.listAll());
		return "itemProduct/list";
	}
}
