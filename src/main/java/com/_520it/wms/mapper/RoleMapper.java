package com._520it.wms.mapper;

import com._520it.wms.domain.Role;
import com._520it.wms.query.QueryObject;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    List<Role> selectAll();
    
    void update(Role role);
    
    Role get(Long id);

    List<Role> queryForList(QueryObject qo);
    
    int queryForCount(QueryObject qo);

	void insertRelationWithPermission(@Param("roleId")Long roleId, @Param("permissionId")Long permissionId);

	void deleteRelationWithPermission(Long id);

	void deleteRelationWithMenu(Long id);

	void insertRelationWithMenu(@Param("roleId")Long roleId,@Param("menuId")Long menuId);
}