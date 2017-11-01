package com._520it.wms.mapper;

import com._520it.wms.domain.SystemMenu;
import com._520it.wms.query.QueryObject;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SystemMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemMenu record);

    SystemMenu selectByPrimaryKey(Long id);

    List<SystemMenu> selectAll();

    int updateByPrimaryKey(SystemMenu record);
    
    List<SystemMenu> queryForList(QueryObject qo);
    
    int queryForCount(QueryObject qo);
    
    List<SystemMenu> selectedMenuByRoleId(Long id);

	List<Map<String, Object>> queryMenuByParentSn(String parentSn);

	List<Map<String, Object>> queryMenuByParentSnAndEmployeeId(@Param("parentSn")String parentSn, @Param("employeeId")Long employeeId);
}