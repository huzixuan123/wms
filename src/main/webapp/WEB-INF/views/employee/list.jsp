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
<title>PSS-员工管理</title>
<style>
	.alt td{ background:black !important;}
</style>
<script type="text/javascript">
	$(function(){
		//跳转到input
		$(".btn_input").click(function(){
			window.location.href="/employee/input.do";
		});
		//删除操作
		$(".btn_delete").click(function(){
			var deleteUrl = $(this).data("url");
			$.dialog({
				title:"温馨提示",
				content:"确定要删除吗?",
				icon:"face-smile",
				cancel:true,
				ok:function(){
					var dg = $.dialog({
						title:"温馨提示",
						icon:"face-smile"
					});
					$.post(deleteUrl,function(data){
						if(data.success){
							dg.content("删除成功").button({
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
		//批量删除操作
		$(".btn_batchDelete").click(function(){
			if($("input:checked").size()==0){
				$.dialog({
					title:"温馨提示",
					content:"亲,请选择被删除的数据",
					icon:"face-smile",
					ok:true
				})
			}else{
				var deleteUrl = $(this).data("url");
				var deleteIds=[];
				$.each($(".acb:checked"),function(index,item){
					deleteIds[index]=$(item).data("eid");
				});
				var count=$(".acb:checked").size();
				$.dialog({
					title:"温馨提示",
					content:"确定要批量删除"+count+"条数据吗?",
					icon:"face-smile",
					cancel:true,
					ok:function(){
						var dg = $.dialog({
							title:"温馨提示",
							icon:"face-smile",
						})
						$.post(deleteUrl,{ids:deleteIds},function(data){
							if(data.success){
								dg.content("批量删除成功").button({
									name:"确定",
									callback:function(){
										window.location.reload();
									}
								});
							}
						})
					}
				})
			}
		});
		
		//复选框批量选中事件
		$("#all").click(function(){
			$(":input[name=IDCheck]").prop("checked",this.checked);
		})
	})
	
</script>
</head>
<body>
	<form id="searchForm" action="/employee/list.do" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							姓名/邮箱
							<input type="text" class="ui_input_txt02" name="keyword" value="${qo.keyword}"/>
							所属部门
							<select class="ui_select01" name="deptId" id="deptId">
								<option value="-1">所有部门</option>
								<c:forEach items="${depts}" var="item">
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
								$("#deptId option[value=${qo.deptId}]").prop("selected",true);
							})
						</script>
						<div id="box_bottom">
							<input type="button" value="批量删除" class="ui_input_btn01 btn_batchDelete"
								data-url="/employee/batchDelete.do"
							/>
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
							<th>编号</th>
							<th>用户名</th>
							<th>EMAIL</th>
							<th>年龄</th>
							<th>所属部门</th>
							<th></th>
						</tr>
						<tbody>
							
							<c:forEach items="${pageResult.list}" var="item">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" 
										data-eid="${item.id}"
									/></td>
									<td>${item.id}</td>
									<td>${item.name}</td>
									<td>${item.email}</td>
									<td>${item.age}</td>
									<td>${item.dept.name}</td>
									<td>
										<a href="/employee/input.do?id=${item.id}">编辑</a>
										<a href="javascript:;" class="btn_delete" data-url="/employee/delete.do?id=${item.id}">删除</a>
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
