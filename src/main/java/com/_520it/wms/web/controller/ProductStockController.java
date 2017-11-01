package com._520it.wms.web.controller;

import com._520it.wms.domain.ProductStock;
import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.query.ProductStockQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productStock")
public class ProductStockController {

    @Autowired
    private IProductStockService productStockService;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private IDepotService depotService;

    @RequestMapping("list")
    public String list(Model model,ProductStockQueryObject qo){
        model.addAttribute("qo",qo);
        model.addAttribute("list",productStockService.query(qo));
        model.addAttribute("brands",brandService.listAll());
        model.addAttribute("depots",depotService.listAll());
        return "productStock/list";
    }
}
