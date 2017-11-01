package com._520it.wms.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com._520it.wms.service.IProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.domain.StockIncomeBill;
import com._520it.wms.domain.StockIncomeBillItem;
import com._520it.wms.mapper.ProductStockMapper;
import com._520it.wms.mapper.StockIncomeBillItemMapper;
import com._520it.wms.mapper.StockIncomeBillMapper;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IStockIncomeBillService;
import com._520it.wms.util.UserContext;

@Service
public class StockIncomeBillServiceImpl implements IStockIncomeBillService {

	@Autowired
	private StockIncomeBillMapper billMapper;
	@Autowired
	private StockIncomeBillItemMapper itemMapper;
	@Autowired
	private IProductStockService stockService;
	@Override
	public void saveOrUpdate(StockIncomeBill bill) {
		System.out.println(bill);
		if (bill.getId() != null) {
			//1.获得原来的单据对象
			StockIncomeBill old = billMapper.selectByPrimaryKey(bill.getId());
			//2.判断是否处于待审核状态,处于待审核就可以修改
			if(old.getStatus()==StockIncomeBill.STATE_NOMAL){
				//3..先把该单据中所有的明细删除
				itemMapper.deleteByBillId(bill.getId());
				//4.保存制单人信息
				bill.setInputUser(old.getInputUser());
				//3.保存制单时间
				bill.setInputTime(new Date());
				BigDecimal totalAmount = BigDecimal.ZERO;
				BigDecimal totalNumber = BigDecimal.ZERO;
				//4.迭代单据明细中的每一条数据,计算总金额和总数量
				for (StockIncomeBillItem item : bill.getItems()) {
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
			List<StockIncomeBillItem> items = bill.getItems();
			for (StockIncomeBillItem item : items) {
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
			for (StockIncomeBillItem item : items) {
				item.setBillId(bill.getId());
				itemMapper.insert(item);
			}
		}
	}
	@Override
	public void delete(Long id) {
		//先根据单据ID删除所有明细
		itemMapper.deleteByBillId(id);
		//再删除单据
		billMapper.deleteByPrimaryKey(id);
	}
	@Override
	public StockIncomeBill get(Long id) {
		StockIncomeBill bill = billMapper.selectByPrimaryKey(id);
		return bill;
	}
	@Override
	public List<StockIncomeBill> listAll() {
		List<StockIncomeBill> list = billMapper.selectAll();
		return list;
	}
	@Override
	public PageResult query(QueryObject qo) {
		int totalCount = billMapper.queryForCount(qo);
		if (totalCount == 0) {
			return PageResult.empty(qo.getPageSize());
		}
		List<StockIncomeBill> list = billMapper.queryForList(qo);
		return new PageResult(qo.getCurrentPage(), qo.getPageSize(), list, totalCount);
	}

	@Override
	public void audit(Long auditId) {
		//审核
		//1.获取数据库中的入库单据
		StockIncomeBill old = billMapper.selectByPrimaryKey(auditId);
		System.out.println(old.getItems());
		//2.判断状态是否为待审核
		if(old.getStatus()==StockIncomeBill.STATE_NOMAL){
			//3.重置审核状态为已审核
			old.setStatus(StockIncomeBill.STATE_AUDIT);
			//4.设置审核信息(审核人,审核时间)
			old.setAuditTime(new Date());
			old.setAuditor(UserContext.getCurrentUser());
			//5.添加单据
			billMapper.updateByPrimaryKey(old);
			//修改库存数据
			stockService.stockInCome(old);
		}
	}
}
