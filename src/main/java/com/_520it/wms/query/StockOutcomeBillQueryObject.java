package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by apple on 2017/10/21.
 */
@Setter
@Getter
public class StockOutcomeBillQueryObject extends BaseOrderQueryObject{
    private Long depotId;//仓库
    private Long clientId;//客户
}
