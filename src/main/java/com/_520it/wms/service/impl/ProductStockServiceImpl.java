package com._520it.wms.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com._520it.wms.domain.*;
import com._520it.wms.query.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.mapper.ProductStockMapper;
import com._520it.wms.service.IProductStockService;

@Service
public class ProductStockServiceImpl implements IProductStockService {

	@Autowired
	private ProductStockMapper productStockMapper;
	@Autowired
	private ProductStockMapper psMapper;

	@Override
	public void stockInCome(StockIncomeBill old) {
		//6.遍历单据的明细
		for (StockIncomeBillItem item : old.getItems()) {
			//根据货品Id和仓库id查询出唯一的库存数据
			ProductStock ps = psMapper.selectByProductAndDepot
					(item.getProduct().getId(),old.getDepot().getId());
			if(ps==null){//如果没有,应该插入一条数据
				ps = new ProductStock();
				ps.setPrice(item.getCostPrice());
				ps.setProduct(item.getProduct());
				ps.setDepot(old.getDepot());
				ps.setAmount(item.getAmount());
				ps.setStoreNumber(item.getNumber());
				psMapper.insert(ps);
			}else{//如果有,就在原有的基础上修改库存数量和库存价格
				//设置新的库存数量和库存价格
				BigDecimal totalNumber = item.getNumber()
						.add(ps.getStoreNumber()).setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal totalAmount = item.getAmount()
						.add(ps.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
				BigDecimal price = 	totalAmount.divide(totalNumber,2,BigDecimal.ROUND_HALF_UP);
				ps.setAmount(totalAmount);
				ps.setPrice(price);
				ps.setStoreNumber(totalNumber);
				psMapper.updateByPrimaryKey(ps);
			}
		}
	}

	@Override
	public void stockOutCome(StockOutcomeBill old) {
		//遍历出库明细
		List<StockOutcomeBillItem> items = old.getItems();
		for (StockOutcomeBillItem item : items) {
			//根据货品id和仓库Id来确定唯一的库存数据
			ProductStock ps = psMapper.selectByProductAndDepot
					(item.getProduct().getId(),old.getDepot().getId());
			//如果指定仓库中没有该货品
			if(ps==null){
				throw  new RuntimeException(old.getDepot().getName()+"仓库中没有货品"+item.getProduct().getName());
				//如果指定仓库中货品数量不足
			}else if (ps.getStoreNumber().compareTo(item.getNumber())<0) {
				throw new RuntimeException(old.getDepot().getName()+"仓库中"+item.getProduct().getName()+"货品不足");
				//有该货品且数量充足
			}else {
				//库存中当下的货品数量=原来的货品数量-出库的货品数量
				ps.setStoreNumber(ps.getStoreNumber().subtract(item.getNumber()));
				//库存中当下的货品总额=原来的货品总额-出库的货品数量*该货品在库存中的价格
				ps.setAmount(ps.getAmount().subtract(item.getNumber().multiply(ps.getPrice())));
				//更新库存信息
				psMapper.updateByPrimaryKey(ps);
			}
			//出库的时候添加销售帐数据
			SaleAccount sa = new SaleAccount();
			sa.setVdate(new Date());
			sa.setNumber(item.getNumber());
			sa.setCostPrice(ps.getPrice());
			sa.setSalePrice(item.getSalePrice());
			sa.setCostAmount(sa.getNumber().multiply(sa.getCostPrice()));
			sa.setSaleAmount(item.getAmount());
			sa.setSaleman(old.getInputUser());
			sa.setProduct(item.getProduct());
			sa.setClient(old.getClient());
		}
	}

	@Override
	public List<ProductStock> query(QueryObject qo) {
		return productStockMapper.queryForList(qo);
	}
}
