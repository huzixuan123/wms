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
</head>
<body>
	<form id="searchForm" action="/productStock/list.do" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							货品名称或编号:
							<input type="text" class="ui_input_txt02" name="keyword" value="${qo.keyword}"/>
							所在仓库:
							<select class="ui_select01" name="depotId" id="depotId">
								<option value="-1">所有仓库</option>
								<c:forEach items="${depots}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
							货品品牌:
							<select class="ui_select01" name="brandId" id="brandId">
								<option value="-1">所有品牌</option>
								<c:forEach items="${brands}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
							库存阈值:
							<input type="text" class="ui_input_txt02" name="maxNumber" value="${qo.maxNumber}"/>
						</div>
						<script type="text/javascript">
							$(function(){
								//高级查询click事件
								$(".btn_select").click(function(){
									$("#searchForm").submit();
								});
								//下拉列表回显
								$("#depotId option[value=${qo.depotId}]").prop("selected",true);
								$("#brandId option[value=${qo.brandId}]").prop("selected",true);
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
							<th>仓库</th>
							<th>货品编码</th>
							<th>货品名称</th>
							<th>品牌</th>
							<th>库存数量</th>
							<th>成本</th>
							<th>库存汇总</th>
						</tr>
						<tbody>
							<c:forEach items="${list}" var="item">
								<tr>
									<td>${item.depot.name}</td>
									<td>${item.product.sn}</td>
									<td>${item.product.name}</td>
									<td>${item.product.brandName}</td>
									<td>${item.storeNumber}</td>
									<td>${item.price}</td>
									<td>${item.amount}</td>
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
