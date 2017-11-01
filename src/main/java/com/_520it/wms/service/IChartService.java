package com._520it.wms.service;

import com._520it.wms.query.QueryObject;

import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2017/10/22.
 */
public interface IChartService {
    List<Map<String,Object>> queryOrderChart(QueryObject qo);
    List<Map<String,Object>> querySaleChart(QueryObject qo);

}
