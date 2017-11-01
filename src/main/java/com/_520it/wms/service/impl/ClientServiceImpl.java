package com._520it.wms.service.impl;

import com._520it.wms.domain.Client;
import com._520it.wms.mapper.ClientMapper;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private ClientMapper clientMapper;

	@Override
	public void saveOrUpdate(Client e) {
		if (e.getId() != null) {
			clientMapper.updateByPrimaryKey(e);
		} else {
			clientMapper.insert(e);
		}
	}

	@Override
	public void delete(Long id) {
		clientMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Client get(Long id) {
		Client e = clientMapper.selectByPrimaryKey(id);
		return e;
	}

	@Override
	public List<Client> listAll() {
		List<Client> list = clientMapper.selectAll();
		return list;
	}

	@Override
	public PageResult query(QueryObject qo) {
		int totalCount = clientMapper.queryForCount(qo);
		if (totalCount == 0) {
			return PageResult.empty(qo.getPageSize());
		}
		List<Client> list = clientMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), list, totalCount);
	}
}
