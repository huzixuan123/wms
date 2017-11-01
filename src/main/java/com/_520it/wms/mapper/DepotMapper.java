package com._520it.wms.mapper;

import com._520it.wms.domain.Depot;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface DepotMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Depot depot);

    Depot selectByPrimaryKey(Long id);
    
    int updateByPrimaryKey(Depot depot);

    List<Depot> selectAll();

    List<Depot> queryForList(QueryObject qo);
    
    int queryForCount(QueryObject qo);
}