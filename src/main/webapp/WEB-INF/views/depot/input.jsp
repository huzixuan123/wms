<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" src="/js/commonAll.js"></script>
</head>
<body>
<script type="text/javascript">
	$(function(){
		$("#editForm").ajaxForm(function(data){
		console.debug(data.success);
			if(data.success){
				$.dialog({
					title:"温馨提示",
					content:"操作成功",
					icon:"face-smile",
					ok:function(){
						window.location.href="/depot/list.do";
					}
				});
			} 
		});
	});
</script>

<form name="editForm" action="/depot/saveOrUpdate.do" method="post" id="editForm">
	<input type="hidden" name="id" value="${depot.id}">
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">仓库编辑</span>
			<div id="page_close">
				<a>
					<img  width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">仓库名称</td>
					<td class="ui_text_lt">
						<input name="name" value="${depot.name}" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">仓库地址</td>
					<td class="ui_text_lt">
						<input type="text" name="location" value="${depot.location}" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>
</body>
</html>