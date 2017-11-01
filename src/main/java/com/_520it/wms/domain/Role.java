package com._520it.wms.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Role extends BaseDomain{
	private String name;
	private String sn;
	//给角色分配权限
	private List<Permission> permissions = new ArrayList<>();
	//给角色分配菜单
	private List<SystemMenu> menus = new ArrayList<>();
}
