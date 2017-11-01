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
	//时间插件
	$(function(){
		$("input[name=vdate]").addClass("Wdate").click(function(){
			WdatePicker();
		})
	})
</script>
</head>
<body>
<script type="text/javascript">
	$(function(){
		//选择商品事件
		$(".searchproduct").click(function(){
			//找到放大镜所在行
			var currentTr = $(this).closest("tr");
			url="/itemProduct/list.do";
			$.dialog.open(url,{
				title:"货品信息",
				width:800,
				height:600,
				close:function(){
					var json = $.dialog.data("json");
					if(json!=null){
						//把数据回显到当前行中
						currentTr.find("[tag=pid]").val(json.id);
						currentTr.find("[tag=name]").val(json.name);
						currentTr.find("[tag=costPrice]").val(json.costPrice);
						currentTr.find("[tag=brand]").text(json.brandName);
					}
				}
			})
		})
		
		//给商品价格和数量绑定失去焦点事件,自动计算金额小计
		$("input[tag=costPrice],input[tag=number]").blur(function(){
			var currentTr = $(this).closest("tr");
			var costPrice = parseFloat(currentTr.find("[tag=costPrice]").val());
			var number = parseFloat(currentTr.find("[tag=number]").val());
			var amount = (costPrice * number).toFixed(2);
			currentTr.find("[tag=amount]").text(amount);
		})
		
		//添加明细框的点击事件
		$(".appendRow").click(function(){
			//获取第一行明细,并克隆
			var copy = $("#edit_table_body tr:first-child").clone(true)
			//每次克隆后先清空
			copy.find("[tag=name]").val("");
			copy.find("[tag=brand]").text("");
			copy.find("[tag=costPrice]").val("");
			copy.find("[tag=number]").val("");
			copy.find("[tag=amount]").text("");
			copy.find("[tag=remark]").val("");
			//将克隆出的新的一行添加到父元素中
			copy.appendTo("#edit_table_body");
		});
		
		//在提交表单之前,计算出明细的索引
		$("#editForm").submit(function(){
			$.each($("#edit_table_body tr"),function(index,item){
				$(item).find("[tag=pid]").prop("name","items["+index+"].product.id");
				$(item).find("[tag=costPrice]").prop("name","items["+index+"].costPrice");
				$(item).find("[tag=number]").prop("name","items["+index+"].number");
				$(item).find("[tag=remark]").prop("name","items["+index+"].remark");
			})
		})
		//在提交表单之前发送一个ajax请求,并根据接受到的json数据返回一个对话框
		$("#editForm").ajaxForm(function(data){
			if(data.success){
				$.dialog({
					title:"温馨提示",
					content:"操作成功",
					icon:"face-smile",
					ok:function(){
						window.location.href="/stockIncomeBill/list.do";
					}
				});
			} 
		});
		
		//明细框的删除
		$(".removeItem").click(function(){
			var currentTr = $(this).closest("tr");
			if($("#edit_table_body tr").size()>1){
				//自己删除自己
				currentTr.remove();
			}else{
				//清空
				currentTr.find("[tag=name]").val("");
				currentTr.find("[tag=brand]").text("");
				currentTr.find("[tag=costPrice]").val("");
				currentTr.find("[tag=number]").val("");
				currentTr.find("[tag=amount]").text("");
				currentTr.find("[tag=remark]").val("");
			}
		});
	});
</script>

<form name="editForm" action="/stockIncomeBill/saveOrUpdate.do" method="post" id="editForm">
	<input type="hidden" name="id" value="${stockIncomeBill.id}">
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">入库订单编辑</span>
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
                                <th width="60"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                           <c:if test="${empty stockIncomeBill}">
	                           <!--添加  -->
	                            <tr>
	                                <td></td>
	                                <td>
	                                    <input disabled="true" readonly="true" class="ui_input_txt02" tag="name"/>
	                                    <img src="/images/common/search.png" class="searchproduct"/>
	                                    <input type="hidden" name="items[0].product.id" tag="pid"/>
	                                </td>
	                                <td><span tag="brand">${items.product.brandName}</span></td>
	                                <td><input tag="costPrice" name="items[0].costPrice"
	                                                 class="ui_input_txt00"/></td>
	                                <td><input tag="number" name="items[0].number"
	                                                 class="ui_input_txt00"/></td>
	                                <td><span tag="amount"></span></td>
	                                <td><input tag="remark" name="items[0].remark"
	                                                 class="ui_input_txt02"/></td>
	                                <td>
	                                    <a href="javascript:;" class="removeItem">删除明细</a>
	                                </td>
	                            </tr>
                            </c:if>
                            <c:if test="${not empty stockIncomeBill}">
	                            <!--修改  -->
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
	                                <td>
	                                    <a href="javascript:;" class="removeItem">删除明细</a>
	                                </td>
	                            </tr>
	                            </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
					&nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
					&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
					</td>
				</tr>
		</div>
	</div>
</form>
</body>
</html>