package com._520it.wms.mapper;

import com._520it.wms.query.OrderChartQueryObject;
import com._520it.wms.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface ChartMapper {
    List<Map<String,Object>> queryOrderChart(QueryObject qo);
    List<Map<String,Object>> querySaleChart(QueryObject qo);
}
