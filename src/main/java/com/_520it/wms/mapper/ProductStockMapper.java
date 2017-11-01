package com._520it.wms.mapper;

import com._520it.wms.domain.ProductStock;
import java.util.List;

import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.query.ProductStockQueryObject;
import com._520it.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

public interface ProductStockMapper {

    int insert(ProductStock record);

    List<ProductStock> queryForList(QueryObject qo);

    int updateByPrimaryKey(ProductStock record);

	ProductStock selectByProductAndDepot(@Param("productId")Long productId,@Param("depotId")Long depotId);
}