<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
<title>PSS-采购订单管理</title>
<style>
	.alt td{ background:black !important;}
</style>
<script type="text/javascript">
	$(function(){
		//跳转到input
		$(".btn_input").click(function(){
			window.location.href="/orderBill/input.do";
		});
	
		//审核
		$(".btn_audit").click(function(){
			var auditUrl = $(this).data("url");
			$.dialog({
				title:"温馨提示",
				content:"确定要审核吗?",
				icon:"face-smile",
				cancel:true,
				ok:function(){
					var dg = $.dialog({
						title:"温馨提示",
						icon:"face-smile"
					});
					$.post(auditUrl,function(data){
						if(data.success){
							dg.content("审核成功").button({
								name:"确定",
								callback:function(){
									window.location.reload();
								}
							});
						}
					});
				}
			});
		});
	});
</script>
<script type="text/javascript">
	$(function(){
		//添加时间插件
		$("input[name=beginDate],input[name=endDate]").addClass("Wdate").click(function(){
			WdatePicker();
		});
		
	})
</script>
</head>
<body>
	<form id="searchForm" action="/orderBill/list.do" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							业务时间:
							<input type="text" class="ui_input_txt02" name="beginDate" 
							value="<fmt:formatDate value="${qo.beginDate}" pattern="yyyy-MM-dd"/>"/>
							~
							<input type="text" class="ui_input_txt02" name="endDate" 
							value="<fmt:formatDate value="${qo.endDate}" pattern="yyyy-MM-dd"/>"/>
							供应商:
							<select class="ui_select01" name="supplierId" id="supplierId">
								<option value="-1">所有供应商</option>
								<c:forEach items="${suppliers}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
							审核状态:
							<select class="ui_select01" name="status" id="status">
								<option value="-1">所有状态</option>
								<option value="0">待审核</option>
								<option value="1">已审核</option>
							</select>
						</div>
						<script type="text/javascript">
							$(function(){
								//高级查询click事件
								$(".btn_select").click(function(){
									$("input[name='currentPage']").val(1);
									$("#searchForm").submit();
								});
								//下拉列表回显
								$("#supplierId option[value=${qo.supplierId}]").prop("selected",true);
								$("#status option[value=${qo.status}]").prop("selected",true);
							})
						</script>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_select"/> 
							<input type="button" value="新增" class="ui_input_btn01 btn_input"/> 
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>订单编号</th>
							<th>业务时间</th>
							<th>供应商</th>
							<th>总金额</th>
							<th>总数量</th>
							<th>录入人</th>
							<th>审核人</th>
							<th>状态</th>
							<th></th>
						</tr>
						<tbody>
							<c:forEach items="${pageResult.list}" var="item">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" /></td>
									<td>${item.id}</td>
									<td><fmt:formatDate value="${item.vdate}" pattern="yyyy-MM-dd"/></td>
									<td>${item.supplier.name}</td>
									<td>${item.totalAmount}</td>
									<td>${item.totalNumber}</td>
									<td>${item.inputUser.name}</td>
									<td>${item.auditor.name}</td>
									<c:if test=""></c:if>
									<td>
										<span style="color: ${item.status==0?'green':'red'}">
											${item.displayStatus}
										</span>
									</td>
									<c:if test="${item.status==0}">
									<td>
										<a href="/orderBill/input.do?id=${item.id}">编辑</a>
										<a href="javascript:;" class="btn_delete" data-url="/orderBill/delete.do?id=${item.id}">删除</a>
										<a href="javascript:;" class="btn_audit" data-url="/orderBill/audit.do?auditId=${item.id}">审核</a>
									</td>
									</c:if>
									<c:if test="${item.status==1}">
									<td>
										<a href="/orderBill/view.do?id=${item.id}">查看</a>
									</td>
									</c:if>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<%@include file="/WEB-INF/views/common/common_page.jsp" %>
			</div>
		</div>
	</form>
</body>
</html>
