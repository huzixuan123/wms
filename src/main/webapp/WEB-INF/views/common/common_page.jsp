<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			<script type="text/javascript">
				$(function(){
					//翻页和跳转按钮的的click事件
					$(".btn_page").click(function(){
						var pageNum=$(this).data("page")|| $("input[name='currentPage']").val()//获取要跳转的页码
						$("input[name='currentPage']").val(pageNum);//将页码设置给跳转的文本框中
						$("#searchForm").submit();//提交高级查询表单
					});
					//pageSize的onchange事件
					$(":input[name='pageSize']").change(function(){
						$("input[name='currentPage']").val(1);//将要跳转到首页
						$("#searchForm").submit();//提交高级查询表单
					});
					//select标签的回显
					$(":input[name=pageSize] option[value=${pageResult.pageSize}]").prop("selected",true);
				});
			</script>
    		<div class="ui_tb_h30">
					<div class="ui_flt" style="height: 30px; line-height: 30px;">
						共有
						<span class="ui_txt_bold04">${pageResult.totalCount}</span>
						条记录，当前第
						<span class="ui_txt_bold04">${pageResult.currentPage}/${pageResult.totalPage}</span>
						页
					</div>
					<div class="ui_frt">
						<input type="button" value="首页" class="ui_input_btn01 btn_page" data-page="1"/>
						<input type="button" value="上一页" class="ui_input_btn01 btn_page" data-page="${pageResult.prevPage}"/>
						<input type="button" value="下一页" class="ui_input_btn01 btn_page" data-page="${pageResult.nextPage}"/>
						<input type="button" value="尾页" class="ui_input_btn01 btn_page"   data-page="${pageResult.totalPage}"/>
						
						<select name="pageSize" class="ui_select02" >
							<option value="5">5</option>
							<option value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
						</select>
						转到第<input type="text" name="currentPage" value="${pageResult.currentPage}" cssClass="ui_input_txt01" type="number" min="1" style="width:50px;"/>
							 <input type="button" class="ui_input_btn01 btn_page" value="跳转"/>
					</div>
				</div>