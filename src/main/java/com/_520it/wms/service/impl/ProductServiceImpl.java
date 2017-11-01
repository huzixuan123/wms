package com._520it.wms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.domain.Brand;
import com._520it.wms.domain.Product;
import com._520it.wms.mapper.BrandMapper;
import com._520it.wms.mapper.ProductMapper;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private BrandMapper brandMapper;
	
	public void saveOrUpdate(Product p) {
		if(p.getBrandId()!=null){
			Brand brand = brandMapper.selectByPrimaryKey(p.getBrandId());
			p.setBrandName( brand.getName());
		}
		if (p.getId() != null) {
			productMapper.updateByPrimaryKey(p);
		} else {
			productMapper.insert(p);
		}
	}

	public void delete(Long id) {
		productMapper.deleteByPrimaryKey(id);
	}

	public Product get(Long id) {
		Product e = productMapper.selectByPrimaryKey(id);
		return e;
	}

	public List<Product> listAll() {
		List<Product> list = productMapper.selectAll();
		return list;
	}

	public PageResult query(QueryObject qo) {
		int totalCount = productMapper.queryForCount(qo);
		if (totalCount == 0) {
			return PageResult.empty(qo.getPageSize());
		}
		List<Product> list = productMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), list, totalCount);
	}
}
