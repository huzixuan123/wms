package com._520it.wms.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.domain.Supplier;
import com._520it.wms.mapper.SupplierMapper;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.ISupplierService;

@Service
public class SupplierServiceImpl implements ISupplierService {

	@Autowired
	private SupplierMapper supplierMapper;

	public void saveOrUpdate(Supplier e) {
		if (e.getId() != null) {
			supplierMapper.updateByPrimaryKey(e);
		} else {
			supplierMapper.insert(e);
		}
	}

	public void delete(Long id) {
		supplierMapper.deleteByPrimaryKey(id);
	}

	public Supplier get(Long id) {
		Supplier e = supplierMapper.selectByPrimaryKey(id);
		return e;
	}

	public List<Supplier> listAll() {
		List<Supplier> list = supplierMapper.selectAll();
		return list;
	}

	public PageResult query(QueryObject qo) {
		int totalCount = supplierMapper.queryForCount(qo);
		if (totalCount == 0) {
			return PageResult.empty(qo.getPageSize());
		}
		List<Supplier> list = supplierMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), list, totalCount);
	}
}
