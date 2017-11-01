package com._520it.wms.query;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by apple on 2017/10/22.
 */
@Setter
@Getter
public class ProductStockQueryObject extends QueryObject{
    private String keyword;
    private Long depotId=-1L;
    private Long brandId=-1L;
    private BigDecimal maxNumber;

    public String getKeyword() {
        return super.str2null(keyword);
    }

}
