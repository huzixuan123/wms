package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.management.Query;
import java.util.Date;

/**
 * 抽取订单查询条件
 */
@Setter
@Getter
public class BaseOrderQueryObject extends QueryObject{
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date beginDate;//业务开始时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;//业务结束时间
    private int status=-1;//审核状态
}
