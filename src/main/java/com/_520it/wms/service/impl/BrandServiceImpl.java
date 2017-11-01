package com._520it.wms.service.impl;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.domain.Brand;
import com._520it.wms.mapper.BrandMapper;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IBrandService;

@Service
public class BrandServiceImpl implements IBrandService {

	@Autowired
	private BrandMapper brandMapper;

	public void saveOrUpdate(Brand e) {
		if (e.getId() != null) {
			brandMapper.updateByPrimaryKey(e);
		} else {
			brandMapper.insert(e);
		}
	}

	public void delete(Long id) {
		brandMapper.deleteByPrimaryKey(id);
	}

	public Brand get(Long id) {
		Brand e = brandMapper.selectByPrimaryKey(id);
		return e;
	}

	public List<Brand> listAll() {
		List<Brand> list = brandMapper.selectAll();
		return list;
	}

	public PageResult query(QueryObject qo) {
		int totalCount = brandMapper.queryForCount(qo);
		if (totalCount == 0) {
			return PageResult.empty(qo.getPageSize());
		}
		List<Brand> list = brandMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), list, totalCount);
	}
}
