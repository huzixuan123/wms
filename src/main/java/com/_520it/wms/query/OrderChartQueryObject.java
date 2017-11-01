package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Setter
@Getter
public class OrderChartQueryObject extends QueryObject{
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    private String keyword;
    private Long supplierId=-1L;
    private Long brandId=-1L;
    private String groupBy="iu.name"; //按照什么条件分组

    //keyword为null,"","  "都返回null
    public String getKeyword() {
        return super.str2null(keyword);
    }

    //存放分组查询的条件参数
    public static Map<String,Object> groups = new LinkedHashMap<>(); //分组的多个条件
    static {
        groups.put("iu.name","订货人员");
        groups.put("p.name","货品名称");
        groups.put("s.name","供应商");
        groups.put("p.brandName","货品品牌");
        groups.put("DATE_FORMAT(bill.vdate,'%Y-%m')","订货日期(月)");
        groups.put("DATE_FORMAT(bill.vdate,'%Y-%m-%d')","订货日期(日)");
    }
}
