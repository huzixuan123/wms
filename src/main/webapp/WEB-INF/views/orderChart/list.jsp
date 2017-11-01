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
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
<title>PSS-采购订单管理</title>
<style>
	.alt td{ background:black !important;}
</style>
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
	<form id="searchForm" action="/chart/order.do" method="post">
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
							货品:
							<input type="text" class="ui_input_txt02" name="keyword"/>
							供应商:
							<select class="ui_select01" name="supplierId" id="supplierId">
								<option value="-1">所有供应商</option>
								<c:forEach items="${suppliers}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
							品牌:
							<select class="ui_select01" name="brandId" id="brandId">
								<option value="-1">所有品牌</option>
								<c:forEach items="${brands}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
							分组:
							<select class="ui_select01" name="groupBy" id="groupBy">
								<c:forEach items="${groups}" var="entry">
									<option value="${entry.key}">${entry.value}</option>
								</c:forEach>
							</select>
						</div>
						<script type="text/javascript">
							$(function(){
								//高级查询click事件
								$(".btn_select").click(function(){
									$("#searchForm").submit();
								});
								//下拉列表回显
								$("#supplierId option[value=${qo.supplierId}]").prop("selected",true);
								$("#brandId option[value=${qo.brandId}]").prop("selected",true);
								$("#groupBy option[value='${qo.groupBy}']").prop("selected",true);
							})
						</script>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_select"/> 
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th>分组类型</th>
							<th>采购总数量</th>
							<th>采购总金额</th>
						</tr>
						<tbody>
							<c:forEach items="${dataList}" var="item">
								<tr>
									<td>${item.groupType}</td>
									<td>${item.totalNumber}</td>
									<td>${item.totalAmount}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
