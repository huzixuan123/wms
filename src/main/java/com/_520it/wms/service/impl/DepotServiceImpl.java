package com._520it.wms.service.impl;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.domain.Depot;
import com._520it.wms.mapper.DepotMapper;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IDepotService;

@Service
public class DepotServiceImpl implements IDepotService {

	@Autowired
	private DepotMapper depotMapper;

	public void saveOrUpdate(Depot e) {
		if (e.getId() != null) {
			depotMapper.updateByPrimaryKey(e);
		} else {
			depotMapper.insert(e);
		}
	}

	public void delete(Long id) {
		depotMapper.deleteByPrimaryKey(id);
	}

	public Depot get(Long id) {
		Depot e = depotMapper.selectByPrimaryKey(id);
		return e;
	}

	public List<Depot> listAll() {
		List<Depot> list = depotMapper.selectAll();
		return list;
	}

	public PageResult query(QueryObject qo) {
		int totalCount = depotMapper.queryForCount(qo);
		if (totalCount == 0) {
			return PageResult.empty(qo.getPageSize());
		}
		List<Depot> list = depotMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), list, totalCount);
	}
}
