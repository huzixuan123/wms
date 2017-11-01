package com._520it.wms.service.impl;

import com._520it.wms.mapper.ChartMapper;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2017/10/22.
 */
@Service
public class ChartServiceImpl implements IChartService {

    @Autowired
    private ChartMapper chartMapper;

    @Override
    public List<Map<String,Object>> queryOrderChart(QueryObject qo) {
        return chartMapper.queryOrderChart(qo);
    }


    @Override
    public List<Map<String, Object>> querySaleChart(QueryObject qo) {
        return chartMapper.querySaleChart(qo);
    }
}
