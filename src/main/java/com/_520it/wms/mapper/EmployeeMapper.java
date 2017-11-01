package com._520it.wms.mapper;

import com._520it.wms.domain.Employee;
import com._520it.wms.query.QueryObject;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);
    
    List<Employee> queryForList(QueryObject qo);
    
    int queryForCount(QueryObject qo);

	void insertRelationWithRole(@Param("employeeId")Long id, @Param("roleId")Long roleId);

	void deleteRelationWithRole(Long id);

	Employee selectEmployeeByUsernameAndPassword(@Param("username")String username, @Param("password")String password);

	List<String> selectExpressionByEmployeeId(Long id);

	void batchDelete(@Param("ids") Long[] ids);
}