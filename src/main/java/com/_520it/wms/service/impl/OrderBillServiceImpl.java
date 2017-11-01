package com._520it.wms.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.OrderBill;
import com._520it.wms.domain.OrderBillItem;
import com._520it.wms.mapper.OrderBillItemMapper;
import com._520it.wms.mapper.OrderBillMapper;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IOrderBillService;
import com._520it.wms.util.UserContext;

@Service
public class OrderBillServiceImpl implements IOrderBillService {

	@Autowired
	private OrderBillMapper billMapper;
	@Autowired
	private OrderBillItemMapper itemMapper;

	public void saveOrUpdate(OrderBill bill) {
		System.out.println(bill);
		if (bill.getId() != null) {
			//1.获得原来的单据对象
			OrderBill old = billMapper.selectByPrimaryKey(bill.getId());
			//2.判断是否处于待审核状态,处于待审核就可以修改
			if(old.getStatus()==OrderBill.STATE_NOMAL){
				//3..先把该单据中所有的明细删除
				itemMapper.deleteByBillId(bill.getId());
				//4.保存制单人信息
				bill.setInputUser(old.getInputUser());
				//3.保存制单时间
				bill.setInputTime(new Date());
				BigDecimal totalAmount = BigDecimal.ZERO;
				BigDecimal totalNumber = BigDecimal.ZERO;
				//4.迭代单据明细中的每一条数据,计算总金额和总数量
				for (OrderBillItem item : bill.getItems()) {
					BigDecimal amount = item.getCostPrice().multiply(item.getNumber()).setScale(2,
							BigDecimal.ROUND_HALF_UP);
					totalNumber = totalNumber.add(item.getNumber());
					totalAmount = totalAmount.add(amount);
					//5.将每条明细的金额小计存入对应的item对象中
					item.setAmount(amount);
					//6.设置明细所属单据的ID
					item.setBillId(bill.getId());
					//7.重新将新的明细添加到单据中
					itemMapper.insert(item);
				}
				//8.给单据设置总金额和数量
				bill.setTotalAmount(totalAmount);
				bill.setTotalNumber(totalNumber);
				//9.修改单据对象
				billMapper.updateByPrimaryKey(bill);
			}
	
		} else {
			//1.保存制单人信息
			Employee inputUser = UserContext.getCurrentUser();
			bill.setInputUser(inputUser);
			//2.保存制单时间
			bill.setInputTime(new Date());
			BigDecimal totalAmount = BigDecimal.ZERO;
			BigDecimal totalNumber = BigDecimal.ZERO;
			//3.迭代单据明细中的每一条数据,计算总金额和总数量
			List<OrderBillItem> items = bill.getItems();
			for (OrderBillItem item : items) {
				BigDecimal amount = item.getCostPrice().multiply(item.getNumber()).setScale(2,
						BigDecimal.ROUND_HALF_UP);
				totalNumber = totalNumber.add(item.getNumber());
				totalAmount = totalAmount.add(amount);
				//将每条明细的金额小计存入对应的item对象中
				item.setAmount(amount);
			}
			//给单据设置总金额和数量
			bill.setTotalAmount(totalAmount);
			bill.setTotalNumber(totalNumber);
			//4.保存单据对象
			billMapper.insert(bill);
			//5.设置明细所属单据的ID
			for (OrderBillItem item : items) {
				item.setBillId(bill.getId());
				itemMapper.insert(item);
			}
		}
	}

	public void delete(Long id) {
		itemMapper.deleteByBillId(id);
		billMapper.deleteByPrimaryKey(id);
	}

	public OrderBill get(Long id) {
		OrderBill bill = billMapper.selectByPrimaryKey(id);
		return bill;
	}

	public List<OrderBill> listAll() {
		List<OrderBill> list = billMapper.selectAll();
		return list;
	}

	public PageResult query(QueryObject qo) {
		int totalCount = billMapper.queryForCount(qo);
		if (totalCount == 0) {
			return PageResult.empty(qo.getPageSize());
		}
		List<OrderBill> list = billMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), list, totalCount);
	}

	@Override
	public void audit(Long auditId) {
		//1.获取数据库中真实的单据
		OrderBill old = billMapper.selectByPrimaryKey(auditId);
		//2.判断是否已审核
		if(old.getStatus()==OrderBill.STATE_NOMAL){ //如果待审核
			//3.将状态设置为已审核
			old.setStatus(OrderBill.STATE_AUDIT);
			//4.设置审核时间和审核人
			old.setAuditTime(new Date());
			old.setAuditor(UserContext.getCurrentUser());
			//5.更改原来的单据
			billMapper.updateByPrimaryKey(old);
		}
	}
}
