package com._520it.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by apple on 2017/10/21.
 */
@Setter
@Getter
public class StockOutcomeBillItem extends BaseDomain{
    private BigDecimal salePrice; //商品售价
    private BigDecimal number; //入库数量
    private BigDecimal amount;//入库总价
    private String remark;//备注
    private Product product;//商品
    private Long billId;//关联单据
}
