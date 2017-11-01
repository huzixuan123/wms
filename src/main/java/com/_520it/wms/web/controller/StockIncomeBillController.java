package com._520it.wms.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.query.QrderBillQueryObject;
import com._520it.wms.query.StockIncomeBillQueryObject;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IStockIncomeBillService;
import com._520it.wms.util.JSONResult;

@Controller
@RequestMapping("/stockIncomeBill")
public class StockIncomeBillController {

	@Autowired
	private IStockIncomeBillService billService;
	@Autowired
	private IDepotService depotService;

	@RequiredPermission("入库订单列表")
	@RequestMapping("/list")
	public String list(Model model, StockIncomeBillQueryObject qo) {
		model.addAttribute("qo", qo);
		model.addAttribute("pageResult", billService.query(qo));
		model.addAttribute("depots", depotService.listAll());
		return "stockIncomeBill/list";
	}

	@RequiredPermission("入库订单编辑或者添加")
	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("stockIncomeBill", billService.get(id));
		}
		model.addAttribute("depots", depotService.listAll());
		return "stockIncomeBill/input";
	}

	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public JSONResult saveOrUpdate(StockIncomeBill bill) {
		billService.saveOrUpdate(bill);
		System.out.println(bill.getItems());
		return new JSONResult();
	}
	
	@RequiredPermission("入库订单删除")
	@RequestMapping("/delete")
	@ResponseBody
	public JSONResult delete(Long id){
		if(id!=null){
			billService.delete(id);
		}
		return new JSONResult();
	}
	@RequiredPermission("入库订单审核")
	@RequestMapping("/audit")
	@ResponseBody
	public JSONResult audit(Long auditId){
		if(auditId!=null){
			billService.audit(auditId);
		}
		return new JSONResult();
	}
	
	@RequiredPermission("入库订单查看")
	@RequestMapping("/view")
	public String view(Model model, Long id) {
		if (id != null) {
			model.addAttribute("stockIncomeBill", billService.get(id));
		}
		model.addAttribute("depots", depotService.listAll());
		return "stockIncomeBill/view";
	}
}
