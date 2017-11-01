package com._520it.wms.web.controller;


import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.query.StockOutcomeBillQueryObject;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IStockOutcomeBillService;
import com._520it.wms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/stockOutcomeBill")
public class StockOutcomeBillController {

	@Autowired
	private IStockOutcomeBillService billService;
	@Autowired
	private IDepotService depotService;
	@Autowired
	private IClientService clientService;

	@RequiredPermission("出库订单列表")
	@RequestMapping("/list")
	public String list(Model model, StockOutcomeBillQueryObject qo) {
		model.addAttribute("qo", qo);
		model.addAttribute("pageResult", billService.query(qo));
		model.addAttribute("depots", depotService.listAll());
		model.addAttribute("clients", clientService.listAll());
		return "stockOutcomeBill/list";
	}

	@RequiredPermission("出库订单编辑或者添加")
	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("stockOutcomeBill", billService.get(id));
		}
		model.addAttribute("depots", depotService.listAll());
		model.addAttribute("clients", clientService.listAll());
		return "stockOutcomeBill/input";
	}

	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public JSONResult saveOrUpdate(StockOutcomeBill bill) {
		billService.saveOrUpdate(bill);
		return new JSONResult();
	}

	@RequiredPermission("出库订单删除")
	@RequestMapping("/delete")
	@ResponseBody
	public JSONResult delete(Long id){
		if(id!=null){
			billService.delete(id);
		}
		return new JSONResult();
	}
	@RequiredPermission("出库订单审核")
	@RequestMapping("/audit")
	@ResponseBody
	public JSONResult audit(Long auditId){
		JSONResult jsonResult = new JSONResult();
		if(auditId!=null){
			try {
				billService.audit(auditId);
			} catch (Exception e ) {
				jsonResult.mark(e.getMessage());
			}
		}
		return jsonResult;
	}

	@RequiredPermission("出库订单查看")
	@RequestMapping("/view")
	public String view(Model model, Long id) {
		if (id != null) {
			model.addAttribute("stockOutcomeBill", billService.get(id));
		}
		model.addAttribute("depots", depotService.listAll());
		model.addAttribute("clients", clientService.listAll());
		return "stockOutcomeBill/view";
	}
}
