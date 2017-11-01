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
<script type="text/javascript" src="/js/jquery/iframeTools.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript" src="/js/plugins/fancyBox/jquery.fancybox.pack.js"></script>
<link rel="stylesheet" type="text/css" href="/js/plugins/fancyBox/jquery.fancybox.css" media="screen" />
<script type="text/javascript">
	$(function(){
		$(".btn_choose").click(function(){
			var json = $(this).data("json");
			$.dialog.data("json",json);
			$.dialog.close();
		})
	});
</script>
<title>PSS-货品管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="searchForm" action="/itemProduct/list.do" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							货品名称/货品编号
							<input type="text" class="ui_input_txt02" name="keyword" value="${qo.keyword}"/>
							所属品牌
							<select class="ui_select01" name="brandId" id="brandId">
								<option value="-1">所有品牌</option>
								<c:forEach items="${brands}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
						</div>
						<script type="text/javascript">
							$(function(){
								//高级查询click事件
								$(".btn_select").click(function(){
									$("input[name='currentPage']").val(1);
									$("#searchForm").submit();
								});
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
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>编号</th>
							<th>货品图片</th>
							<th>货品名称</th>
							<th>货品编码</th>
							<th>货品品牌</th>
							<th>成本价格</th>
							<th>销售价格</th>
							
							<th></th>
						</tr>
						<tbody>
							<c:forEach items="${pageResult.list}" var="item">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" /></td>
									<td>${item.id}</td>
									<td>
									<a class="fancybox" href="${item.imagePath}">
										<img src="${item.smallImagePath}" class="list_img_min">
									</a>
									</td>
									<td>${item.name}</td>
									<td>${item.sn}</td>
									<td>${item.brandName}</td>
									<td>${item.costPrice}</td>
									<td>${item.salePrice}</td>
									<td>
										<input type="button" value="选择该商品" class="left2right btn_choose"
											data-json='${item.jsonString}'
										/>
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
