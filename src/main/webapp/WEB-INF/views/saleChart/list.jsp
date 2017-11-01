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
<script type="text/javascript" src="/js/jquery/iframeTools.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
<title>PSS-销售信息管理</title>
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
	<form id="searchForm" action="/chart/sale.do" method="post">
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
							<input type="text" class="ui_input_txt02" name="keyword" value="${qo.keyword}"/>
							客户:
							<select class="ui_select01" name="clientId" id="clientId">
								<option value="-1">所有客户</option>
								<c:forEach items="${clients}" var="item">
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
						<script type="text/javascript">
							$(function () {
								$(":input").change(function () {
									$("#searchForm").submit();
								});
							})
						</script>
						</div>
						<script type="text/javascript">
							$(function(){
								//高级查询click事件
								$(".btn_select").click(function(){
									$("#searchForm").submit();
								});
								//下拉列表回显
								$("#clientId option[value=${qo.clientId}]").prop("selected",true);
								$("#brandId option[value=${qo.brandId}]").prop("selected",true);
								$("#groupBy option[value='${qo.groupBy}']").prop("selected",true);
							})
						</script>
						<div class="box_bottom" style="padding-right:10px;text-align: right">
							<input type="button" value="柱状报表" class="left2right btn_bar"/>
							<input type="button" value="饼状报表" class="left2right btn_pie"/>
						</div>
						<script type="text/javascript">
							$(function () {
								$(".btn_bar").click(function () {
									var formStr = $("#searchForm").serialize();
									$.dialog.open("/chart/groupByBar.do?"+formStr,{
										title:"柱状报表",
										width:650,
										height:500
									})
								});
								$(".btn_pie").click(function () {
									var formStr = $("#searchForm").serialize();
									$.dialog.open("/chart/groupByPie.do?"+formStr,{
										title:"饼状报表",
										width:650,
										height:500
									})
								});
							});
						</script>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th>分组类型</th>
							<th>销售总数量</th>
							<th>销售总金额</th>
							<th>毛利润</th>
						</tr>
						<tbody>
							<c:forEach items="${dataList}" var="item">
								<tr>
									<td>${item.groupType}</td>
									<td>${item.totalNumber}</td>
									<td>${item.totalAmount}</td>
									<td>${item.grossProfit}</td>
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
