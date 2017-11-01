package com._520it.wms.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Employee extends BaseDomain{
	private String name;
	private String password;
	private String email;
	private int age;
	private boolean admin=false;
	private Department dept;
	private List<Role> roles = new ArrayList<>();
}
