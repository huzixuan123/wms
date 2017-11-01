<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>WMS-权限管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript">
    	$(function(){
    		//加载权限
    		$(".btn_reload").click(function(){
    			var reloadUrl = $(this).data("url");
    			$.dialog({
    				title:"温馨提示",
    				content:"加载权限可能需要很长时间,确定加载吗?",
    				icon:"face-smile",
    				cancel:true,
    				ok:function(){
    					var dg = $.dialog({
    						title:"温馨提示",
    						icon:"face-smile"
    					});
    					$.post(reloadUrl,function(data){
    						if(data.success){
    							dg.content("加载成功").button({
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
    	})
    </script>
</head>
<body>
<form id="searchForm" action="/permission/list.do" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_bottom">
                        <input type="button" value="权限加载" class="ui_input_btn01 btn_reload"
                        data-url="/permission/reload.do"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>编号</th>
                        <th>权限名称</th>
                        <th>权限表达式</th>
                        <th style="width: 200px"></th>
                    </tr>
                    <tbody>
                    <c:forEach items="${pageResult.list}" var="item">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" class="acb"/></td>
                            <td>${item.id} </td>
                            <td>${item.name} </td>
                            <td>${item.expression} </td>
                            <td>
                                <a href="javascript:;" class="btn_delete" data-url="/permission/delete.do?id=${item.id}">删除</a>
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
    
