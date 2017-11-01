package com._520it.wms.web.controller;

import com._520it.wms.domain.Client;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IClientService;
import com._520it.wms.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private IClientService clientService;

	@RequiredPermission("客户列表")
	@RequestMapping("/list")
	public String list(Model model,QueryObject qo) {
		model.addAttribute("pageResult",clientService.query(qo));
		return "client/list";
	}

	@RequiredPermission("客户添加或者编辑")
	@RequestMapping("/input")
	public String input(Model model,Long id) {
		if(id!=null){
			model.addAttribute("client", clientService.get(id));
		}
		return "client/input";
	}
	
	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public JSONResult saveOrUpdate(Client client) {
		clientService.saveOrUpdate(client);
		return new JSONResult();
	}
	
	@RequiredPermission("客户删除")
	@RequestMapping("/delete")
	@ResponseBody
	public JSONResult delete(Long id) {
		clientService.delete(id);
		return new JSONResult();
	}
}
