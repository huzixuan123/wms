<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>PSS-客户管理</title>
<style>
	.alt td{ background:black !important;}
</style>
<script type="text/javascript">
	$(function(){
		//跳转到input
		$(".btn_input").click(function(){
			window.location.href="/client/input.do";
		});
	})
</script>
</head>
<body>
	<form id="searchForm" action="/client/list.do" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
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
							<th>编号</th>
							<th>客户名称</th>
							<th>客户编码</th>
							<th>联系方式</th>
							<th></th>
						</tr>
						<tbody>
							<c:forEach items="${pageResult.list}" var="item">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" /></td>
									<td>${item.id}</td>
									<td>${item.name}</td>
									<td>${item.sn}</td>
									<td>${item.phone}</td>
									<td>
										<a href="/client/input.do?id=${item.id}">编辑</a>
										<a href="javascript:;" class="btn_delete" data-url="/client/delete.do?id=${item.id}">删除</a>
									</td>
									
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
