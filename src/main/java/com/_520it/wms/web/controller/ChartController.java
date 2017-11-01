package com._520it.wms.web.controller;

import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.SaleChartQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IChartService;
import com._520it.wms.service.IClientService;
import com._520it.wms.service.ISupplierService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2017/10/22.
 */
@Controller
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    private IChartService chartService;
    @Autowired
    private IClientService clientService;
    @Autowired
    private IBrandService brandService;

    @RequestMapping("/order")
    public String list(Model model, @ModelAttribute("qo") OrderChartQueryObject qo) {
        model.addAttribute("dataList", chartService.queryOrderChart(qo));
        model.addAttribute("clients", clientService.listAll());
        model.addAttribute("brands", brandService.listAll());
        model.addAttribute("groups", OrderChartQueryObject.groups);
        return "orderChart/list";
    }

    @RequestMapping("/sale")
    public String list2(Model model, @ModelAttribute("qo") SaleChartQueryObject qo) {
        model.addAttribute("dataList", chartService.querySaleChart(qo));
        model.addAttribute("clients", clientService.listAll());
        model.addAttribute("brands", brandService.listAll());
        model.addAttribute("groups", SaleChartQueryObject.groups);
        return "saleChart/list";
    }

    //柱状图
    @RequestMapping("/groupByBar")
    public String groupByBar(Model model, @ModelAttribute("qo") SaleChartQueryObject qo) {
        List<Map<String, Object>> list = chartService.querySaleChart(qo);
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        for (Map<String, Object> map : list) {
            list1.add(map.get("groupType"));
            list2.add(map.get("totalAmount"));
        }
        //共享横坐标数据到页面
        model.addAttribute("groupTypes", JSON.toJSONString(list1));
        //共享纵坐标数据到页面
        model.addAttribute("totalAmounts", JSON.toJSONString(list2));
        //按照什么来分组
        model.addAttribute("groupName", SaleChartQueryObject.groups.get(qo.getGroupBy()));
        return "saleChart/groupByBar";
    }

    //饼状图
    @RequestMapping("/groupByPie")
    public String groupByPie(Model model, @ModelAttribute("qo") SaleChartQueryObject qo) {
        List<Map<String, Object>> list = chartService.querySaleChart(qo);
        List<Map<String, Object>> data = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        BigDecimal max = BigDecimal.ZERO;
        for (Map<String, Object> item : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", item.get("groupType"));

            BigDecimal amount = (BigDecimal) item.get("totalAmount");

            map.put("value",amount);
            list2.add(item.get("groupType"));
            data.add(map);

            //排序,把数据呈现为漏斗图
            if(amount.compareTo(max)>=0){
                max=amount;
            }

        }
        //分组的类型和数据
        model.addAttribute("groupMsg", JSON.toJSONString(data));
        //
        model.addAttribute("groupType", JSON.toJSONString(list2));
        //按照什么来分组
        model.addAttribute("groupName", SaleChartQueryObject.groups.get(qo.getGroupBy()));
        model.addAttribute("max", max);
        return "saleChart/groupByPie";
    }
}
