package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeQueryObject extends QueryObject {
    private String keyword;//名字或者邮箱
    private Long deptId = -1L;

    //keyword为null,"","  "都返回null
    public String getKeyword() {
        return super.str2null(keyword);
    }
}
