package com._520it.wms.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com._520it.wms.domain.OrderBill;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.query.QrderBillQueryObject;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IOrderBillService;
import com._520it.wms.service.ISupplierService;
import com._520it.wms.util.JSONResult;
import com._520it.wms.util.UserContext;

@Controller
@RequestMapping("/orderBill")
public class OrderBillController {

	@Autowired
	private IOrderBillService billService;
	@Autowired
	private ISupplierService supplierService;

	@RequiredPermission("采购订单列表")
	@RequestMapping("/list")
	public String list(Model model, QrderBillQueryObject qo) {
		model.addAttribute("qo", qo);
		model.addAttribute("pageResult", billService.query(qo));
		model.addAttribute("suppliers", supplierService.listAll());
		return "orderBill/list";
	}

	@RequiredPermission("采购订单编辑或者添加")
	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("orderBill", billService.get(id));
		}
		model.addAttribute("suppliers", supplierService.listAll());
		return "orderBill/input";
	}

	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public JSONResult saveOrUpdate(OrderBill bill) {
		billService.saveOrUpdate(bill);
		System.out.println(bill.getItems());
		return new JSONResult();
	}
	
	@RequiredPermission("采购订单删除")
	@RequestMapping("/delete")
	@ResponseBody
	public JSONResult delete(Long id){
		if(id!=null){
			billService.delete(id);
		}
		return new JSONResult();
	}
	@RequiredPermission("采购订单审核")
	@RequestMapping("/audit")
	@ResponseBody
	public JSONResult audit(Long auditId){
		if(auditId!=null){
			billService.audit(auditId);
		}
		return new JSONResult();
	}
	
	@RequiredPermission("采购订单查看")
	@RequestMapping("/view")
	public String view(Model model, Long id) {
		if (id != null) {
			model.addAttribute("orderBill", billService.get(id));
		}
		model.addAttribute("suppliers", supplierService.listAll());
		return "orderBill/view";
	}
}
