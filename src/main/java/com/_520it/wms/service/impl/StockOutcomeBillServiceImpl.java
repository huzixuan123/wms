package com._520it.wms.service.impl;

import com._520it.wms.domain.Employee;
import com._520it.wms.domain.ProductStock;
import com._520it.wms.domain.StockOutcomeBill;
import com._520it.wms.domain.StockOutcomeBillItem;
import com._520it.wms.mapper.ProductStockMapper;
import com._520it.wms.mapper.StockOutcomeBillItemMapper;
import com._520it.wms.mapper.StockOutcomeBillMapper;
import com._520it.wms.query.PageResult;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.service.IStockOutcomeBillService;
import com._520it.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {

    @Autowired
    private StockOutcomeBillMapper billMapper;
    @Autowired
    private StockOutcomeBillItemMapper itemMapper;
    @Autowired
    private IProductStockService stockService;

    @Override
    public void saveOrUpdate(StockOutcomeBill bill) {
        System.out.println(bill);
        if (bill.getId() != null) {
            //1.获得原来的单据对象
            StockOutcomeBill old = billMapper.selectByPrimaryKey(bill.getId());
            //2.判断是否处于待审核状态,处于待审核就可以修改
            if (old.getStatus() == StockOutcomeBill.STATE_NOMAL) {
                //3..先把该单据中所有的明细删除
                itemMapper.deleteByBillId(bill.getId());
                //4.保存制单人信息
                bill.setInputUser(old.getInputUser());
                //3.保存制单时间
                bill.setInputTime(new Date());
                BigDecimal totalAmount = BigDecimal.ZERO;
                BigDecimal totalNumber = BigDecimal.ZERO;
                //4.迭代单据明细中的每一条数据,计算总金额和总数量
                for (StockOutcomeBillItem item : bill.getItems()) {
                    BigDecimal amount = item.getSalePrice().multiply(item.getNumber()).setScale(2,
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
            List<StockOutcomeBillItem> items = bill.getItems();
            for (StockOutcomeBillItem item : items) {
                BigDecimal amount = item.getSalePrice().multiply(item.getNumber()).setScale(2,
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
            for (StockOutcomeBillItem item : items) {
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
    public StockOutcomeBill get(Long id) {
        StockOutcomeBill bill = billMapper.selectByPrimaryKey(id);
        return bill;
    }

    @Override
    public List<StockOutcomeBill> listAll() {
        List<StockOutcomeBill> list = billMapper.selectAll();
        return list;
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = billMapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.empty(qo.getPageSize());
        }
        List<StockOutcomeBill> list = billMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), list, totalCount);
    }

    @Override
    public void audit(Long auditId) {
        //审核
        //1.根据Id获取数据库中要审核的出库单据
        StockOutcomeBill old = billMapper.selectByPrimaryKey(auditId);
        //2.判断审核状态,如果审核状态为待审核就审核
        if (old.getStatus() == StockOutcomeBill.STATE_NOMAL) {
            //3.重新设置为已审核
            old.setStatus(StockOutcomeBill.STATE_AUDIT);
            //4.设置审核信息(审核人,审核日期)
            old.setAuditTime(new Date());
            old.setAuditor(UserContext.getCurrentUser());
            //修改出库单据数据
            billMapper.updateByPrimaryKey(old);
            //修改库存数据
            stockService.stockOutCome(old);
        }
    }
}
