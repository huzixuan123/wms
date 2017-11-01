<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/jquery-form/jquery.form.min.js"></script>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/jquery/iframeTools.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function(){
		//返回列表
		$("#cancelbutton").click(function(){
			window.history.back();
		})
		//将查看的信息设置为只读
		$(":input").prop("readonly",true);
		//下拉列表回显
		$("#depotId option[value=${stockIncomeBill.depot.id}]").prop("selected",true);
	})
</script>
</head>
<body>
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">入库订单查看</span>
			<div id="page_close">
				<a>
					<img  width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" bstockIncome="0">
				<tr>
					<td class="ui_text_rt" width="140">订单编号</td>
					<td class="ui_text_lt">
						<input name="sn" value="${stockIncomeBill.sn}" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">仓库</td>
					<td class="ui_text_lt">
						<select id="depotId" name="depot.id" class="ui_select01">
							<c:forEach items="${depots}" var="item">
								<option value="${item.id}">${item.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">业务时间</td>
					<td class="ui_text_lt">
						
						<input type="text" name="vdate" value="<fmt:formatDate value="${stockIncomeBill.vdate}" pattern="yyyy-MM-dd"/>" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
                    <td class="ui_text_rt" width="140">单据明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
                        <table class="edit_table" cellspacing="0" cellpadding="0" bstockIncome="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
	                        <c:forEach items="${stockIncomeBill.items}" var="item">
	                              <tr>
	                                <td></td>
	                                <td>
	                                    <input disabled="true" readonly="true" class="ui_input_txt02" name="items[0].product.name" value="${item.product.name}" tag="name"/>
	                                    <img src="/images/common/search.png" class="searchproduct"/>
	                                    <input type="hidden" name="items[0].product.id" value="${item.product.id}" tag="pid"/>
	                                </td>
	                                <td><span tag="brand">${item.product.brandName}</span></td>
	                                <td><input tag="costPrice" name="items[0].costPrice" value="${ item.costPrice}"
	                                                 class="ui_input_txt00"/></td>
	                                <td><input tag="number" name="items[0].number" value="${ item.number}"
	                                                 class="ui_input_txt00"/></td>
	                                <td><span tag="amount">${ item.amount}</span></td>
	                                <td><input tag="remark" name="items[0].remark" value="${ item.remark}"
	                                                 class="ui_input_txt02"/></td>
	                          </tr>
	                          </c:forEach>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
					&nbsp;<input id="cancelbutton" type="button" value="返回列表" class="ui_input_btn01"/>
					</td>
				</tr>
		</div>
	</div>
</body>
</html>